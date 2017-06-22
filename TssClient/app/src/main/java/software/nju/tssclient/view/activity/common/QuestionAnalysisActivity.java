package software.nju.tssclient.view.activity.common;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.ButterKnife;
import software.nju.tssclient.R;
import software.nju.tssclient.model.entity.QuestionResult;
import software.nju.tssclient.presenter.contract.QuestionAnalysisContract;
import software.nju.tssclient.presenter.impl.QuestionAnalysisPresenterImpl;
import software.nju.tssclient.util.TokenBuilder;

/**
 * Created by ss14 on 2017/6/22.
 */
public class QuestionAnalysisActivity extends AppCompatActivity implements QuestionAnalysisContract.View{



    private QuestionAnalysisContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_analysis);
        ButterKnife.bind(this);

        this.presenter = new QuestionAnalysisPresenterImpl(this);

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

        this.presenter.getQuestionAnalysisByThreeIds(token,assignmentId,stdId,questionId);

    }

    @Override
    public void showAnalysis(QuestionResult analysis) {

    }
}