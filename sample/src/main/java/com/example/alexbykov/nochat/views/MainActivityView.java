package com.example.alexbykov.nochat.views;

import java.util.List;

/**
 * @author Alex Bykov
 *         Date: 20.10.17.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 */

public interface MainActivityView {


    void newMessage(Object message);
    void removeMessage(Object message);
    void updateMessage(Object message);
    void addMessages(List<Object> messages);
}
