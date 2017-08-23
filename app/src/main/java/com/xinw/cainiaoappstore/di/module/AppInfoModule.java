package com.xinw.cainiaoappstore.di.module;

import com.xinw.cainiaoappstore.data.AppInfoModel;
import com.xinw.cainiaoappstore.data.http.ApiService;
import com.xinw.cainiaoappstore.presenter.contract.AppInfoContract;

import dagger.Module;
import dagger.Provides;

/**
 * byD9ing on 2017/8/23.
 * Describe:
 * good luck
 */

@Module
public class AppInfoModule {
    private AppInfoContract.AppInfoView mView;

    public AppInfoModule(AppInfoContract.AppInfoView mView) {
        this.mView = mView;

    }

    @Provides
    public AppInfoContract.AppInfoView provideView() {
        return mView;
    }

    @Provides
    public AppInfoModel provideModel(ApiService apiService) {
        return new AppInfoModel(apiService);
    }
}
