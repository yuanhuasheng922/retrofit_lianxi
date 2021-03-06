package com.bwie.yuan.retrofit_lianxi.utils;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

public interface BacaApi {
    @GET
    Observable<ResponseBody> get(@Url String url);
    @POST
    Observable<ResponseBody> post(@Url String url, @QueryMap Map<String,String> map);
}
