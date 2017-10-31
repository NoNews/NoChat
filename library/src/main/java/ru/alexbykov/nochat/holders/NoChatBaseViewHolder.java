package ru.alexbykov.nochat.holders;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    public RelativeLayout ltMessageRoot;
    public LinearLayout ltMessage;


    public NoChatBaseViewHolder(View itemView) {
        super(itemView);
    }

    @SuppressWarnings("unchecked")
    protected final <T extends View> T bindView(@IdRes int id) {
        return (T) itemView.findViewById(id);
    }
}
