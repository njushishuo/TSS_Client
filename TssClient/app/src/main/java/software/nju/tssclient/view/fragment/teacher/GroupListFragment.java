package software.nju.tssclient.view.fragment.teacher;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import software.nju.tssclient.R;
import software.nju.tssclient.model.entity.Group;
import software.nju.tssclient.model.entity.User;
import software.nju.tssclient.presenter.contract.GroupContract;
import software.nju.tssclient.presenter.impl.GroupPresenterImpl;
import software.nju.tssclient.view.Adapter.GroupAdapter;
import software.nju.tssclient.view.activity.LoginActivity;


public class GroupListFragment extends Fragment  implements GroupContract.View {


    @BindView(R.id.group_recycler_view)
    RecyclerView recyclerView;

    private GroupContract.Presenter presenter;

    public GroupListFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new GroupPresenterImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_group, container, false);
        ButterKnife.bind(this,view);

        User user = (User) getActivity().getIntent().getExtras().get(LoginActivity.EXTRA_MESSAGE);

        presenter.getAllGroups(user.getUsername(),user.getPassword());

        return view;
    }

    @Override
    public void showGroups(List<Group> groups) {

        for(Group group : groups){
            System.out.println(group.getName());
        }

        GroupAdapter adapter = new GroupAdapter(groups, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

    }
}
