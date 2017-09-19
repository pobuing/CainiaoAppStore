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
    public static final int CATEGORY = 3;

    // TODO: 精品
    public static final int FEATURED = 0;
    // TODO: 排行
    public static final int TOPLIST = 1;
    // TODO: 新品
    public static final int NEWLIST = 2;

    @Inject
    public AppInfoPresenter(AppInfoModel mModel, AppInfoContract.AppInfoView mView) {
        super(mModel, mView);
    }


    public void request(int type, int page, int categoryId, int flagType) {
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

        Observable observable = getObservable(type, page, categoryId, flagType);
        observable.compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(subscriber);
    }

    public void requestData(int type, int page) {
        request(type, page, 0, 0);

    }

    public void requestCategoryApps(int categoryId, int page, int flagType) {
        request(CATEGORY, page, categoryId, flagType);
    }

    private Observable getObservable(int type, int page, int categoryId, int flagType) {
        switch (type) {
            case TOP_LIST:
                return mModel.topList(page);
            case GAME:
                return mModel.getGames(page);
            case CATEGORY:
                // TODO: 精品
                if (flagType == FEATURED) {
                    return mModel.getFeaturedAppsByCategory(categoryId, page);
                } else if (flagType == TOPLIST) {
                    return mModel.getTopListAppsByCategory(categoryId, page);
                } else if (flagType == NEWLIST) {
                    return mModel.getNewListAppsByCategory(categoryId, page);
                }
            default:
                return Observable.empty();
        }
    }
}
