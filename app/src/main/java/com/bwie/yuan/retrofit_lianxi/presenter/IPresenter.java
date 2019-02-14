package com.bwie.yuan.retrofit_lianxi.presenter;

import com.bwie.yuan.retrofit_lianxi.callback.MyCallBack;

import java.util.Map;

public interface IPresenter {
    void onget(String url, Class clazz);
    void onpost(String url, Class clazz, Map<String,String> map);

}
