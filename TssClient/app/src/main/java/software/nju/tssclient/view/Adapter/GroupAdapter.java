package software.nju.tssclient.view.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import software.nju.tssclient.R;
import software.nju.tssclient.model.entity.Group;
import software.nju.tssclient.view.ViewHolder.GroupViewHolder;
import software.nju.tssclient.view.activity.teacher.GroupDetailActivity;

/**
 * Created by ss14 on 2017/6/14.
 * 用于老师查看班级列表
 *
 */

public class GroupAdapter extends RecyclerView.Adapter<GroupViewHolder> {

    List<Group> list = Collections.emptyList();
    Context context ;


    public GroupAdapter (List<Group> groups , Context context){
        this.list = groups;
        this.context = context;
    }



    @Override
    public GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group, parent, false);
        GroupViewHolder holder = new GroupViewHolder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(GroupViewHolder holder, final int position) {
        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.idText.setText(list.get(position).getId()+"");
        holder.nameText.setText(list.get(position).getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGroupDetailActivity(list.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    private void startGroupDetailActivity(int groupId){

        Intent intent = new Intent(context, GroupDetailActivity.class);
        intent.putExtra("id", groupId);
        context.startActivity(intent);
    }

}
