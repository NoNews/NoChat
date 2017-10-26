package ru.alexbykov.nochat;

/**
 * @author Alex Bykov
 *         Date: 24.10.17.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 */

public enum NoChatAdapterState {

    IN_PROGRESS,
    ON_BOTTOM, //user is on bottom
    ON_TOP,
    SCROLLING_UP,
    SCROLLING_DOWN,
    SCROLLING_OR_MIDDLE //we don't know

}
