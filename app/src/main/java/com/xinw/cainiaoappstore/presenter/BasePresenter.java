package com.xinw.cainiaoappstore.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.xinw.cainiaoappstore.ui.activity.BaseView;

/**
 * byD9ing on 2017/8/10.
 * Describe: MVP架构 BasePresenter
 * good luck
 */

public class BasePresenter<M, V extends BaseView> {
    protected M mModel;
    protected V mView;
    protected Context mContext;

    public BasePresenter(M mModel, V mView) {
        this.mModel = mModel;
        this.mView = mView;
        initContext();
    }

    private void initContext() {
        if (mView instanceof Fragment) {
            mContext = ((Fragment) mView).getActivity();
        } else {
            mContext = ((Activity) mView);
        }
    }
}
