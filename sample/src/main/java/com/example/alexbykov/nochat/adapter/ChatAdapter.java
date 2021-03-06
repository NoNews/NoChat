package com.example.alexbykov.nochat.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.alexbykov.nochat.R;
import com.example.alexbykov.nochat.data.MessageDTO;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ru.alexbykov.nochat.BaseNoChatAdapter;
import ru.alexbykov.nochat.custom_views.NoChatBubbleView;
import ru.alexbykov.nochat.holders.NoChatBaseViewHolder;
import ru.alexbykov.nochat.holders.NoChatInboxHolder;
import ru.alexbykov.nochat.holders.NoChatOutboxHolder;
import ru.alexbykov.nochat.models.NoChatProgress;

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

    private boolean isSelectMode = false;


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
                setupOutbox(position, outbox, holder);
                break;
        }
        super.onBindViewHolder(holder, position);
    }

    private void setupOutbox(int position, MessageDTO message, NoChatBaseViewHolder holder) {

        NoChatOutboxHolder outboxHolder = (NoChatOutboxHolder) holder;

        outboxHolder.ltReplyMessage.setVisibility(View.VISIBLE);
        outboxHolder.bubbleMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        if (position == getItemCount() - 1) {
            outboxHolder.bubbleMessage.setMode(NoChatBubbleView.Mode.WITH_ANGLE);
        } else {
            outboxHolder.bubbleMessage.setMode(NoChatBubbleView.Mode.WITHOUT_ANGLE);
        }

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

        holder.viewBackground.setSelected(false);

//        outboxHolder.tvDate.setText(message.getDate());
    }

    private void setupInbox(MessageDTO message, NoChatBaseViewHolder holder) {
        NoChatInboxHolder inboxHolder = (NoChatInboxHolder) holder;
        inboxHolder.tvMessage.setText(message.getText());
        inboxHolder.tvName.setText(message.getFrom());

        inboxHolder.bubbleMessage.setMode(NoChatBubbleView.Mode.WITH_ANGLE);

        inboxHolder.bubbleMessage.setOnClickListener(v -> {

        });


        inboxHolder.ltImageContent.setOnClickListener(v -> {

        });
        if (message.getImage() != null) {
            inboxHolder.ltImageContent.setVisibility(View.VISIBLE);
            inboxHolder.tvMessage.setVisibility(View.GONE);
            inboxHolder.progressBarImage.setVisibility(View.VISIBLE);

            Picasso.with(inboxHolder.ivImage.getContext())
                    .load(message.getImage())
                    .into(inboxHolder.ivImage, new Callback() {
                        @Override
                        public void onSuccess() {
                            inboxHolder.progressBarImage.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            inboxHolder.progressBarImage.setVisibility(View.GONE);
                        }
                    });
        }
//        inboxHolder.ltFileContent.setVisibility(View.VISIBLE);
//        inboxHolder.tvFileName.setText("Файл");
//        inboxHolder.tvFileSize.setText("3333");
//        inboxHolder.ivFileImage.setBackgroundResource(R.drawable.ic_chat_attach);

        ((NoChatInboxHolder) holder).civProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PHOTO", "onClick: ");
            }
        });


        holder.tvDate.setText("10:33");

//        if (message.getLinkedMessages() != null) {
//            holder.ltMailedMessages.setVisibility(View.VISIBLE);
//            MailedMessageAdapter adapter = new MailedMessageAdapter(message.getLinkedMessages());
//            adapter.setHasStableIds(true);
//            holder.rvMailedMessages.setLayoutManager(new LinearLayoutManager(context));
//            holder.rvMailedMessages.setHasFixedSize(true);
//            holder.rvMailedMessages.setNestedScrollingEnabled(false);
//            holder.rvMailedMessages.setAdapter(adapter);
//            holder.rvMailedMessages.setRecycledViewPool(recycledViewPool);
//        } else {
//            holder.ltMailedMessages.setVisibility(View.GONE);
//        }
//
//        holder.viewBackground.setSelected(false);
//
//        if (message.isBubbleWithAngle()) {
//            holder.bubbleMessage.setMode(NoChatBubbleView.Mode.WITH_ANGLE);
//        } else {
//            holder.bubbleMessage.setMode(NoChatBubbleView.Mode.WITHOUT_ANGLE);
//        }
//
//
//        inboxHolder.rootMessage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isSelectMode) {
//                    message.setSelected(!message.getSelected());
//                    inboxHolder.viewBackground.setSelected(message.getSelected());
//                }
//            }
//        });
//
//        inboxHolder.rootMessage.setOnLongClickListener(v -> {
//            if (!isSelectMode) {
//                isSelectMode = true;
//                message.setSelected(true);
//                inboxHolder.viewBackground.setSelected(true);
//                return true;
//            }
//            return false;
//        });

        holder.viewBackground.setSelected(message.getSelected());
        holder.bubbleMessage.setMode(NoChatBubbleView.Mode.WITHOUT_ANGLE);
        holder.tvDate.setVisibility(View.GONE);

//        holder.bubbleMessage.setMode(NoChatBubbleView.Mode.WITH_ANGLE, true);

    }

    private void setupLinkedMessagesAdapter() {

    }


    @Override
    public void newMessage(Object message) {
        super.newMessage(message);
        MessageDTO lastMessage = (MessageDTO) getPreLastMessage();
        lastMessage.setBubbleWithAngle(false);
        changePreLastMessage();
    }

    public void setMyJid(String myJid) {
        this.myJid = myJid;
    }


}
