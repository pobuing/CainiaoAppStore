package com.xinw.cainiaoappstore.presenter;

import android.util.Log;

import com.xinw.cainiaoappstore.bean.AppInfo;
import com.xinw.cainiaoappstore.bean.BaseBean;
import com.xinw.cainiaoappstore.bean.PageBean;
import com.xinw.cainiaoappstore.common.rx.RxHttpReponseCompat;
import com.xinw.cainiaoappstore.common.rx.subscriber.ProgressSubscriber;
import com.xinw.cainiaoappstore.data.RecommendModel;
import com.xinw.cainiaoappstore.presenter.contract.RecommendContract;

import javax.inject.Inject;

import rx.Observable;

/**
 * byD9ing on 2017/8/10.
 * Describe:RecommendPresenter
 * good luck
 */

public class RecommendPresenter extends BasePresenter<RecommendModel, RecommendContract.ReView> {

    @Inject
    public RecommendPresenter(RecommendContract.ReView mView, RecommendModel model) {
        super(model, mView);
    }

    public void requestDatas() {

        Observable<BaseBean<PageBean<AppInfo>>> beanObservable = mModel.getApps();
        beanObservable.compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(new ProgressSubscriber<PageBean<AppInfo>>(mContext, mView) {
                    @Override
                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
                        Log.d("123", "onNext: ");
                        if (appInfoPageBean != null) {
                            mView.showResult(appInfoPageBean.getDatas());
                        } else {
                            mView.showNodata();
                        }
                    }
                });
    }
}
