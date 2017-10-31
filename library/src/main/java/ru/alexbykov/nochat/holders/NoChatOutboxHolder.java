package ru.alexbykov.nochat.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ru.alexbykov.nochat.R;


/**
 * @author Alex Bykov
 *         Date: 20.10.17.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 */

public class NoChatOutboxHolder extends NoChatBaseViewHolder {


    public NoChatOutboxHolder(View itemView) {
        super(itemView);
        bindBubbleViews();
    }
}
