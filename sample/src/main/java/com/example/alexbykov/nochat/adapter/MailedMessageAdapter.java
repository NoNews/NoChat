package com.example.alexbykov.nochat.adapter;

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
        mailedMessageHolder.tvMailedMessage.setText(message.getText());
        mailedMessageHolder.tvMailedMessageName.setText(message.getFrom());
        mailedMessageHolder.tvMailedMessageDate.setText("Вторник, 22:40");
        super.onBindViewHolder(holder, position);
    }
}
