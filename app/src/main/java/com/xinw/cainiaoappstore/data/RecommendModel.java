package com.xinw.cainiaoappstore.data;

import com.xinw.cainiaoappstore.bean.AppInfo;
import com.xinw.cainiaoappstore.bean.BaseBean;
import com.xinw.cainiaoappstore.bean.IndexBean;
import com.xinw.cainiaoappstore.bean.PageBean;
import com.xinw.cainiaoappstore.data.http.ApiService;

import rx.Observable;

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

    public Observable<BaseBean<PageBean<AppInfo>>> getApps() {
        return apiService.getApps("{'page':0}");
    }


    public Observable<BaseBean<IndexBean>> getIndex() {
        return apiService.index();
    }

}
