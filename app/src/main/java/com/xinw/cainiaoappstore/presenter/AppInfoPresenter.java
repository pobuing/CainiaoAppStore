package com.xinw.cainiaoappstore.presenter;

import com.xinw.cainiaoappstore.bean.AppInfo;
import com.xinw.cainiaoappstore.bean.PageBean;
import com.xinw.cainiaoappstore.common.rx.RxHttpReponseCompat;
import com.xinw.cainiaoappstore.common.rx.subscriber.ErrorHandlerSubscriber;
import com.xinw.cainiaoappstore.common.rx.subscriber.ProgressDialogSubscriber;
import com.xinw.cainiaoappstore.data.AppInfoModel;
import com.xinw.cainiaoappstore.presenter.contract.AppInfoContract;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * byD9ing on 2017/8/23.
 * Describe:
 * good luck
 */

public class AppInfoPresenter extends BasePresenter<AppInfoModel, AppInfoContract.AppInfoView> {
    @Inject
    public AppInfoPresenter(AppInfoModel mModel, AppInfoContract.AppInfoView mView) {
        super(mModel, mView);
    }

    public void requestData(int page) {
        Subscriber subscriber = null;
        // TODO: 第一页需要进度页面
        if (page == 0) {
            subscriber = new ProgressDialogSubscriber<PageBean<AppInfo>>(mContext) {
                @Override
                public void onNext(PageBean<AppInfo> pageBean) {
                    mView.showResult(pageBean);
                }

            };
        } else {
            subscriber = new ErrorHandlerSubscriber<PageBean<AppInfo>>(mContext) {
                @Override
                public void onCompleted() {
                    mView.onLoadMoreComplete();
                }

                @Override
                public void onNext(PageBean<AppInfo> pageBean) {
                    mView.showResult(pageBean);

                }

            };
        }
        mModel.topList(page)
                .compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(subscriber);
    }
}
