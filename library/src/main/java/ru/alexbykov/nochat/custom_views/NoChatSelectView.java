package ru.alexbykov.nochat.custom_views;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import ru.alexbykov.nochat.R;

/**
 * @author Alex Bykov
 *         Date: 10.12.2017.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 */

public class NoChatSelectView extends View {
    public NoChatSelectView(Context context) {
        super(context);
        setup();
    }

    public NoChatSelectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public NoChatSelectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public NoChatSelectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setup();
    }

    private void setup() {
        setBackgroundColor(getSelectedColor());
        setVisibility(GONE);
    }


    public void setSelected(boolean selected) {
        if (selected) {
            setVisibility(VISIBLE);
        } else {
            setVisibility(GONE);
        }
    }

    private int getSelectedColor() {
        return ContextCompat.getColor(getContext(), R.color.no_chat_blue_transparent);
    }
}
