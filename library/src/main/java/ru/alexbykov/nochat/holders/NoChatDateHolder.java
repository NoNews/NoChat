package ru.alexbykov.nochat.holders;

import android.view.View;
import android.widget.TextView;

import ru.alexbykov.nochat.R;

/**
 * @author Alex Bykov
 *         Date: 26.10.17.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 */

public class NoChatDateHolder extends NoChatBaseViewHolder {


    public TextView tvHeaderDate;


    public NoChatDateHolder(View itemView) {
        super(itemView);
        tvHeaderDate=bindView(R.id.tv_header_date);
    }
}

