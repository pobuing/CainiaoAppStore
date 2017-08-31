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
public class AppInfoModel {

    private ApiService apiService;

    public AppInfoModel(ApiService apiService) {
        this.apiService = apiService;
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getApps() {
        return apiService.getApps("{'page':0}");
    }


    public Observable<BaseBean<IndexBean>> getIndex() {
        return apiService.index();
    }


    /**
     * get topList Data from server
     *
     * @param page
     * @return
     */
    public Observable<BaseBean<PageBean<AppInfo>>> topList(int page) {
        return apiService.topList(page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getGames(int page) {
        return apiService.game(page);
    }
}
