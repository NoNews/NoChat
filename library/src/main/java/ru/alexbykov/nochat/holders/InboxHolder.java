package ru.alexbykov.nochat.holders;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import ru.alexbykov.nochat.R;


/**
 * @author Alex Bykov
 *         Date: 20.10.17.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 */

public class InboxHolder extends BaseViewHolder {


    public TextView tvMessage;
    public TextView tvDate;
    public TextView tvName;
    public ImageView civPhoto;
    public ImageView ivImage;


    public InboxHolder(View itemView) {
        super(itemView);
        tvMessage = bindView(R.id.tv_message);
        tvDate = bindView(R.id.tv_date);
        tvName = bindView(R.id.tv_name);
        ivImage = bindView(R.id.iv_photo);
    }
}
