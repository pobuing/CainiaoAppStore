package com.xinw.cainiaoappstore.data.http;

import com.xinw.cainiaoappstore.bean.AppInfo;
import com.xinw.cainiaoappstore.bean.BaseBean;
import com.xinw.cainiaoappstore.bean.PageBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * byD9ing on 2017/8/10.
 * Describe:
 * good luck
 */

public interface ApiService {
    // TODO: BASE_URL
    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";
    // TODO: BASE_IMGURL
//    public static final String BASE_IMG_URL = "http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";
    String BASE_IMG_URL ="http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";

//    public Call<PageBean<AppInfo>> getApps(@Query("p") String jsonParam);

    @GET("featured2")
    Observable<BaseBean<PageBean<AppInfo>>> getApps(@Query("p") String jsonParam);
}
