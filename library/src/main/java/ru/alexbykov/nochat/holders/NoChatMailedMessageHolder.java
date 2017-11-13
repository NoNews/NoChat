package ru.alexbykov.nochat.holders;

import android.view.View;
import android.widget.TextView;

import ru.alexbykov.nochat.R;

/**
 * @author Alex Bykov
 *         Date: 13.11.2017.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 */

public class NoChatMailedMessageHolder extends NoChatBaseViewHolder {

    public TextView tvMailedMessage;
    public TextView tvMailedMessageName;
    public TextView tvMailedMessageDate;

    public NoChatMailedMessageHolder(View itemView) {
        super(itemView);
        bindMailedMessageHolder();
        tvMailedMessage = bindView(R.id.tv_mailed_message);
        tvMailedMessageName = bindView(R.id.tv_mailed_message_name);
        tvMailedMessageDate = bindView(R.id.tv_mailed_message_date);
    }

}
