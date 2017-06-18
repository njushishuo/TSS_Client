package software.nju.tssclient.view.fragment.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import software.nju.tssclient.R;
import software.nju.tssclient.model.entity.Student;
import software.nju.tssclient.model.entity.User;
import software.nju.tssclient.view.activity.common.LoginActivity;


public class ProfileFragment extends Fragment {

    @BindView(R.id.profile_name_text)
    TextView nameText;
    @BindView(R.id.profile_number_text)
    TextView numberText;
    @BindView(R.id.profile_number_label)
    TextView numberLabel;
    @BindView(R.id.profile_type_text)
    TextView typeText;
    @BindView(R.id.profile_email_text)
    TextView emailText;
    @BindView(R.id.profile_gender_text)
    TextView genderText;

    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this,view);

        Intent intent =getActivity().getIntent();
        User user  = (User) intent.getExtras().get(LoginActivity.EXTRA_MESSAGE);
        if(user!=null){
            nameText.setText(user.getName());
            typeText.setText(getType(user.getType()));
            genderText.setText(getGender(user.getGender()));
            emailText.setText(user.getEmail());

            if(user.getType().equals("student")){

            }else{
                view.findViewById(R.id.number_layout).setVisibility(View.GONE);
            }

        }


        return view;
    }

    private String getType(String type){
        if(type.equals("student")){
            return "学生";
        }else if(type.equals("teacher")){
            return "老师";
        }else if(type.equals("admin")){
            return "管理员";
        }
        return  "未知";
    }

    private String getGender(String gender){
        if(gender.equals("male")){
            return "男";
        }else if(gender.equals("female")){
            return "女";
        }
        return  "未知";
    }


}
