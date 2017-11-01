package com.example.alexbykov.nochat.presenters;

import android.os.Handler;

import com.example.alexbykov.nochat.data.MessageDTO;
import com.example.alexbykov.nochat.views.MainActivityView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.alexbykov.nochat.custom_views.MessageInputView;

/**
 * @author Alex Bykov
 *         Date: 20.10.17.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 *         <p>
 *         <p>
 *         Test sample for develop mode, it will be remove
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
        for (int i = 0; i < 100; i++) {
            MessageDTO local = new MessageDTO();
            local.setFrom("0");
            local.setMessageId(String.valueOf(i));
            local.setAuthorPhoto("https://s-cdn.sportbox.ru/images/styles/690_388/fp_fotos/3b/98/58e5db8e4cb2bffc0e4628908aa52368588ba45fa639b779902561.jpg");
            local.setFrom("Сергей Павлоc");
            local.setText("kdjfjdfjdfjldlfjkljkdflkjdfljkdfjklfdjlkldfjkkdjfjdfjdfjldlfjkljkdflkjdfljkdfjklfdjlkldfjkkdjfjdfjdfjldlfjkljkdflkjdfljkdfjklfdjlkldfjkkdjfjdfjdfjldlfjkljkdflkjdfljkdfjklfdjlkldfjkkdjfjdfjdfjldlfjkljkdflkjdfljkdfjklfdjlkldfjkkdjfjdfjdfjldlfjkljkdflkjdfljkdfjklfdjlkldfjkkdjfjdfjdfjldlfjkljkdflkjdfljkdfjklfdjlkldfjkkdjfjdfjdfjldlfjkljkdflkjdfljkdfjklfdjlkldfjkkdjfjdfjdfjldlfjkljkdflkjdfljkdfjklfdjlkldfjkkdjfjdfjdfjldlfjkljkdflkjdfljkdfjklfdjlkldfjk");
            local.setImage("https://pp.userapi.com/c840423/v840423120/1d5ff/s3NvYioLF3Q.jpg");
            messages.add(local);
        }
        return messages;
    }

    public List<Object> getOldMessages() {
        List<Object> messages = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            MessageDTO local = new MessageDTO();
            local.setFrom("0");
            local.setAuthorPhoto("https://s-cdn.sportbox.ru/images/styles/690_388/fp_fotos/3b/98/58e5db8e4cb2bffc0e4628908aa52368588ba45fa639b779902561.jpg");
            local.setMessageId(String.valueOf(i));
            local.setFrom("Сергей Павлоcdddbdhshhdfkhjdsfkjdhfhjdsfjkhdskhjfkjhkhdsjkjsfdskhjdв");
            local.setText(String.valueOf(i));
            messages.add(local);
        }
        Collections.reverse(messages);
        return messages;
    }

    public void onTopLoadMore() {
        mainActivityView.showTopProgress(true);

        new Handler().postDelayed(() -> {
            mainActivityView.showTopProgress(false);
            mainActivityView.addMessagesToTop(getOldMessages());
        }, 1000);

    }


}
