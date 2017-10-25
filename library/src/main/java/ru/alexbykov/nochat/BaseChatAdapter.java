package ru.alexbykov.nochat;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.alexbykov.nochat.holders.BaseViewHolder;
import ru.alexbykov.nochat.holders.InboxHolder;
import ru.alexbykov.nochat.holders.OutboxHolder;
import ru.alexbykov.nochat.models.NoChatProgress;
import ru.alexbykov.nochat.utils.NoChatScrollUtils;

/**
 * @author Alex Bykov
 *         Date: 18.10.17.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 */

public abstract class BaseChatAdapter<M> extends RecyclerView.Adapter<BaseViewHolder> {


    protected List<M> messages;
    protected RecyclerView recyclerView;
    private Runnable topListener;
    private Runnable bottomListener;
    private int loadingTriggerThreshold = 2;


    private static final int LAYOUT_INBOX = R.layout.no_chat_inbox;
    private static final int LAYOUT_OUTBOX = R.layout.no_chat_outbox;
    private static final int LAYOUT_PROGRESS = R.layout.no_chat_progress;

    protected static final int VIEW_TYPE_INBOX = 0;
    protected static final int VIEW_TYPE_OUTBOX = 1;
    protected static final int VIEW_TYPE_DATE = 2;
    protected static final int VIEW_TYPE_PROGRESS = 3;

    private final NoChatProgress noChatProgress = new NoChatProgress();


    private AdapterState adapterState = AdapterState.ON_BOTTOM;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;
        switch (viewType) {
            case VIEW_TYPE_INBOX:
                view = inflate(parent, LAYOUT_INBOX);
                return new InboxHolder(view);
            case VIEW_TYPE_OUTBOX:
                view = inflate(parent, LAYOUT_OUTBOX);
                return new OutboxHolder(view);
            case VIEW_TYPE_PROGRESS:
                view = inflate(parent, LAYOUT_PROGRESS);
                return new BaseViewHolder(view);
        }

        return null;
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

    }


    public void onBottom(Runnable bottomListener) {
        this.bottomListener = bottomListener;
    }

    public void onTop(Runnable topListener) {
        this.topListener = topListener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (NoChatScrollUtils.isOnBottom(recyclerView, loadingTriggerThreshold)) {
                    bottomListener.run();

                } else if (NoChatScrollUtils.isOnTop(recyclerView, loadingTriggerThreshold)) {
                    topListener.run();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return messages == null || messages.isEmpty() ? 0 : messages.size();
    }


    public void newMessage(M t) {
        addToBottomWithScroll(t, getItemCount());
    }

    public void removeMessage(M t) {

    }

    public void updateMessage(M t) {

    }

    public void updateMessages(List<M> messages) {

    }

    public void addMessages(List<M> newMessages, AddMessagesMode messagesMode) {
        switch (messagesMode) {
            case TO_END:
                addAll(newMessages, messages.size() - 1);
                break;
            case TO_START:
                addAll(newMessages, 0);
                break;
            case INSTEAD_ALL:
                this.messages.clear();
                this.messages.addAll(newMessages);
                notifyDataSetChanged();
                break;
        }
    }

    protected View inflate(ViewGroup parent, @LayoutRes int layout) {
        return LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
    }

    private void addToBottomWithScroll(M item, int position) {
        messages.add(position, item);
        notifyItemInserted(position);
        if (NoChatScrollUtils.isOnBottom(recyclerView, 1)) {
            NoChatScrollUtils.scrollToLastVisible(recyclerView);
        } else {
            NoChatScrollUtils.fullScrollToBottom(this, recyclerView);
        }
    }

    private void add(M item, int position) {
        messages.add(position, item);
        notifyItemInserted(position);
    }

    private void addAll(List<M> items, int position) {
        for (M item : items) {
            add(item, position);
        }
    }

    @SuppressWarnings("unchecked")
    public void showBottomProgress(boolean show) {
        if (show && adapterState != AdapterState.IN_PROGRESS) {
            messages.add((M) noChatProgress);
            adapterState = AdapterState.IN_PROGRESS;
            notifyItemInserted(getItemCount() - 1);
        } else {
            final int lastItemPosition = messages.size() - 1;
            notifyItemRemoved(lastItemPosition);
            messages.remove(lastItemPosition);
            setAdapterState();
        }
    }

    private void setAdapterState() {
        if (NoChatScrollUtils.isOnBottom(recyclerView, loadingTriggerThreshold)) {
            adapterState = AdapterState.ON_BOTTOM;
        } else if (NoChatScrollUtils.isOnTop(recyclerView, loadingTriggerThreshold)) {
            adapterState = AdapterState.ON_TOP;
        } else adapterState = AdapterState.SCROLLING_OR_MIDDLE;
    }

    @SuppressWarnings("unchecked")
    public void showTopProgress(boolean show) {
        if (show && adapterState != AdapterState.IN_PROGRESS) {
            messages.add(0, (M) noChatProgress);
            adapterState = AdapterState.IN_PROGRESS;
            notifyItemInserted(0);
        } else {
            notifyItemRemoved(0);
            messages.remove(0);
            setAdapterState();
        }
    }

    private void remove(int position) {
        notifyItemRemoved(position);
        messages.remove(position);
    }

}
