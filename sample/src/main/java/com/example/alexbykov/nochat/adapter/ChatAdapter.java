package com.example.alexbykov.nochat.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.alexbykov.nochat.R;
import com.example.alexbykov.nochat.data.MessageDTO;
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

public class ChatAdapter extends BaseChatAdapter<Object, BaseViewHolder> {


    private Context context;
    private String myJid="1";


    private static final int INBOX = 0;
    private static final int OUTBOX = 1;
    private static final int DATE = 2;

    private static final int LAYOUT_INBOX = R.layout.no_chat_inbox;
    private static final int LAYOUT_OUTBOX = R.layout.no_chat_outbox;


    public ChatAdapter() {
        messages = new ArrayList<>();
    }


    @Override
    public int getItemViewType(int position) {

        final Object message = messages.get(position);

        if (message instanceof MessageDTO) {
            if (((MessageDTO) message).getFrom().equals(myJid)) {
                return OUTBOX;
            } else return INBOX;
        } else return DATE;

    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        final int itemViewType = getItemViewType(position);

        if (itemViewType == DATE) {

        } else {
            MessageDTO message = (MessageDTO) messages.get(position);

            if (itemViewType == OUTBOX) {
                setupOutbox(message, holder);
            } else {
                setupInbox(message, holder);
            }
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
    }


    public void setMyJid(String myJid) {
        this.myJid = myJid;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;
        switch (viewType) {
            case INBOX:
                view = inflate(parent, LAYOUT_INBOX);
                return new InboxHolder(view);
            case OUTBOX:
                view = inflate(parent, LAYOUT_OUTBOX);
                return new OutboxHolder(view);
        }

        return null;
    }


}
