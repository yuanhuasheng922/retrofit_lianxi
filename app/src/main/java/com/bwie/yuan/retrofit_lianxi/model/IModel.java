package com.bwie.yuan.retrofit_lianxi.model;

import com.bwie.yuan.retrofit_lianxi.callback.MyCallBack;

import java.util.Map;

public interface IModel {

    void onget(String url, Class clazz, MyCallBack callBack);
    void onpost(String url, Class clazz, Map<String,String> map,MyCallBack callBack);

}
