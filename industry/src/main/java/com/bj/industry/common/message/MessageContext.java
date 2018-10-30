package com.bj.industry.common.message;

public class MessageContext {

    public static String message(int key) {
        return MessageHolder.getInstance().getMessage(key);
    }

}
