package com.example.alexbykov.nochat.adapter;

import android.content.Context;
import android.view.View;

import com.example.alexbykov.nochat.data.MessageDTO;
import com.squareup.picasso.Picasso;

import ru.alexbykov.nochat.models.NoChatProgress;
import ru.alexbykov.nochat.holders.NoChatBaseViewHolder;
import ru.alexbykov.nochat.holders.NoChatInboxHolder;
import ru.alexbykov.nochat.holders.NoChatOutboxHolder;

import java.util.ArrayList;

import ru.alexbykov.nochat.BaseNoChatAdapter;

/**
 * @author Alex Bykov
 *         Date: 20.10.17.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 */

public class ChatAdapter extends BaseNoChatAdapter<Object> {


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
    public void onBindViewHolder(NoChatBaseViewHolder holder, int position) {

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

    private void setupOutbox(MessageDTO message, NoChatBaseViewHolder holder) {
        NoChatOutboxHolder outboxHolder = (NoChatOutboxHolder) holder;
        outboxHolder.tvMessage.setText(message.getText());
//        outboxHolder.tvDate.setText(message.getDate());
    }

    private void setupInbox(MessageDTO message, NoChatBaseViewHolder holder) {
        NoChatInboxHolder inboxHolder = (NoChatInboxHolder) holder;
        inboxHolder.tvMessage.setText(message.getText());
        inboxHolder.tvName.setText(message.getFrom());
        if (message.getImage()!=null){
            Picasso.with(inboxHolder.ivImage.getContext())
                    .load(message.getImage())
                    .into(inboxHolder.ivImage);
            inboxHolder.tvMessage.setVisibility(View.GONE);
            inboxHolder.ivImage.setVisibility(View.VISIBLE);

        }
    }


    public void setMyJid(String myJid) {
        this.myJid = myJid;
    }


}
