package ru.alexbykov.nochat;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author Alex Bykov
 *         Date: 18.10.17.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 */

public class BaseChatAdapter<М, T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {


    private List<T> messages;
    private Runnable onScrollDownListener;
    private Runnable onScrollUpListener;

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(T holder, int position) {

    }

    @Override
    public int getItemCount() {
        return messages == null || messages.isEmpty() ? 0 : messages.size();
    }


    public void newMessage(T t) {
        add(t, getItemCount());
    }

    public void removeMessage(T t) {

    }

    public void updateMessage(T t) {

    }

    public void updateMessages(List<T> messages) {

    }

    public void addMessages(List<T> newMessages, AddMessagesMode messagesMode) {

        switch (messagesMode) {
            case TO_END:
                this.messages.addAll(newMessages);
                notifyDataSetChanged();
                break;
            case TO_START:
                this.messages.addAll(0, newMessages);
                notifyDataSetChanged();
                break;
            case INSTEAD_ALL:
                this.messages.clear();
                this.messages.addAll(newMessages);
                notifyDataSetChanged();
                break;
        }
    }


    private void add(T item, int position) {
        messages.add(position, item);
        notifyItemInserted(position);
    }

    private void remove(int position) {
        notifyItemRemoved(position);
        messages.remove(position);
    }
}
