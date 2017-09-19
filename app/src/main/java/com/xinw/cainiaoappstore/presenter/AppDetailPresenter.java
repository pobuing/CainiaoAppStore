package com.xinw.cainiaoappstore.presenter;

import com.xinw.cainiaoappstore.bean.AppInfo;
import com.xinw.cainiaoappstore.common.rx.RxHttpReponseCompat;
import com.xinw.cainiaoappstore.common.rx.subscriber.ProgressSubscriber;
import com.xinw.cainiaoappstore.data.AppInfoModel;
import com.xinw.cainiaoappstore.presenter.contract.AppInfoContract;

import javax.inject.Inject;

/**
 * byD9ing on 2017/9/19.
 * Describe:
 * good luck
 */

public class AppDetailPresenter extends BasePresenter<AppInfoModel, AppInfoContract.AppDetailView> {
    @Inject
    public AppDetailPresenter(AppInfoModel mModel, AppInfoContract.AppDetailView mView) {
        super(mModel, mView);
    }

    public void getAppDetail(int id) {
        mModel.getAppDetail(id)
                .compose(RxHttpReponseCompat.<AppInfo>compatResult())
                .subscribe(new ProgressSubscriber<AppInfo>(mContext, mView) {
                    @Override
                    public void onNext(AppInfo appInfo) {
                        mView.showAppDetail(appInfo);
                    }
                });

    }
}
