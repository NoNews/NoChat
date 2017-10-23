package ru.alexbykov.nochat;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author Alex Bykov
 *         Date: 18.10.17.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 */

public class BaseChatAdapter<М, T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {


    protected List<М> messages;
    private Runnable onScrollDownListener;
    private Runnable onScrollUpListener;
    protected RecyclerView recyclerView;

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(T holder, int position) {

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return messages == null || messages.isEmpty() ? 0 : messages.size();
    }


    public void newMessage(М t) {
        add(t, getItemCount());
    }

    public void removeMessage(М t) {

    }

    public void updateMessage(М t) {

    }

    public void updateMessages(List<М> messages) {

    }

    public void addMessages(List<М> newMessages, AddMessagesMode messagesMode) {

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
//                scrollToBottom();
                break;
        }
    }

    protected View inflate(ViewGroup parent, @LayoutRes int layout) {
        return LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
    }

    private void add(М item, int position) {
        messages.add(position, item);
        notifyItemInserted(position);
        scrollToBottom();
    }

    private void scrollToBottom() {
        final LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.getLayoutManager().scrollToPosition(manager.findLastVisibleItemPosition() + 1);
    }
//    private void scrollToBottom() {
//        final LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
//        recyclerView.getLayoutManager().scrollToPosition(manager.findLastVisibleItemPosition() + 1);
//    }

    private void remove(int position) {
        notifyItemRemoved(position);
        messages.remove(position);
    }

}
