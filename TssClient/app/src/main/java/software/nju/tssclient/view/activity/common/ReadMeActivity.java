package software.nju.tssclient.view.activity.common;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import software.nju.tssclient.R;
import software.nju.tssclient.model.entity.ReadMe;
import software.nju.tssclient.presenter.contract.ReadMeContract;
import software.nju.tssclient.presenter.impl.ReadMePresenterImpl;
import software.nju.tssclient.util.TokenBuilder;

/**
 * Created by ss14 on 2017/6/22.
 */

public class ReadMeActivity  extends AppCompatActivity implements ReadMeContract.View{


    @BindView(R.id.readme_text)
    TextView readMeText;

    private ReadMeContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readme);
        ButterKnife.bind(this);

        this.presenter = new ReadMePresenterImpl(this);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String token = TokenBuilder.getToken(preferences);
        int assignmentId=0,questionId=0,stdId =0;
        Intent intent = getIntent();
        assignmentId = intent.getIntExtra("assignmentId",38);
        questionId = intent.getIntExtra("questionId",1);
        stdId = preferences.getInt("userId",227);

        Log.d("assId",assignmentId+"");
        Log.d("quesId",questionId+"");
        Log.d("stdId",stdId+"");

        this.presenter.getReadMeByThreeIds(token,assignmentId,stdId,questionId);
        this.readMeText.setText("测试用的readme");
    }


    @Override
    public void showReadMe(ReadMe readMe) {
        this.readMeText.setText(readMe.getContent());
    }
}