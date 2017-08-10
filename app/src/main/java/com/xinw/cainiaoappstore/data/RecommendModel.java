package com.xinw.cainiaoappstore.data;

import com.xinw.cainiaoappstore.bean.AppInfo;
import com.xinw.cainiaoappstore.bean.PageBean;
import com.xinw.cainiaoappstore.http.ApiService;
import com.xinw.cainiaoappstore.http.HttpManager;

import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * byD9ing on 2017/8/10.
 * Describe: 推荐页面的Model
 * good luck
 */

public class RecommendModel {

    public void getApps(Callback<PageBean<AppInfo>> callback) {
        HttpManager httpManager = new HttpManager();
        Retrofit retrofit = httpManager.getRetrofit(httpManager.getOkHttpClient());
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getApps("{'page':0}").enqueue(callback);
    }

}
