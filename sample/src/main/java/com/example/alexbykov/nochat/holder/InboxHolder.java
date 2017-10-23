package com.example.alexbykov.nochat.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexbykov.nochat.R;

/**
 * @author Alex Bykov
 *         Date: 20.10.17.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 */

public class InboxHolder extends BaseViewHolder {


    private TextView tvMessage;
    private TextView tvDate;
    private TextView tvName;
    private ImageView civPhoto;


    public InboxHolder(View itemView) {
        super(itemView);
        tvMessage = bindView(R.id.tv_message);
        tvDate = bindView(R.id.tv_date);
//        tvName = bindView(R.id.)
    }
}
