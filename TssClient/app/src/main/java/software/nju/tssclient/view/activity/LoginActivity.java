package software.nju.tssclient.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import software.nju.tssclient.model.entity.User;
import software.nju.tssclient.presenter.contract.LoginContract;
import software.nju.tssclient.presenter.impl.LoginPresenterImpl;
import software.nju.tssclient.R;
import software.nju.tssclient.view.activity.teacher.TeacherMainActivity;


public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    public static final String  EXTRA_MESSAGE = "USER_INFO";


    @BindView(R.id.loginBtn)
    Button loginBth;
    @BindView(R.id.username)
    TextView usernameText;
    @BindView(R.id.password)
    TextView passwordText;

    LoginContract.Presenter loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenterImpl(this);
        loginBth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login(){
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();
        loginPresenter.getUserInfo(username,password);
    }


    @Override
    public void showUserInfo(User user) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sharedPreferences.edit().putString("username",user.getUsername()).commit();
        sharedPreferences.edit().putString("password",user.getPassword()).commit();

        Intent intent = new Intent(this,TeacherMainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showError() {
        CharSequence text = "登陆失败，请重试";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
        toast.show();
    }
}

