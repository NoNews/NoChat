package com.example.alexbykov.nochat.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.alexbykov.nochat.R;
import com.example.alexbykov.nochat.presenters.MainActivityPresenter;
import com.example.alexbykov.nochat.views.MainActivityView;

import ru.alexbykov.nochat.custom_views.MessageInputView;

public class MainActivity extends AppCompatActivity implements MainActivityView {


    private int LAYOUT = R.layout.activity_main;


    private MainActivityPresenter mainActivityPresenter;
    private MessageInputView ltInput;


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
    }
}
