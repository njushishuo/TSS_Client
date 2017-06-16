package software.nju.tssclient.view.fragment.common;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import software.nju.tssclient.R;
import software.nju.tssclient.model.entity.Homework;
import software.nju.tssclient.presenter.contract.HomeworkContract;
import software.nju.tssclient.presenter.impl.HomeworkPresenterImpl;
import software.nju.tssclient.util.TokenBuilder;
import software.nju.tssclient.view.Adapter.AssignmentAdapter;
import software.nju.tssclient.view.GridItemDividerDecoration;


public class HomeworkListFragment extends Fragment  implements HomeworkContract.View {


    @BindView(R.id.homework_recycler_view)
    RecyclerView recyclerView;

    private HomeworkContract.Presenter presenter;

    AssignmentAdapter adapter;


    public HomeworkListFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new HomeworkPresenterImpl(this);
        adapter = new AssignmentAdapter(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_homework_list, container, false);
        ButterKnife.bind(this,view);

        Intent intent =getActivity().getIntent();
        int courseId  = (int) intent.getExtras().get("courseId");
        String token = TokenBuilder.getToken(PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()));

        Log.d("token", token);
        Log.d("courseId", courseId+"");


        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new GridItemDividerDecoration
                (getContext(), R.dimen.divider_height, R.color.divider));

        presenter.getHomeworkByCourseId(token,courseId);

        return view;
    }


    @Override
    public void showHomework(List<Homework> homeworkList) {

        for(Homework homework : homeworkList){
            System.out.println(homework.getTitle());
        }

        adapter.addAssignments(homeworkList);

    }
}
