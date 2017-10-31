package ru.alexbykov.nochat.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ru.alexbykov.nochat.R;


/**
 * @author Alex Bykov
 *         Date: 20.10.17.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 */

public class NoChatOutboxHolder extends NoChatBaseViewHolder {

//    public TextView tvMessage;
//    public TextView tvDate;
//    public ImageView ivImage;
//    public RelativeLayout ltMessageRoot;
//    public LinearLayout ltMessage;

    public NoChatOutboxHolder(View itemView) {
        super(itemView);
        tvMessage = bindView(R.id.tv_message);
        tvDate = bindView(R.id.tv_date);
        ivImage = bindView(R.id.iv_image);
        ltMessageRoot = bindView(R.id.lt_message_root);
        ltMessage = bindView(R.id.lt_message);
    }
}
