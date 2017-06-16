package software.nju.tssclient.view.ViewHolder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import software.nju.tssclient.R;

/**
 * Created by ss14 on 2017/6/14.
 * 用于老师查看班级列表
 */

public class GroupViewHolder extends RecyclerView.ViewHolder {

    public TextView idText;
    public TextView nameText;
    public CardView cardView;

    public GroupViewHolder(View itemView) {
        super(itemView);
        idText = (TextView) itemView.findViewById(R.id.group_id_text);
        nameText = (TextView) itemView.findViewById(R.id.group_name_text);
        cardView = (CardView) itemView.findViewById(R.id.group_card_view);
    }
}
