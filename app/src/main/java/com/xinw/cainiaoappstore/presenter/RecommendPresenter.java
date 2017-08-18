package com.xinw.cainiaoappstore.presenter;

import com.xinw.cainiaoappstore.bean.IndexBean;
import com.xinw.cainiaoappstore.common.rx.RxHttpReponseCompat;
import com.xinw.cainiaoappstore.common.rx.subscriber.ProgressSubscriber;
import com.xinw.cainiaoappstore.data.RecommendModel;
import com.xinw.cainiaoappstore.presenter.contract.RecommendContract;

import javax.inject.Inject;

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
        mModel.getIndex().compose(RxHttpReponseCompat.<IndexBean>compatResult()).subscribe(new ProgressSubscriber<IndexBean>(mContext, mView) {
            @Override
            public void onNext(IndexBean indexBean) {
                mView.showResult(indexBean);
            }
        });
    }
}
