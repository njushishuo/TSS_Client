package software.nju.tssclient.view.activity.common;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import software.nju.tssclient.R;
import software.nju.tssclient.model.entity.QuestionScore;
import software.nju.tssclient.presenter.contract.QuestionScoreContract;
import software.nju.tssclient.presenter.impl.QuestionScorePresenterImpl;
import software.nju.tssclient.util.TokenBuilder;

/**
 * Created by ss14 on 2017/6/22.
 */

public class QuestionScoreActivity extends AppCompatActivity implements QuestionScoreContract.View{



    @BindView(R.id.question_score_chart)
    BarChart barChart;

    private QuestionScoreContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_score);
        ButterKnife.bind(this);

        this.presenter = new QuestionScorePresenterImpl(this);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String token = TokenBuilder.getToken(preferences);
        int assignmentId=0,questionId=0;
        Intent intent = getIntent();
        assignmentId = intent.getIntExtra("assignmentId",38);
        questionId = intent.getIntExtra("questionId",1);

        this.presenter.getQuestionScoreBy(token,assignmentId,questionId);
    }


    @Override
    public void showScoreGraph(QuestionScore questionScore) {
        ArrayList<BarEntry> entryList = new ArrayList();
        entryList.add(new BarEntry(25f,0));
        entryList.add(new BarEntry(34f,1));
        entryList.add(new BarEntry(55f,2));
        entryList.add(new BarEntry(35f,3));
        entryList.add(new BarEntry(21f,3));
        BarDataSet dataSet = new BarDataSet(entryList,"人数");

//        ArrayList<BarEntry> stringList = new ArrayList();
//        stringList.add("<60");
//        stringList.add("60-70");
//        stringList.add("71-80");
//        stringList.add("81-90");
//        stringList.add(">90");

        BarData data  = new BarData(dataSet);

        barChart.setData(data);


    }
}