package com.bj.industry.common.message;

import java.util.HashMap;
import java.util.Map;

public final class MessageHolder {
    /**
     * 单例
     */
    private static final MessageHolder holder = new MessageHolder();

    /**
     * 消息映射
     * code--message
     */
    private Map<Integer, String> codeMessages = new HashMap<>();

    /**
     * 构造函数
     */
    private MessageHolder() {

    }

    /**
     * 获取单例对象
     * @return
     */
    public static MessageHolder getInstance() {
        return holder;
    }

    /**
     * 向message资源持有器增加message
     */
    public void addMessage(Integer key, String message) {
        codeMessages.put(key, message);
    }

    /**
     * 根据key获取message内容
     */
    public String getMessage(Integer key) {
        return codeMessages.get(key);
    }
}
