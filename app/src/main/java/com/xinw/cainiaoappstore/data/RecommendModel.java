package com.xinw.cainiaoappstore.data;

import com.xinw.cainiaoappstore.bean.AppInfo;
import com.xinw.cainiaoappstore.bean.PageBean;
import com.xinw.cainiaoappstore.data.http.ApiService;

import retrofit2.Callback;

/**
 * byD9ing on 2017/8/10.
 * Describe: 推荐页面的Model
 * good luck
 */

public class RecommendModel {

    private ApiService apiService;

    public RecommendModel(ApiService apiService) {
        this.apiService = apiService;
    }

    public void getApps(Callback<PageBean<AppInfo>> callback) {
        apiService.getApps("{'page':0}").enqueue(callback);
    }

}
