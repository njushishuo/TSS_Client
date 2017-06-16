package software.nju.tssclient.view.ViewHolder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import software.nju.tssclient.R;

/**
 * Created by ss14 on 2017/6/16.
 */

public class CourseViewHolder extends RecyclerView.ViewHolder {

    public TextView nameText;
    public CardView cardView;

    public CourseViewHolder(View itemView) {
            super(itemView);
            nameText = (TextView) itemView.findViewById(R.id.course_name_text);
            cardView = (CardView) itemView.findViewById(R.id.course_card_view);
    }
}