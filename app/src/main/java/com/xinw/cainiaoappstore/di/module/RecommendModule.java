package com.xinw.cainiaoappstore.di.module;

import android.app.ProgressDialog;

import com.xinw.cainiaoappstore.data.RecommendModel;
import com.xinw.cainiaoappstore.data.http.ApiService;
import com.xinw.cainiaoappstore.presenter.contract.RecommendContract;
import com.xinw.cainiaoappstore.ui.fragment.RecommendFragment;

import dagger.Module;
import dagger.Provides;

/**
 * byD9ing on 2017/8/11.
 * Describe: RecommendModel module
 * good luck
 */

@Module
public class RecommendModule {

    private RecommendContract.ReView mView;

    public RecommendModule(RecommendContract.ReView mView) {
        this.mView = mView;
    }

    @Provides
    public RecommendContract.ReView provideView() {
        return mView;
    }


    @Provides
    public RecommendModel provideRecommendModel(ApiService apiService) {
        return new RecommendModel(apiService);
    }

    @Provides
    public ProgressDialog provideProgressDialog(RecommendContract.ReView reView) {
        return new ProgressDialog(((RecommendFragment) reView).getActivity());
    }
}
