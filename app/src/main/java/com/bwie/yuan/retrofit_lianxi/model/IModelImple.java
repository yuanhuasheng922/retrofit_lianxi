package com.bwie.yuan.retrofit_lianxi.model;

import com.bwie.yuan.retrofit_lianxi.callback.MyCallBack;
import com.bwie.yuan.retrofit_lianxi.utils.RetrofitManager;
import com.google.gson.Gson;

import java.util.Map;

public class IModelImple implements IModel{
    @Override
    public void onget(String url, final Class clazz, final MyCallBack callBack) {
        RetrofitManager.getInstance().get(url, new RetrofitManager.HttpListener() {
            @Override
            public void onsuccess(String data) {
                Object o=new Gson().fromJson(data,clazz);
               if (callBack!=null)
               {
                   callBack.onSuccesss(o);
               }
            }

            @Override
            public void onfail(String error) {
                if (callBack!=null)
                {
                   callBack.onFails(error);
                }
            }
        });
    }

    @Override
    public void onpost(String url, final Class clazz, Map<String, String> map, final MyCallBack callBack) {
            RetrofitManager.getInstance().post(url, map, new RetrofitManager.HttpListener() {
                @Override
                public void onsuccess(String data) {
                    Object o=new Gson().fromJson(data,clazz);
                    if (callBack!=null)
                    {
                        callBack.onSuccesss(o);
                    }
                }

                @Override
                public void onfail(String error) {
                    if (callBack!=null)
                    {
                       callBack.onFails(error);
                    }
                }
            });
    }
}
