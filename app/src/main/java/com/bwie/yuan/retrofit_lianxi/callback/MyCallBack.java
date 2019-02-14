package com.bwie.yuan.retrofit_lianxi.callback;

public interface MyCallBack<T> {
    void onSuccesss(T data);
    void onFails(String error);
}
