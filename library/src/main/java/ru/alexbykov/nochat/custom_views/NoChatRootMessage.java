package ru.alexbykov.nochat.custom_views;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import ru.alexbykov.nochat.R;

/**
 * @author Alex Bykov
 *         Date: 08.12.2017.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 */

public class NoChatRootMessage extends FrameLayout {


    private View background;

    public NoChatRootMessage(Context context) {
        super(context);
    }

    public NoChatRootMessage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoChatRootMessage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public NoChatRootMessage(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }



}
