package software.nju.tssclient.view.ViewHolder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import software.nju.tssclient.R;

/**
 * Created by ss14 on 2017/6/16.
 */

public class AssignmentViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView startAt;
    public TextView endAt;
    public TextView status;
    public CardView cardView;


    public AssignmentViewHolder(View itemView) {
        super(itemView);
        this.title = (TextView) itemView.findViewById(R.id.assignment_title_text);
        this.startAt = (TextView) itemView.findViewById(R.id.assignment_start_text);
        this.endAt = (TextView) itemView.findViewById(R.id.assignment_end_text);
        this.status = (TextView) itemView.findViewById(R.id.assignment_status_text);
        this.cardView = (CardView) itemView.findViewById(R.id.assignment_card_view);

    }
}
