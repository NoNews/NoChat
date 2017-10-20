package com.example.alexbykov.nochat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.alexbykov.nochat.custom_views.MessageInputView;

public class MainActivity extends AppCompatActivity {


    private int LAYOUT = R.layout.activity_main;

    private MessageInputView ltInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        setupUI();
        setupUX();

    }

    private void setupUI() {
        getWindow().setBackgroundDrawableResource(R.drawable.back);
        ltInput = findViewById(R.id.lt_input);
    }

    private void setupUX() {
        ltInput.onSendClick(new MessageInputView.OnSendClickListener() {
            @Override
            public void onClickSend(String text) {
                
            }
        });

        ltInput.onAttachmentsClick(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
