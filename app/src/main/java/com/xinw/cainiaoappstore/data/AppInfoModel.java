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


    public Observable<BaseBean<PageBean<AppInfo>>> getFeaturedAppsByCategory(int categoryid, int page) {
        return apiService.getFeaturedAppsByCategory(categoryid, page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getTopListAppsByCategory(int categoryid, int page) {
        return apiService.getTopListAppsByCategory(categoryid, page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getNewListAppsByCategory(int categoryid, int page) {
        return apiService.getNewListAppsByCategory(categoryid, page);
    }

    public Observable<BaseBean<AppInfo>> getAppDetail( int id){

        return  apiService.getAppDetail(id);
    }
}
