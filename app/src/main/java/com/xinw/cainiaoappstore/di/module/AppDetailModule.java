package com.xinw.cainiaoappstore.di.module;

import com.xinw.cainiaoappstore.data.AppInfoModel;
import com.xinw.cainiaoappstore.data.http.ApiService;
import com.xinw.cainiaoappstore.presenter.contract.AppInfoContract;

import dagger.Module;
import dagger.Provides;

/**
 * byD9ing on 2017/9/19.
 * Describe:
 * good luck
 */
@Module(includes = {AppModelModule.class})
public class AppDetailModule {
    private AppInfoContract.AppDetailView mView;

    public AppDetailModule(AppInfoContract.AppDetailView view){


        this.mView = view;
    }





    @Provides
    public AppInfoContract.AppDetailView provideView(){

        return mView;
    }
}
