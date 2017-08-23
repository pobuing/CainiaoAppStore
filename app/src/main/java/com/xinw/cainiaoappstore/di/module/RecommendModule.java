package com.xinw.cainiaoappstore.di.module;

import android.app.ProgressDialog;

import com.xinw.cainiaoappstore.data.AppInfoModel;
import com.xinw.cainiaoappstore.data.http.ApiService;
import com.xinw.cainiaoappstore.presenter.contract.AppInfoContract;
import com.xinw.cainiaoappstore.ui.fragment.RecommendFragment;

import dagger.Module;
import dagger.Provides;

/**
 * byD9ing on 2017/8/11.
 * Describe: AppInfoModel module
 * good luck
 */

@Module
public class RecommendModule {

    private AppInfoContract.ReView mView;

    public RecommendModule(AppInfoContract.ReView mView) {
        this.mView = mView;
    }

    @Provides
    public AppInfoContract.ReView provideView() {
        return mView;
    }


    @Provides
    public AppInfoModel provideRecommendModel(ApiService apiService) {
        return new AppInfoModel(apiService);
    }

    @Provides
    public ProgressDialog provideProgressDialog(AppInfoContract.ReView reView) {
        return new ProgressDialog(((RecommendFragment) reView).getActivity());
    }
}
