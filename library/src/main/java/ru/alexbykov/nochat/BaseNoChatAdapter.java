package ru.alexbykov.nochat;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.alexbykov.nochat.holders.NoChatBaseViewHolder;
import ru.alexbykov.nochat.holders.NoChatInboxHolder;
import ru.alexbykov.nochat.holders.NoChatOutboxHolder;
import ru.alexbykov.nochat.models.NoChatProgress;
import ru.alexbykov.nochat.utils.NoChatScrollUtils;

/**
 * @author Alex Bykov
 *         Date: 18.10.17.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 */

public abstract class BaseNoChatAdapter<M> extends RecyclerView.Adapter<NoChatBaseViewHolder> {


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

    private boolean noMoreTopData;


    private NoChatAdapterState adapterState = NoChatAdapterState.ON_BOTTOM;

    @Override
    public NoChatBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;
        switch (viewType) {
            case VIEW_TYPE_INBOX:
                view = inflate(parent, LAYOUT_INBOX);
                return new NoChatInboxHolder(view);
            case VIEW_TYPE_OUTBOX:
                view = inflate(parent, LAYOUT_OUTBOX);
                return new NoChatOutboxHolder(view);
            case VIEW_TYPE_PROGRESS:
                view = inflate(parent, LAYOUT_PROGRESS);
                return new NoChatBaseViewHolder(view);
        }

        return null;
    }


    @Override
    public void onBindViewHolder(NoChatBaseViewHolder holder, int position) {

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
                if (NoChatScrollUtils.isOnTop(recyclerView, loadingTriggerThreshold)) {
                    if (adapterState != NoChatAdapterState.IN_PROGRESS) {
                        topListener.run();
                    }
                } else if (NoChatScrollUtils.isOnBottom(recyclerView, loadingTriggerThreshold)) {
                    if (adapterState != NoChatAdapterState.IN_PROGRESS) {
                        bottomListener.run();
                    }
                }
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

    public void addMessages(List<M> newMessages, NoChatAddMessagesMode messagesMode) {
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

    private void setAdapterState() {
        if (NoChatScrollUtils.isOnBottom(recyclerView, loadingTriggerThreshold)) {
            adapterState = NoChatAdapterState.ON_BOTTOM;
        } else if (NoChatScrollUtils.isOnTop(recyclerView, loadingTriggerThreshold)) {
            adapterState = NoChatAdapterState.ON_TOP;
        } else adapterState = NoChatAdapterState.SCROLLING_OR_MIDDLE;
    }

    @SuppressWarnings("unchecked")
    public void showBottomProgress(boolean show) {
        if (show && adapterState != NoChatAdapterState.IN_PROGRESS) {
            add((M) noChatProgress, getItemCount() - 1);
            adapterState = NoChatAdapterState.IN_PROGRESS;
        } else {
            remove(messages.size() - 1);
            setAdapterState();
        }
    }

    @SuppressWarnings("unchecked")
    public void showTopProgress(boolean show) {
        if (show && adapterState != NoChatAdapterState.IN_PROGRESS) {
            add((M) noChatProgress, 0);
            adapterState = NoChatAdapterState.IN_PROGRESS;
        } else {
            remove(0);
            setAdapterState();
        }
    }


    public void setTopNoMoreData(boolean noMoreData) {
        this.noMoreTopData = noMoreData;
    }

    private void remove(int position) {
        notifyItemRemoved(position);
        messages.remove(position);
    }


    public void unsubscribe() {
        messages = null;
        topListener = null;
        bottomListener = null;
        recyclerView.addOnScrollListener(null);
        recyclerView = null;
    }
}
