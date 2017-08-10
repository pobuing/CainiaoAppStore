package com.xinw.cainiaoappstore.presenter;

import com.xinw.cainiaoappstore.bean.AppInfo;
import com.xinw.cainiaoappstore.bean.PageBean;
import com.xinw.cainiaoappstore.data.RecommendModel;
import com.xinw.cainiaoappstore.presenter.contract.RecommendContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * byD9ing on 2017/8/10.
 * Describe:RecommendPresenter
 * good luck
 */

public class RecommendPresenter implements RecommendContract.RePresenter {
    private RecommendModel mModel;
    private RecommendContract.ReView mView;

    public RecommendPresenter(RecommendContract.ReView mView) {
        this.mView = mView;
        mModel = new RecommendModel();
    }

    @Override
    public void requestDatas() {
        // TODO: progress show
        mView.showLoading();
        mModel.getApps(new Callback<PageBean<AppInfo>>() {
            @Override
            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
                mView.dismissLoading();
                if (response != null) {
                    mView.showResult(response.body().getDatas());
                } else {
                    mView.showNodata();
                }
            }

            @Override
            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
                mView.dismissLoading();
                mView.showError(t.getMessage());
            }
        });
    }
}
