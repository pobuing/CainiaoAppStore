package com.xinw.cainiaoappstore.common.util;

import android.content.Context;
import android.graphics.Color;

import com.xinw.cainiaoappstore.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * byD9ing on 2017/8/16.
 * Describe:
 * good luck
 */

public class ProgressDialogHandler {
    private Context context;
    private SweetAlertDialog mProgressDialog = null;
    // TODO: 可取消开关
    private boolean isCancelable;
    private OnProgressCancelListener cancelListener;

    public ProgressDialogHandler(Context context, boolean isCancelable, OnProgressCancelListener cancelListener) {
        this.context = context;
        this.isCancelable = isCancelable;
        this.cancelListener = cancelListener;
        initProgressDialog();

    }

    /**
     * init Progress Dialog
     */
    private void initProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
            // TODO: setting
            mProgressDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            mProgressDialog.setTitleText(context.getResources().getString(R.string.loading));

        }
        if (isCancelable) {
            mProgressDialog.setCancelText("close");
            // TODO: register listener
            mProgressDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.cancel();
                    if (cancelListener != null) {
                        cancelListener.onCancelProgress();
                    }
                }
            });
        }

    }

    /**
     * show progress dialog
     */
    public void showProgressDialog() {
        if (mProgressDialog != null && !mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }


    public void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismissWithAnimation();
            mProgressDialog = null;
        }
    }

    public interface OnProgressCancelListener {
        void onCancelProgress();
    }
}

