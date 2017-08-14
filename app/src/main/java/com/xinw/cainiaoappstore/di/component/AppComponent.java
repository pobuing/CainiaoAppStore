package com.xinw.cainiaoappstore.di.component;

import com.xinw.cainiaoappstore.data.http.ApiService;
import com.xinw.cainiaoappstore.di.module.AppModule;
import com.xinw.cainiaoappstore.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * byD9ing on 2017/8/14.
 * Describe:
 * good luck
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    public ApiService getApiService();
}
