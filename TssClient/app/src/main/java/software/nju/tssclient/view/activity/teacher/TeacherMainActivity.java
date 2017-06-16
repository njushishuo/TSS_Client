package software.nju.tssclient.view.activity.teacher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import software.nju.tssclient.R;
import software.nju.tssclient.view.fragment.CourseListFragment;
import software.nju.tssclient.view.fragment.teacher.GroupListFragment;
import software.nju.tssclient.view.fragment.teacher.TeacherProfileFragment;

public class TeacherMainActivity extends AppCompatActivity {

    @BindView(R.id.tea_navigation)
    BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.tea_group:
                    selectedFragment = new GroupListFragment();
                    break;
                case R.id.tea_course:
                    selectedFragment = new CourseListFragment();
                    break;
                case R.id.tea_profile:
                    selectedFragment = new TeacherProfileFragment();
                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.tea_fragment_container, selectedFragment);
            transaction.commit();
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);
        ButterKnife.bind(this);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.tea_fragment_container, new GroupListFragment());
        transaction.commit();

    }

}
