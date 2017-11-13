package ru.alexbykov.nochat;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.alexbykov.nochat.holders.NoChatBaseViewHolder;
import ru.alexbykov.nochat.holders.NoChatMailedMessageHolder;

/**
 * @author Alex Bykov
 *         Date: 13.11.2017.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 */

public abstract class BaseNoChatMailedMessageAdapter<M> extends BaseNoChatAdapter<M> {


    private static final int LAYOUT_MAILED_MESSAGE = R.layout.no_chat_item_mailed_message;

    public BaseNoChatMailedMessageAdapter(List<M> messages) {
        this.messages = new ArrayList<>();
        this.messages.addAll(messages);
    }

    @Override
    public NoChatBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = inflate(parent, LAYOUT_MAILED_MESSAGE);
        return new NoChatMailedMessageHolder(view);
    }


}
