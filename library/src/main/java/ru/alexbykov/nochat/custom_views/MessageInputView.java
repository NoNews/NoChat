package ru.alexbykov.nochat.custom_views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.List;

import ru.alexbykov.nochat.R;
import ru.alexbykov.nochat.utils.NoChatStringUtils;

/**
 * @author Alex Bykov
 *         Date: 18.10.17.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 */

public class MessageInputView extends FrameLayout {


    private static int LAYOUT = R.layout.no_chat_message_input;
    private static int ANIMATION_DURATION = 250;

    private OnSendClickListener sendClickListener;
    private Runnable onAttachmentsClick;
    private RelativeLayout rootView;
    private ImageView ivSend;
    private ImageView ivAttachments;
    public AutoCompleteTextView etInput;
    private boolean isAutoHideInputButtonEnabled = true;


    public MessageInputView(@NonNull Context context) {
        super(context);
        setupUI();
    }

    public MessageInputView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setupUI();
    }

    public MessageInputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupUI();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MessageInputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setupUI();
    }

    public void setupAutoCompleteFields(List<String> autoCompleteFields) {
        etInput.setAdapter(new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line, autoCompleteFields));
    }


    private void setupUI() {
        bindViews();
        setupListeners();
        ivSend.setVisibility(GONE);
    }

    private void setupListeners() {
        ivSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                final String text = etInput.getText().toString().trim();
//                if (!NoChatStringUtils.isEmpty(text)) {
//                    sendClickListener.onClickSend(text);
//                }
                sendClickListener.onClickSend(text);
                clearInput();
            }
        });
        ivAttachments.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onAttachmentsClick.run();
            }
        });

        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isAutoHideInputButtonEnabled) {
                    checkTextChanged(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void checkTextChanged(CharSequence s) {

        if (NoChatStringUtils.isEmpty(s.toString())) {
            hideInput(true);
        } else {
            showInput(true);
        }
    }

    private void clearInput() {
        etInput.getText().clear();
    }

    public void hideInput(boolean animate) {

        if (ivSend.getVisibility() == VISIBLE) {
            if (animate) {
                animateHide(ivSend);
                animateShow(ivAttachments);
            } else {
                hideView(ivSend);
                showView(ivAttachments);
            }
        }
    }

    public void setAutoHideInputButtonEnabled(boolean isAutoHideInputButtonEnabled) {
        if (this.isAutoHideInputButtonEnabled != isAutoHideInputButtonEnabled) {

            this.isAutoHideInputButtonEnabled = isAutoHideInputButtonEnabled;
            if (!this.isAutoHideInputButtonEnabled) {
                showInput(false);
            } else {
                checkTextChanged(etInput.getText());
            }
        }
    }

    public void showInput(boolean animate) {
        if (ivSend.getVisibility() != VISIBLE) {
            if (animate) {
                animateHide(ivAttachments);
                animateShow(ivSend);
            } else {
                hideView(ivAttachments);
                showView(ivSend);
            }
        }
    }

    private void showView(View view) {
        view.setVisibility(VISIBLE);
    }

    private void hideView(View view) {
        view.setVisibility(GONE);
    }

    private void bindViews() {
        rootView = (RelativeLayout) LayoutInflater.from(getContext()).inflate(LAYOUT, null);
        addView(rootView);
        ivSend = rootView.findViewById(R.id.iv_send);
        ivAttachments = rootView.findViewById(R.id.iv_attachments);
        etInput = rootView.findViewById(R.id.et_input);
    }

    public void onSendClick(OnSendClickListener sendClickListener) {
        this.sendClickListener = sendClickListener;
    }

    public void onAttachmentsClick(Runnable onAttachmentsClick) {
        this.onAttachmentsClick = onAttachmentsClick;
    }


    private void animateHide(final View view) {
        view.animate()
                .scaleX(0f)
                .scaleY(0f)
                .alpha(0f)
                .setDuration(ANIMATION_DURATION)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationCancel(Animator animation) {
                        view.clearAnimation();
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(GONE);
                        view.clearAnimation();
                    }
                });
    }

    private void animateShow(final View view) {
        view.animate()
                .scaleX(1f)
                .scaleY(1f)
                .alpha(1f)
                .setDuration(ANIMATION_DURATION)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(VISIBLE);
                        view.clearAnimation();
                    }
                });
    }


    public void unbind() {
        sendClickListener = null;
        onAttachmentsClick = null;
        ivAttachments.setOnClickListener(null);
        ivSend.setOnClickListener(null);
        etInput.addTextChangedListener(null);
    }


    public interface OnSendClickListener {
        void onClickSend(String text);
    }

}
