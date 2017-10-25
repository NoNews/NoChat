package com.example.alexbykov.nochat.adapter;

import android.content.Context;

import com.example.alexbykov.nochat.data.MessageDTO;

import ru.alexbykov.nochat.models.NoChatDate;
import ru.alexbykov.nochat.models.NoChatProgress;
import ru.alexbykov.nochat.holders.BaseViewHolder;
import ru.alexbykov.nochat.holders.InboxHolder;
import ru.alexbykov.nochat.holders.OutboxHolder;

import java.util.ArrayList;

import ru.alexbykov.nochat.BaseChatAdapter;

/**
 * @author Alex Bykov
 *         Date: 20.10.17.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 */

public class ChatAdapter extends BaseChatAdapter<Object> {


    private Context context;
    private String myJid = "1";



    public ChatAdapter() {
        messages = new ArrayList<>();
    }


    @Override
    public int getItemViewType(int position) {

        final Object message = messages.get(position);
        if (message instanceof MessageDTO) {
            if (((MessageDTO) message).getFrom().equals(myJid)) {
                return VIEW_TYPE_OUTBOX;
            } else {
                return VIEW_TYPE_INBOX;
            }
        } else if (message instanceof NoChatProgress) {
            return VIEW_TYPE_PROGRESS;
        } else {
            return VIEW_TYPE_DATE;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        final int itemViewType = getItemViewType(position);


        switch (itemViewType) {

            case VIEW_TYPE_DATE:
                break;
            case VIEW_TYPE_INBOX:
                MessageDTO inbox = (MessageDTO) messages.get(position);
                setupInbox(inbox, holder);
                break;
            case VIEW_TYPE_OUTBOX:
                MessageDTO outbox = (MessageDTO) messages.get(position);
                setupOutbox(outbox, holder);
                break;
        }
        super.onBindViewHolder(holder, position);
    }

    private void setupOutbox(MessageDTO message, BaseViewHolder holder) {
        OutboxHolder outboxHolder = (OutboxHolder) holder;
        outboxHolder.tvMessage.setText(message.getText());
//        outboxHolder.tvDate.setText(message.getDate());
    }

    private void setupInbox(MessageDTO message, BaseViewHolder holder) {
        InboxHolder inboxHolder = (InboxHolder) holder;
        inboxHolder.tvMessage.setText(message.getText());
        inboxHolder.tvName.setText(message.getFrom());
    }


    public void setMyJid(String myJid) {
        this.myJid = myJid;
    }


}
