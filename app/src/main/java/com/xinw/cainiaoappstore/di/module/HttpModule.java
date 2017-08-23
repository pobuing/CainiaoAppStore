package com.xinw.cainiaoappstore.di.module;

import android.app.Application;

import com.google.gson.Gson;
import com.xinw.cainiaoappstore.BuildConfig;
import com.xinw.cainiaoappstore.common.http.CommonParamsIntercepter;
import com.xinw.cainiaoappstore.data.http.ApiService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * byD9ing on 2017/8/14.
 * Describe: Http相关 Dagger2
 * good luck
 */
@Module
public class HttpModule {
    @Provides
    @Singleton
    public OkHttpClient getOkHttpClient(Gson gson, Application application) {

        // TODO: 创建OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            // TODO: 构建log拦截器
            HttpLoggingInterceptor loggin = new HttpLoggingInterceptor();
            // TODO: 设置记录整个body
            loggin.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggin);
        }
        OkHttpClient okHttpClient = builder
                // TODO: 连接超时设置
                .connectTimeout(10, TimeUnit.SECONDS)
                // TODO: 读取超时设置
                .readTimeout(10, TimeUnit.SECONDS)
                // TODO: 公共参数拦截器
                .addInterceptor(new CommonParamsIntercepter(application, gson))
                .build();

        return okHttpClient;
    }


    @Provides
    @Singleton
    public Retrofit getRetrofit(OkHttpClient okHttp) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttp);
        return builder.build();
    }

    @Provides
    @Singleton
    public ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
