package com.xinw.cainiaoappstore.common.rx;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.JsonParseException;
import com.xinw.cainiaoappstore.common.exception.ApiException;
import com.xinw.cainiaoappstore.common.exception.BaseException;
import com.xinw.cainiaoappstore.common.exception.ErrorMessageFactory;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * byD9ing on 2017/8/16.
 * Describe:
 * good luck
 */

public class RxErrorHandler {
    private Context mContext;

    public RxErrorHandler(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 处理异常方法
     *
     * @param e
     * @return
     */
    public BaseException handlerError(Throwable e) {
        BaseException exception = new BaseException();

        if (e instanceof ApiException) {
            exception.setCode(((ApiException) e).getCode());
        } else if (e instanceof SocketException) {
            exception.setCode(BaseException.SOCKET_ERROR);
        } else if (e instanceof SocketTimeoutException) {
            exception.setCode(BaseException.SOCKET_TIMEOUT_ERROR);

        } else if (e instanceof JsonParseException) {
            exception.setCode(BaseException.JSON_ERROR);

        } else if (e instanceof HttpException) {

        } else {
            exception.setCode(BaseException.UNKNOWN_ERROR);
        }
        exception.setDisplayMessage(ErrorMessageFactory.create(mContext, exception.getCode()));
        return exception;
    }

    /**
     * show message
     *
     * @param e
     */
    public void showErrorMessage(BaseException e) {
        Toast.makeText(mContext, e.getDisplayMessage(), Toast.LENGTH_SHORT).show();
    }
}
