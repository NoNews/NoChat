package ru.alexbykov.nochat.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ru.alexbykov.nochat.R;


/**
 * @author Alex Bykov
 *         Date: 20.10.17.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 */

public class NoChatInboxHolder extends NoChatBaseViewHolder {


    public TextView tvMessage;
    public TextView tvDate;
    public TextView tvName;
    public ImageView civPhoto;
    public ImageView ivImage;


    public NoChatInboxHolder(View itemView) {
        super(itemView);
        tvMessage = bindView(R.id.tv_message);
        tvDate = bindView(R.id.tv_date);
        tvName = bindView(R.id.tv_name);
        ivImage = bindView(R.id.iv_photo);
    }
}
