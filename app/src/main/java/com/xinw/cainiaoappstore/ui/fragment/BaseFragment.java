package com.xinw.cainiaoappstore.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinw.cainiaoappstore.application.MyApplication;
import com.xinw.cainiaoappstore.di.component.AppComponent;
import com.xinw.cainiaoappstore.presenter.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * byD9ing on 2017/8/14.
 * Describe:
 * good luck
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    private View rootView;
    private Unbinder mUnbinder;
    private MyApplication mApplication;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(setLayoutId(), container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mApplication = ((MyApplication) getActivity().getApplication());
        setUpActivityComponent(mApplication.getmAppComponent());
        init();
    }

    /**
     * 初始化方法
     */
    protected abstract void init();

    protected abstract void setUpActivityComponent(AppComponent appComponent);

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
    }

    /**
     * 设置布局文件
     *
     * @return
     */
    public abstract int setLayoutId();
}
