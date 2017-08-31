package com.xinw.cainiaoappstore.presenter;

import com.xinw.cainiaoappstore.bean.AppInfo;
import com.xinw.cainiaoappstore.bean.PageBean;
import com.xinw.cainiaoappstore.common.rx.RxHttpReponseCompat;
import com.xinw.cainiaoappstore.common.rx.subscriber.ErrorHandlerSubscriber;
import com.xinw.cainiaoappstore.common.rx.subscriber.ProgressSubscriber;
import com.xinw.cainiaoappstore.data.AppInfoModel;
import com.xinw.cainiaoappstore.presenter.contract.AppInfoContract;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * byD9ing on 2017/8/23.
 * Describe:
 * good luck
 */

public class AppInfoPresenter extends BasePresenter<AppInfoModel, AppInfoContract.AppInfoView> {
    public static final int TOP_LIST = 1;
    public static final int GAME = 2;

    @Inject
    public AppInfoPresenter(AppInfoModel mModel, AppInfoContract.AppInfoView mView) {
        super(mModel, mView);
    }

    public void requestData(int type, int page) {
        Subscriber subscriber = null;
        // TODO: 第一页需要进度页面
        if (page == 0) {
            subscriber = new ProgressSubscriber<PageBean<AppInfo>>(mContext, mView) {
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

        Observable observable = getObservable(type, page);
        observable.compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(subscriber);

    }

    private Observable getObservable(int type, int page) {
        switch (type) {
            case TOP_LIST:
                return mModel.topList(page);
            case GAME:
                return mModel.getGames(page);
        }
        return Observable.empty();
    }
}
