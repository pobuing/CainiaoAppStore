package com.xinw.cainiaoappstore.application;

import android.app.Application;

import com.xinw.cainiaoappstore.di.component.AppComponent;
import com.xinw.cainiaoappstore.di.component.DaggerAppComponent;
import com.xinw.cainiaoappstore.di.module.AppModule;
import com.xinw.cainiaoappstore.di.module.HttpModule;

/**
 * byD9ing on 2017/8/14.
 * Describe:
 * good luck
 */

public class MyApplication extends Application {
    public static String TAG = "AppStroe";
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this))
                .httpModule(new HttpModule()).build();
    }


    public AppComponent getmAppComponent() {
        return mAppComponent;
    }
}
