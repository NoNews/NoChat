package ru.alexbykov.nochat;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * @author Alex Bykov
 *         Date: 18.10.17.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 */

public class ChatRecyclerView extends RecyclerView {


    public ChatRecyclerView(Context context) {
        super(context);
    }

    public ChatRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ChatRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
