package software.nju.tssclient.view.activity.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import software.nju.tssclient.R;
import software.nju.tssclient.model.entity.Student;
import software.nju.tssclient.presenter.contract.GroupDetailContract;
import software.nju.tssclient.presenter.impl.GroupDetailPresenterImpl;
import software.nju.tssclient.util.TokenBuilder;
import software.nju.tssclient.view.Adapter.StudentAdapter;
import software.nju.tssclient.view.GridItemDividerDecoration;

import static java.security.AccessController.getContext;

public class GroupDetailActivity extends AppCompatActivity  implements  GroupDetailContract.View{

    @BindView(R.id.student_recycler_view)
    RecyclerView recyclerView;

    private GroupDetailContract.Presenter presenter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);
        ButterKnife.bind(this);

        this.presenter = new GroupDetailPresenterImpl(this);

        Intent intent = getIntent();
        int id = intent.getIntExtra("groupId",1);
        String token = TokenBuilder.getToken
                (PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        presenter.getStudentsByGroupId(token,id);
    }



    @Override
    public void showStudents(List<Student> students) {
        for(Student student : students){
            System.out.println(student.getName());
        }

        StudentAdapter adapter = new StudentAdapter(students, getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        recyclerView.addItemDecoration(new GridItemDividerDecoration
                (getApplicationContext(), R.dimen.divider_height, R.color.divider));

    }
}
