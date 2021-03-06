package software.nju.tssclient.view.fragment.teacher;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import software.nju.tssclient.R;
import software.nju.tssclient.model.entity.Group;
import software.nju.tssclient.presenter.contract.GroupContract;
import software.nju.tssclient.presenter.impl.GroupListPresenterImpl;
import software.nju.tssclient.util.TokenBuilder;
import software.nju.tssclient.view.Adapter.GroupAdapter;
import software.nju.tssclient.view.GridItemDividerDecoration;


public class GroupListFragment extends Fragment  implements GroupContract.View {


    @BindView(R.id.group_recycler_view)
    RecyclerView recyclerView;

    private GroupContract.Presenter presenter;

    public GroupListFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new GroupListPresenterImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_group_list, container, false);
        ButterKnife.bind(this,view);

        String token = TokenBuilder.getToken(PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()));
        presenter.getAllGroups(token);

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
        recyclerView.addItemDecoration(new GridItemDividerDecoration
                (getContext(), R.dimen.divider_height, R.color.divider));

    }
}
