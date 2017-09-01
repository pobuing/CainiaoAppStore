package com.xinw.cainiaoappstore.common.rx.subscriber;

import android.content.Context;
import android.text.TextUtils;

import com.xinw.cainiaoappstore.common.exception.BaseException;
import com.xinw.cainiaoappstore.ui.activity.BaseView;

/**
 * byD9ing on 2017/8/16.
 * Describe:
 * good luck
 */

public abstract class ProgressSubscriber<T> extends ErrorHandlerSubscriber<T> {
    private BaseView mBaseView;

    public ProgressSubscriber(Context mContext, BaseView mBaseView) {
        super(mContext);
        this.mBaseView = mBaseView;
    }


    public boolean isShowProgress() {
        return true;
    }


    @Override
    public void onStart() {
        if (isShowProgress()) {
            mBaseView.showLoading();
        }
    }


    @Override
    public void onCompleted() {

        mBaseView.dismissLoading();
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        if (e != null) {

            BaseException baseException = mErrorHandler.handlerError(e);
            if (!TextUtils.isEmpty(e.getMessage())) {
                if (e.getMessage().equals("permission disgranted")) {
                    mBaseView.showError("授权失败");
                } else {
                    mBaseView.showError(baseException.getDisplayMessage());
                }
            } else {
                mBaseView.showError(baseException.getDisplayMessage());
            }

        }
    }
}
