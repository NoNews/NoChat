package ru.alexbykov.nochat.custom_views;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;

/**
 * @author Alex Bykov
 *         Date: 01.12.2017.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 */

public class BubbleView extends FrameLayout {


    private Mode mode;

    public BubbleView(@NonNull Context context) {
        super(context);
    }

    public BubbleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BubbleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BubbleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public enum Mode {
        WITHOUT_ANGLE, WITH_ANGLE
    }

}
