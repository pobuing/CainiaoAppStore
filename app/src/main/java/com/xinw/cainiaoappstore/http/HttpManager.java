package com.xinw.cainiaoappstore.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * byD9ing on 2017/8/10.
 * Describe: http管理
 * good luck
 */

public class HttpManager {

    public OkHttpClient getOkHttpClient() {
        // TODO: 构建log拦截器
        HttpLoggingInterceptor loggin = new HttpLoggingInterceptor();
        // TODO: 设置记录整个body
        loggin.setLevel(HttpLoggingInterceptor.Level.BODY);
        // TODO: 创建OkHttpClient
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(loggin)
                // TODO: 连接超时设置
                .connectTimeout(10, TimeUnit.SECONDS)
                // TODO: 读取超时设置
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }

    public Retrofit getRetrofit(OkHttpClient okHttp) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttp);
        return builder.build();
    }
}
