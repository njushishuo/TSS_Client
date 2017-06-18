package software.nju.tssclient.view.fragment.common;

import android.content.SharedPreferences;
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
import software.nju.tssclient.model.entity.Course;
import software.nju.tssclient.presenter.contract.CourseContract;
import software.nju.tssclient.presenter.impl.CoursePresenterImpl;
import software.nju.tssclient.util.TokenBuilder;
import software.nju.tssclient.view.Adapter.CourseAdapter;
import software.nju.tssclient.view.GridItemDividerDecoration;


public class CourseListFragment extends Fragment implements CourseContract.View{

    @BindView(R.id.course_recycler_view)
    RecyclerView recyclerView;

    CourseContract.Presenter presenter;

    public CourseListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new CoursePresenterImpl(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_course_list, container, false);
        ButterKnife.bind(this,view);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        String token = TokenBuilder.getToken(preferences);
        String username = preferences.getString("username","liuqin");
        Log.d("get Username", username);

        presenter.getCoursesByUsername(token,username);
        return  view;
    }

    @Override
    public void showCourses(List<Course> courses) {
        for(Course course : courses){
            System.out.println(course.getName());
        }

        CourseAdapter adapter = new CourseAdapter(courses, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.addItemDecoration(new GridItemDividerDecoration
                (getContext(), R.dimen.divider_height, R.color.divider));
    }
}
