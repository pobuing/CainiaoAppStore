package com.xinw.cainiaoappstore.common.rx.subscriber;

import android.content.Context;

import com.xinw.cainiaoappstore.common.util.ProgressDialogHandler;

/**
 * byD9ing on 2017/8/16.
 * Describe:
 * good luck
 */

public abstract class ProgressDialogSubscriber<T> extends ErrorHandlerSubscriber<T> implements ProgressDialogHandler.OnProgressCancelListener {
    private final ProgressDialogHandler progressDialogHandler;

    public ProgressDialogSubscriber(Context mContext) {
        super(mContext);
        progressDialogHandler = new ProgressDialogHandler(mContext, false, this);

    }

    protected boolean isShowProgressDialog() {
        return true;
    }

    @Override
    public void onCompleted() {
        if (isShowProgressDialog()) {
            this.progressDialogHandler.dismissProgressDialog();
        }
    }

    @Override
    public void onStart() {
        if (isShowProgressDialog()) {
            this.progressDialogHandler.showProgressDialog();
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        this.progressDialogHandler.dismissProgressDialog();
    }

    @Override
    public void onCancelProgress() {
        // TODO: cancel Subscribe
        unsubscribe();
    }
}
