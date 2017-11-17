package com.example.alexbykov.nochat.adapter;

import android.view.View;

import com.example.alexbykov.nochat.R;
import com.example.alexbykov.nochat.data.MessageDTO;
import java.util.List;
import ru.alexbykov.nochat.BaseNoChatMailedMessageAdapter;
import ru.alexbykov.nochat.holders.NoChatBaseViewHolder;
import ru.alexbykov.nochat.holders.NoChatMailedMessageHolder;

/**
 * @author Alex Bykov
 *         Date: 13.11.2017.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 */

public class MailedMessageAdapter extends BaseNoChatMailedMessageAdapter<MessageDTO> {


    public MailedMessageAdapter(List<MessageDTO> messages) {
        super(messages);
    }


    @Override
    public void onBindViewHolder(NoChatBaseViewHolder holder, int position) {

        NoChatMailedMessageHolder mailedMessageHolder = (NoChatMailedMessageHolder) holder;
        final MessageDTO message = messages.get(position);

//        if (message.getImage() != null) {
//            mailedMessageHolder.ltImageContent.setVisibility(View.VISIBLE);
//            mailedMessageHolder.tvMailedMessage.setVisibility(View.GONE);
//            mailedMessageHolder.progressBarImage.setVisibility(View.VISIBLE);
//
//            Picasso.with(mailedMessageHolder.ivImage.getContext())
//                    .load(message.getImage())
//                    .into(mailedMessageHolder.ivImage, new Callback() {
//                        @Override
//                        public void onSuccess() {
//                            mailedMessageHolder.progressBarImage.setVisibility(View.GONE);
//                        }
//
//                        @Override
//                        public void onError() {
//                            mailedMessageHolder.progressBarImage.setVisibility(View.GONE);
//                        }
//                    });
//        }
        mailedMessageHolder.ltFileContent.setVisibility(View.VISIBLE);
        mailedMessageHolder.tvFileName.setText("Файл");
        mailedMessageHolder.tvFileSize.setText("3333");
        mailedMessageHolder.ivFileImage.setBackgroundResource(R.drawable.ic_chat_attach);


//        mailedMessageHolder.tvMailedMessage.setText(message.getText());
//        mailedMessageHolder.tvMailedMessageName.setText(message.getFrom());
//        mailedMessageHolder.tvMailedMessageDate.setText("Вторник, 22:40");
        super.onBindViewHolder(holder, position);
    }
}

