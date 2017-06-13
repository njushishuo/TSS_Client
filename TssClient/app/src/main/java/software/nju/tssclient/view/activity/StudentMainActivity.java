package software.nju.tssclient.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import software.nju.tssclient.model.entity.User;
import software.nju.tssclient.R;
import software.nju.tssclient.view.fragment.teacher.AssignmentListFragment;
import software.nju.tssclient.view.fragment.teacher.GroupListFragment;
import software.nju.tssclient.view.fragment.teacher.StudentProfileFragment;

public class StudentMainActivity extends AppCompatActivity {

    private User user;


    @BindView(R.id.navigation)
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
                case R.id.tea_assignment:
                    selectedFragment = new AssignmentListFragment();
                    break;
                case R.id.tea_profile:
                    selectedFragment = new StudentProfileFragment();
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
        setContentView(R.layout.activity_main_student);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        this.user = (User) intent.getExtras().get(LoginActivity.EXTRA_MESSAGE);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

}
