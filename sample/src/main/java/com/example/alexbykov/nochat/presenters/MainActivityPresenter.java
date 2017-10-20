package com.example.alexbykov.nochat.presenters;

import com.example.alexbykov.nochat.activities.MainActivity;
import com.example.alexbykov.nochat.views.MainActivityView;

import ru.alexbykov.nochat.custom_views.MessageInputView;

/**
 * @author Alex Bykov
 *         Date: 20.10.17.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 */

public class MainActivityPresenter implements MessageInputView.OnSendClickListener {

    private MainActivityView mainActivityView;


    public MainActivityPresenter(MainActivityView mainActivityView) {
        this.mainActivityView = mainActivityView;
    }



    @Override
    public void onClickSend(String text) {

    }
}
