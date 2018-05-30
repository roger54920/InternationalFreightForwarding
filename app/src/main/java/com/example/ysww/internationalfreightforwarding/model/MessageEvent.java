package com.example.ysww.internationalfreightforwarding.model;

/**
 * Event发送信息
 */

public class MessageEvent {

    private Object data;

    public MessageEvent(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}