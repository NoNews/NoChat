package ru.alexbykov.nochat.holders;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ru.alexbykov.nochat.R;

/**
 * @author Alex Bykov
 *         Date: 20.10.17.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 */

public  class NoChatBaseViewHolder extends RecyclerView.ViewHolder {


    public TextView tvFileName;
    public TextView tvFileSize;
    public ImageView ivFileImage;
    public LinearLayout ltFileContent;

    public TextView tvMessage;
    public TextView tvDate;

    public ImageView ivImage;
    public RelativeLayout ltMessageRoot;
    public LinearLayout ltMessage;
    public FrameLayout ltImageContent;
    public ProgressBar progressBarImage;
    public LinearLayout ltMailedMessages;
    public RecyclerView rvMailedMessages;

    public NoChatBaseViewHolder(View itemView) {
        super(itemView);
    }


    protected final void bindBubbleViews() {
        ltMailedMessages = bindView(R.id.lt_mailed_messages);
        rvMailedMessages = bindView(R.id.rv_mailed_messages);
        bindFile();
        bindMessage();
        tvDate = bindView(R.id.tv_date);
        bindImage();
        progressBarImage = bindView(R.id.progress_bar_image);
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
        ltMessageRoot = bindView(R.id.lt_message_root);
        ltMessage = bindView(R.id.lt_message);
        ltImageContent = bindView(R.id.lt_image_content);
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
