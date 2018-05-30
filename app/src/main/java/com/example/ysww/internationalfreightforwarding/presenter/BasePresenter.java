package com.example.ysww.internationalfreightforwarding.presenter;

/**
 * 基础类型
 */

public interface BasePresenter <T>{
    public void attach(T t);
    public void dettach();
}
