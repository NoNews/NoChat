package com.example.alexbykov.nochat.presenters;

import android.os.Handler;

import com.example.alexbykov.nochat.data.MessageDTO;
import com.example.alexbykov.nochat.views.MainActivityView;

import java.util.ArrayList;
import java.util.List;
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
        List<Object> messages = getMessages();
        mainActivityView.addMessages(messages);
    }


    @Override
    public void onClickSend(String text) {
        MessageDTO local = new MessageDTO();
        local.setFrom("1");
        local.setMessageId("id");
        local.setText(text);
//        local.setDate("25.09.1994");

        mainActivityView.newMessage(local);
    }

    public List<Object> getMessages() {

        List<Object> messages = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            MessageDTO local = new MessageDTO();
            local.setFrom("0");
            local.setMessageId(String.valueOf(i));
            local.setFrom("Сергей Павлоcdddbdhshhdfkhjdsfkjdhfhjdsfjkhdskhjfkjhkhdsjkjsfdskhjdв");
            local.setText(String.valueOf(i));
            if (i == 40) {
                local.setImage("https://stage.otvetapp.ru/media/uploads/ea/25/51/3cac184604a2fcc20548d58e60/Screen%20Shot%202017-10-26%20at%2015.31.59.jpeg");
            }
            if (i == 41) {
                local.setImage("http://kaifolog.ru/uploads/posts/2014-03/thumbs/1395283832_004.jpg");
            }
            if (i == 42) {
                local.setImage("https://vk.com/photo-24098496_456254906");
            }
            if (i == 43) {
                local.setImage("https://vk.com/photo-24098496_456267214");
            }
            local.setText("Сообщениеs www.yandex.ru vhhvchjkcvxjhkvchjkxhjkxvch www.rambler.ru jkxvchjkxcvhjkcxvjhkcvxkhjcvxkhjcvxkhjvxckjhxcvhjkkchjvxkchjvxkhcjvxkchvx " + i);
            messages.add(local);
        }
        return messages;
    }

    public void onTopLoadMore() {
        mainActivityView.showTopProgress(true);

        new Handler().postDelayed(() -> {
            mainActivityView.showTopProgress(false);
            mainActivityView.addMessagesToTop(getMessages());
        }, 2000);

    }


}
