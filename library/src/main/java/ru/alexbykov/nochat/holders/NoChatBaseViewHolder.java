package ru.alexbykov.nochat.holders;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import ru.alexbykov.nochat.R;
import ru.alexbykov.nochat.custom_views.NoChatBubbleView;
import ru.alexbykov.nochat.custom_views.NoChatRootMessage;
import ru.alexbykov.nochat.custom_views.NoChatSelectView;

/**
 * @author Alex Bykov
 *         Date: 20.10.17.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 */

public class NoChatBaseViewHolder extends RecyclerView.ViewHolder {


    public TextView tvFileName;
    public TextView tvFileSize;
    public ImageView ivFileImage;
    public LinearLayout ltFileContent;

    public TextView tvMessage;
    public TextView tvDate;

    public ImageView ivImage;
    public NoChatRootMessage rootMessage;
    public NoChatBubbleView bubbleMessage;
    public FrameLayout ltImageContent;
    public ProgressBar progressBarImage;
    public LinearLayout ltMailedMessages;
    public RecyclerView rvMailedMessages;
    public LinearLayout ltReplyMessage;
    public TextView tvReplyMessage;
    public NoChatSelectView viewBackground;

    public NoChatBaseViewHolder(View itemView) {
        super(itemView);
    }


    protected final void bindBubbleViews() {
        ltMailedMessages = bindView(R.id.lt_mailed_messages);
        rvMailedMessages = bindView(R.id.rv_mailed_messages);
        ltReplyMessage = bindView(R.id.lt_reply_message);
        tvReplyMessage = bindView(R.id.tv_reply_message);
        viewBackground = bindView(R.id.view_background);
        bindImage();
        bindFile();
        bindMessage();
        tvDate = bindView(R.id.tv_date);
    }

    protected final void bindMailedMessageHolder() {
        bindFile();
        bindImage();
        bindMessage();
    }

    private void bindMessage() {
        tvMessage = bindView(R.id.tv_message);
    }

    private void bindImage() {
        ivImage = bindView(R.id.iv_image);
        rootMessage = bindView(R.id.root_message);
        bubbleMessage = bindView(R.id.lt_message);
        ltImageContent = bindView(R.id.lt_image_content);
        progressBarImage = bindView(R.id.progress_bar_image);
    }

    private void bindFile() {
        tvFileName = bindView(R.id.tv_file_name);
        ivFileImage = bindView(R.id.iv_file_image);
        tvFileSize = bindView(R.id.tv_file_size);
        ltFileContent = bindView(R.id.lt_file_content);
    }


    @SuppressWarnings("unchecked")
    protected final <T extends View> T bindView(@IdRes int id) {
        return (T) itemView.findViewById(id);
    }

}
