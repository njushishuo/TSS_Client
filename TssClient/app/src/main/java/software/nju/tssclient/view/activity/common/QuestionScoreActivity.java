package software.nju.tssclient.view.activity.common;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

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
        final String[] quarters = new String[] { "<60", "60-70", "70-80", "80-90",">90" };

        IAxisValueFormatter xAxisformatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return quarters[(int) value];
            }

        };

        IValueFormatter valueFormatter = new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return (int)value +"" ;
            }
        };




        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(14f);
        //xAxis.setTextColor(Color.RED);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisLineColor(R.color.colorAxis);
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(xAxisformatter);

        YAxis yAxisLeft = barChart.getAxisLeft();
        yAxisLeft.setTextSize(14f);
        yAxisLeft.setDrawAxisLine(true);
        yAxisLeft.setDrawGridLines(false);
        yAxisLeft.setAxisLineColor(R.color.colorAxis);

        YAxis yAxisRight = barChart.getAxisRight();
        yAxisRight.setEnabled(false);


        ArrayList<BarEntry> entryList = new ArrayList();
        entryList.add(new BarEntry(0,25f));
        entryList.add(new BarEntry(1,34f));
        entryList.add(new BarEntry(2,55f));
        entryList.add(new BarEntry(3,35f));
        entryList.add(new BarEntry(4,25f));
        BarDataSet dataSet = new BarDataSet(entryList,"人数");
        dataSet.setValueFormatter(valueFormatter);


        BarData data  = new BarData(dataSet);
        data.setValueTextSize(14f);

        data.setBarWidth(0.9f); // set custom bar width
        barChart.setData(data);
        barChart.setFitBars(true); // make the x-axis fit exactly all bars
        barChart.invalidate(); // refresh




    }
}