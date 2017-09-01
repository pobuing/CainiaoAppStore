package com.xinw.cainiaoappstore.common.rx.subscriber;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.xinw.cainiaoappstore.common.exception.BaseException;
import com.xinw.cainiaoappstore.common.rx.RxErrorHandler;
import com.xinw.cainiaoappstore.ui.activity.LoginActivity;

/**
 * byD9ing on 2017/8/16.
 * Describe: 错误处理Subscriber
 * good luck
 */

public abstract class ErrorHandlerSubscriber<T> extends DefaultSunscriber<T> {

    protected RxErrorHandler mErrorHandler = null;

    protected Context mContext;

    public ErrorHandlerSubscriber(Context mContext) {
        this.mContext = mContext;
        mErrorHandler = new RxErrorHandler(mContext);
    }

    @Override
    public void onError(Throwable e) {
        BaseException baseException = mErrorHandler.handlerError(e);
        if (baseException == null) {
            e.printStackTrace();
            Log.d("APPSTORE", "onError: " + e.getMessage());
        } else {
            mErrorHandler.showErrorMessage(baseException);
            if (baseException.getCode() == BaseException.ERROR_TOKEN) {
                toLogin();
            }
        }
    }

    private void toLogin() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
    }
}
