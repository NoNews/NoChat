package ru.alexbykov.nochat.utils;

import android.text.TextUtils;

/**
 * @author Alex Bykov
 *         Date: 20.10.17.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 */

public class NoChatStringUtils {

    private NoChatStringUtils() {
    }


    public static boolean isEmpty(String text) {
        return (TextUtils.isEmpty(text) || isOnlySpaces(text));
    }

    private static boolean isOnlySpaces(String text) {
        return (text.trim().length() == 0);
    }

}
