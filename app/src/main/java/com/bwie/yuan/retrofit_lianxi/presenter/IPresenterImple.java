package com.bwie.yuan.retrofit_lianxi.presenter;

import com.bwie.yuan.retrofit_lianxi.callback.MyCallBack;
import com.bwie.yuan.retrofit_lianxi.model.IModelImple;
import com.bwie.yuan.retrofit_lianxi.view.IView;

import java.util.Map;

public class IPresenterImple implements IPresenter{
    private IView iView;
    private IModelImple iModelImple;

    public IPresenterImple(IView iView) {
        this.iView = iView;
        iModelImple=new IModelImple();
    }

    @Override
    public void onget(String url, Class clazz) {

        iModelImple.onget(url, clazz, new MyCallBack() {
            @Override
            public void onSuccesss(Object data) {
                iView.onsuccess(data);
            }

            @Override
            public void onFails(String error) {

            }
        });
    }

    @Override
    public void onpost(String url, Class clazz, Map<String, String> map) {

    }
}
