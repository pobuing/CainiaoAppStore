package com.xinw.cainiaoappstore.presenter;

import com.xinw.cainiaoappstore.ui.activity.BaseView;

/**
 * byD9ing on 2017/8/10.
 * Describe: MVP架构 BasePresenter
 * good luck
 */

public class BasePresenter<M, V extends BaseView> {
    protected M mModel;
    protected V mView;

    public BasePresenter(M mModel, V mView) {
        this.mModel = mModel;
        this.mView = mView;
    }
}
