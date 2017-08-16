package com.xinw.cainiaoappstore.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.xinw.cainiaoappstore.R;
import com.xinw.cainiaoappstore.application.MyApplication;
import com.xinw.cainiaoappstore.di.component.AppComponent;
import com.xinw.cainiaoappstore.presenter.BasePresenter;
import com.xinw.cainiaoappstore.ui.activity.BaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * byD9ing on 2017/8/16.
 * Describe: 进度fragment
 * good luck
 */

public abstract class ProgressFragment<T extends BasePresenter> extends Fragment implements BaseView {

    private FrameLayout mRootView;
    private FrameLayout mContentView;
    private View mViewProgress;
    private View mViewEmpty;
    private TextView mTextError;
    private MyApplication mApplication;
    private Unbinder unBinder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = ((FrameLayout) inflater.inflate(R.layout.fragment_progress, container, false));
        mContentView = (FrameLayout) mRootView.findViewById(R.id.view_content);
        mViewProgress = mRootView.findViewById(R.id.view_progress);
        mViewEmpty = mRootView.findViewById(R.id.view_empty);
        mTextError = ((TextView) mRootView.findViewById(R.id.text_tip));
        mViewEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEmptyViewClick();
            }
        });
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mApplication = ((MyApplication) getActivity().getApplication());
        setupAcitivtyComponent(mApplication.getmAppComponent());
        setRealContentView();
        init();
    }


    private void setRealContentView() {
        View realContentView = LayoutInflater.from(getActivity()).inflate(setLayout(), mContentView, true);
        unBinder = ButterKnife.bind(this, realContentView);

    }

    public void showProgressView() {
        showView(R.id.view_progress);

    }

    public void showContentView() {

        showView(R.id.view_content);
    }

    public void showEmptyView() {


        showView(R.id.view_empty);
    }


    private void showEmptyView(String displayMessage) {

        showEmptyView();
        mTextError.setText(displayMessage);
    }

    private void showView(int viewId) {
        for (int i = 0; i < mRootView.getChildCount(); i++) {
            View mRootViewChildAt = mRootView.getChildAt(i);
            if (mRootViewChildAt.getId() == viewId) {
                mRootViewChildAt.setVisibility(View.VISIBLE);
            } else {
                mRootViewChildAt.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unBinder != null) {
            unBinder.unbind();
        }
    }

    @Override
    public void showLoading() {
        showProgressView();
    }

    @Override
    public void showError(String displayMessage) {
        showEmptyView(displayMessage);
    }

    @Override
    public void dismissLoading() {
        showContentView();
    }

    protected abstract void init();

    protected abstract int setLayout();

    public abstract void setupAcitivtyComponent(AppComponent appComponent);

    public void onEmptyViewClick() {

    }
}
