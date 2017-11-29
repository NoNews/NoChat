package com.example.alexbykov.nochat.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.alexbykov.nochat.R;
import com.example.alexbykov.nochat.data.MessageDTO;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import ru.alexbykov.nochat.holders.NoChatInboxHolder;
import ru.alexbykov.nochat.models.NoChatProgress;
import ru.alexbykov.nochat.holders.NoChatBaseViewHolder;
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

    private RecyclerView.RecycledViewPool recycledViewPool;


    public ChatAdapter() {
        messages = new ArrayList<>();
        recycledViewPool = new RecyclerView.RecycledViewPool();
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

        outboxHolder.ltReplyMessage.setVisibility(View.VISIBLE);

        final String text = message.getText();
        if (text.length() > 2 && text.charAt(0) == '>' && text.charAt(1) == ' ') {
            final String[] textArray = text.split("\n");
            final String replyMessage = textArray[0];
            outboxHolder.tvReplyMessage.setText(replyMessage.substring(1));
            final String userText = text.substring(replyMessage.length() + 1);
            outboxHolder.tvMessage.setText(userText);
        } else {
            outboxHolder.ltReplyMessage.setVisibility(View.GONE);
            outboxHolder.tvMessage.setText(message.getText());
        }

//        outboxHolder.tvDate.setText(message.getDate());
    }

    private void setupInbox(MessageDTO message, NoChatBaseViewHolder holder) {
        NoChatInboxHolder inboxHolder = (NoChatInboxHolder) holder;
        inboxHolder.tvMessage.setText(message.getText());
        inboxHolder.tvName.setText(message.getFrom());

        inboxHolder.ltImageContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
//        if (message.getImage() != null) {
//            inboxHolder.ltImageContent.setVisibility(View.VISIBLE);
//            inboxHolder.tvMessage.setVisibility(View.GONE);
//            inboxHolder.progressBarImage.setVisibility(View.VISIBLE);
//
//            Picasso.with(inboxHolder.ivImage.getContext())
//                    .load(message.getImage())
//                    .into(inboxHolder.ivImage, new Callback() {
//                        @Override
//                        public void onSuccess() {
//                            inboxHolder.progressBarImage.setVisibility(View.GONE);
//                        }
//
//                        @Override
//                        public void onError() {
//                            inboxHolder.progressBarImage.setVisibility(View.GONE);
//                        }
//                    });
//        }
//        inboxHolder.ltFileContent.setVisibility(View.VISIBLE);
//        inboxHolder.tvFileName.setText("Файл");
//        inboxHolder.tvFileSize.setText("3333");
//        inboxHolder.ivFileImage.setBackgroundResource(R.drawable.ic_chat_attach);

        holder.tvDate.setText("10:33");

        if (message.getLinkedMessages() != null) {
            holder.ltMailedMessages.setVisibility(View.VISIBLE);
            MailedMessageAdapter adapter = new MailedMessageAdapter(message.getLinkedMessages());
            adapter.setHasStableIds(true);
            holder.rvMailedMessages.setLayoutManager(new LinearLayoutManager(context));
            holder.rvMailedMessages.setHasFixedSize(true);
            holder.rvMailedMessages.setNestedScrollingEnabled(false);
            holder.rvMailedMessages.setAdapter(adapter);
            holder.rvMailedMessages.setRecycledViewPool(recycledViewPool);
        } else {
            holder.ltMailedMessages.setVisibility(View.GONE);
        }

    }

    private void setupLinkedMessagesAdapter() {

    }


    public void setMyJid(String myJid) {
        this.myJid = myJid;
    }


}
