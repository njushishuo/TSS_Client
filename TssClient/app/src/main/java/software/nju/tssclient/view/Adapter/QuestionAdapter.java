package software.nju.tssclient.view.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import software.nju.tssclient.R;
import software.nju.tssclient.model.entity.Question;
import software.nju.tssclient.model.entity.QuestionScore;
import software.nju.tssclient.view.ViewHolder.QuestionViewHolder;
import software.nju.tssclient.view.activity.common.QuestionAnalysisActivity;
import software.nju.tssclient.view.activity.common.QuestionListActivity;
import software.nju.tssclient.view.activity.common.QuestionScoreActivity;
import software.nju.tssclient.view.activity.common.ReadMeActivity;

/**
 * Created by ss14 on 2017/6/18.
 */

public class QuestionAdapter extends RecyclerView.Adapter<QuestionViewHolder> {
    int assignmentId;
    List<Question> questions;
    Context context;

    public QuestionAdapter(int assignmentId, List<Question> questions, Context context){
        this.assignmentId = assignmentId;
        this.questions = questions;
        this.context  = context;
    }


    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        QuestionViewHolder holder = new QuestionViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(QuestionViewHolder holder, final int position) {
        holder.titleText.setText(questions.get(position).getTitle());
        holder.despText.setText(questions.get(position).getDescription());
        holder.difText.setText(questions.get(position).getDifficulty()+"");
        holder.gitText.setText(questions.get(position).getGitUrl());
        holder.creatorText.setText(questions.get(position).getCreator().getName());


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String type  = sharedPreferences.getString("type","student");
        if(type.equals("student")){
            holder.teacherLayout.setVisibility(View.GONE);
            holder.readMeText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startReadMeActivity(assignmentId,questions.get(position).getId());
                }
            });

            holder.analysisText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startQuestionAnalysisctivity(assignmentId,questions.get(position).getId());
                }
            });

        }else if(type.equals("teacher")){
            holder.studentLayout.setVisibility(View.GONE);
            holder.scoreText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   startQuestionScoreActivity(assignmentId,questions.get(position).getId());
                }
            });

        }




    }

    @Override
    public int getItemCount() {

        return questions==null? 0: questions.size();
    }


    private void startQuestionScoreActivity(int assignmentId , int questionId){
        Intent intent = new Intent(context, QuestionScoreActivity.class);
        intent.putExtra("assignmentId",assignmentId);
        intent.putExtra("questionId",questionId);
        context.startActivity(intent);
    }

    private void startReadMeActivity(int assignmentId , int questionId){
        Intent intent = new Intent(context, ReadMeActivity.class);
        intent.putExtra("assignmentId",assignmentId);
        intent.putExtra("questionId",questionId);
        context.startActivity(intent);
    }


    private void startQuestionAnalysisctivity(int assignmentId , int questionId){
        Intent intent = new Intent(context, QuestionAnalysisActivity.class);
        intent.putExtra("assignmentId",assignmentId);
        intent.putExtra("questionId",questionId);
        context.startActivity(intent);
    }
}
