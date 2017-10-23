package com.example.alexbykov.nochat.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.alexbykov.nochat.R;
import com.example.alexbykov.nochat.adapter.ChatAdapter;
import com.example.alexbykov.nochat.presenters.MainActivityPresenter;
import com.example.alexbykov.nochat.views.MainActivityView;

import ru.alexbykov.nochat.custom_views.MessageInputView;

public class MainActivity extends AppCompatActivity implements MainActivityView {


    private final int LAYOUT = R.layout.activity_main;


    private ChatAdapter chatAdapter;
    private MainActivityPresenter mainActivityPresenter;
    private MessageInputView ltInput;
    private RecyclerView rvChat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityPresenter = new MainActivityPresenter(this);
        setContentView(LAYOUT);
        setupUI();
        setupUX();

    }

    private void setupUI() {
        getWindow().setBackgroundDrawableResource(R.drawable.back);
        bindViews();
        setupChatAdapter();
    }

    private void setupChatAdapter() {
        chatAdapter = new ChatAdapter();
        rvChat.setLayoutManager(new LinearLayoutManager(this));
        rvChat.setAdapter(chatAdapter);
    }


    private void setupUX() {
        ltInput.onSendClick(mainActivityPresenter);
        ltInput.onAttachmentsClick(new Runnable() {
            @Override
            public void run() {

            }
        });
    }


    private void bindViews() {
        ltInput = findViewById(R.id.lt_input);
        rvChat = findViewById(R.id.rv_chat);
    }

    @Override
    public void newMessage(Object message) {
        chatAdapter.newMessage(message);
    }

    @Override
    public void removeMessage(Object message) {
        chatAdapter.removeMessage(message);
    }

    @Override
    public void updateMessage(Object message) {
        chatAdapter.updateMessage(message);
    }
}
