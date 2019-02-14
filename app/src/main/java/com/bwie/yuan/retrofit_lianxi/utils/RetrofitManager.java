package com.bwie.yuan.retrofit_lianxi.utils;

import java.io.IOException;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitManager {
    private static String BACE_API="http://172.17.8.100/small/";
    private static RetrofitManager instance;
    private final BacaApi bacaApi;

    public static RetrofitManager getInstance()
    {
        if (instance==null)
        {
            instance=new RetrofitManager();
        }
        return instance;

    }

    public RetrofitManager() {

        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();

        OkHttpClient client=new OkHttpClient.Builder()
                .connectTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .build();

        Retrofit retrofit=new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BACE_API)
                .build();
        bacaApi = retrofit.create(BacaApi.class);


    }

    public void get(String url,HttpListener listener)
    {
        bacaApi.get(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(listener));
    }

    public void post(String url, Map<String,String> map,HttpListener listener)
    {
        bacaApi.post(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(listener));

    }

    private Observer getObserver(final HttpListener listener) {

        Observer observer=new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (listener!=null)
                {
                    listener.onfail(e.getMessage());
                }
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String s = responseBody.string();
                    if (listener!=null)
                    {
                        listener.onsuccess(s);
                    }
                } catch (IOException e) {
                    e.printStackTrace();

                    if (listener!=null)
                    {
                        listener.onfail(e.getMessage());
                    }
                }
            }
        };

        return observer;

    }


    HttpListener listener;

    public void setListener(HttpListener listener) {
        this.listener = listener;
    }

    public interface HttpListener{
        void onsuccess(String data);
        void onfail(String error);
    }

}
