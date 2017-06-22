package software.nju.tssclient.view.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import software.nju.tssclient.R;
import software.nju.tssclient.model.entity.Assignment;
import software.nju.tssclient.model.entity.Question;
import software.nju.tssclient.util.Status;
import software.nju.tssclient.view.ViewHolder.AssignmentViewHolder;
import software.nju.tssclient.view.activity.common.QuestionListActivity;

/**
 * Created by ss14 on 2017/6/16.
 */

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentViewHolder> {

    private List<Assignment> assignments ;
    private Context context;

    public AssignmentAdapter ( Context context){
        this.context = context;
        this.assignments = new ArrayList<>();
    }

//    public <T extends Assignment> void addAssignments(List<T> assignmentList ){
//        assignments.addAll(assignmentList);
//    }

    public  void addAssignments(List<? extends Assignment> assignmentList ){

        assignments.addAll(assignmentList);
        notifyDataSetChanged();
    }


    @Override
    public AssignmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_assignment, parent, false);
        AssignmentViewHolder holder = new AssignmentViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(AssignmentViewHolder holder, final int position) {

        holder.title.setText(assignments.get(position).getTitle());
        holder.startAt.setText(formatDate(assignments.get(position).getStartAt()));
        holder.endAt.setText(formatDate(assignments.get(position).getEndAt()));
        holder.status.setText(convertStauts(assignments.get(position).getStatus()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuestionListActivity(assignments.get(position).getId() ,assignments.get(position).getQuestions());
            }
        });
    }

    @Override
    public int getItemCount() {
        return assignments.size();
    }


    private String formatDate(String date){

        return "";
    }

    private String convertStauts(Status status){
        String result="";
        switch (status) {
            case newly:
                result = "新建态";
                break;
            case initing:
                result = "正在初始化";
                break;
            case initFail:
                result = "初始化失败";
                break;
            case initSuccess:
                result = "初始化成功";
                break;
            case ongoing:
                result = "考试正在进行";
                break;
            case timeup:
                result = "考试时间到";
                break;
            case analyzing:
                result = "正在分析结果";
                break;
            case analyzingFinish:
                result = "结果分析完毕";
                break;
        }
        return  result;
    }

    private void startQuestionListActivity(int assignmentId, List<Question> questions){

        Intent intent = new Intent(context, QuestionListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("questionList",(Serializable)questions);
        intent.putExtra("assignmentId",assignmentId);
        intent.putExtras(bundle);
        context.startActivity(intent);

    }
}
