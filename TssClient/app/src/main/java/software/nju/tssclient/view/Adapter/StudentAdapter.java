package software.nju.tssclient.view.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import software.nju.tssclient.R;
import software.nju.tssclient.model.entity.Student;
import software.nju.tssclient.view.ViewHolder.StudentViewHolder;

/**
 * Created by ss14 on 2017/6/15.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentViewHolder> {


    List<Student> list = Collections.emptyList();
    Context context ;

    public StudentAdapter (List<Student > list , Context context){
        this.list = list;
        this.context = context;
    }


    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        StudentViewHolder holder = new StudentViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        holder.numberText.setText(list.get(position).getNumber());
        holder.nameText.setText(list.get(position).getName());
        holder.emailText.setText(list.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
