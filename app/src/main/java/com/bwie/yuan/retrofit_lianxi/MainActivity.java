package com.bwie.yuan.retrofit_lianxi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bwie.yuan.retrofit_lianxi.adapter.ShopAdapter;
import com.bwie.yuan.retrofit_lianxi.bean.ShopBean;
import com.bwie.yuan.retrofit_lianxi.presenter.IPresenterImple;
import com.bwie.yuan.retrofit_lianxi.view.IView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IView {
@BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private IPresenterImple presenterImple;
    private ShopAdapter shopAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenterImple = new IPresenterImple(this);

        initview();
        getShow();
    }

    private void initview() {

        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerview.setLayoutManager(layoutManager);
        shopAdapter = new ShopAdapter(this);
        recyclerview.setAdapter(shopAdapter);
    }

    private void getShow() {
        presenterImple.onget("commodity/v1/findCommodityByKeyword?keyword=卫衣&page=1&count=20",ShopBean.class);
    }

    @Override
    public void onsuccess(Object data) {
        if (data instanceof ShopBean)
        {
            ShopBean shopBean= (ShopBean) data;
            shopAdapter.setmDatas(shopBean.getResult());
        }


    }

    @Override
    public void onfail(String error) {

    }
}
