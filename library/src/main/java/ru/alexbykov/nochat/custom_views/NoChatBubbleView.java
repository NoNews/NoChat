package ru.alexbykov.nochat.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import ru.alexbykov.nochat.R;

/**
 * @author Alex Bykov
 *         Date: 01.12.2017.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 */

public class NoChatBubbleView extends LinearLayout {


    private Mode mode;
    private boolean isInbox = false;

    public NoChatBubbleView(@NonNull Context context) {
        super(context);
    }

    public NoChatBubbleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setupUI(context, attrs);
    }

    public NoChatBubbleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupUI(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public NoChatBubbleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setupUI(context, attrs);
    }


    private void setupUI(Context context, AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.NoChatBubbleView, 0, 0);
        this.isInbox = a.getBoolean(R.styleable.NoChatBubbleView_is_inbox, false);
    }

    public void setMode(Mode mode) {
        setBackgroundResource(getBackgroundByMode(mode));
    }

    private int getBackgroundByMode(Mode mode) {
        return mode == Mode.WITH_ANGLE ? getBackgroundWithAngle() : getBackgroundWithoutAngle();
    }

    @DrawableRes
    private int getBackgroundWithoutAngle() {
        return isInbox ? R.drawable.bubble_inbox_without_angle : R.drawable.bubble_outbox_without_angle;
    }

    @DrawableRes
    private int getBackgroundWithAngle() {
        return isInbox ? R.drawable.bubble_inbox_with_angle : R.drawable.bubble_outbox_with_angle;

    }

    public enum Mode {
        WITHOUT_ANGLE, WITH_ANGLE
    }

}
