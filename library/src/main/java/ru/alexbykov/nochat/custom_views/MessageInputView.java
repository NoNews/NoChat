package ru.alexbykov.nochat.custom_views;

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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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


    private OnSendClickListener sendClickListener;
    private Runnable onAttachmentsClick;
    private RelativeLayout rootView;
    private ImageView ivSend;
    private ImageView ivAttachments;
    private EditText etInput;

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


    private void setupUI() {
        bindViews();
        setupListeners();
        hideInput();
    }

    private void setupListeners() {
        ivSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                final String text = etInput.getText().toString();
                if (!NoChatStringUtils.isEmpty(text)) {
                    sendClickListener.onClickSend(text);
                }
                clearInput();
            }
        });
        ivAttachments.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (NoChatStringUtils.isEmpty(s.toString())) {
                    hideInput();
                } else {
                    showInput();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void clearInput() {
        etInput.getText().clear();
    }

    private void hideInput() {

        if (ivSend.getVisibility() == VISIBLE) {
            ivAttachments.setVisibility(VISIBLE);
            ivSend.setVisibility(GONE);
        }
    }

    private void showInput() {
        if (ivSend.getVisibility() != VISIBLE) {
            ivAttachments.setVisibility(GONE);
            ivSend.setVisibility(VISIBLE);
        }
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


    public void unsubscribe() {
        sendClickListener = null;
        onAttachmentsClick = null;
        etInput.addTextChangedListener(null);
    }


    public interface OnSendClickListener {
        void onClickSend(String text);
    }

}
