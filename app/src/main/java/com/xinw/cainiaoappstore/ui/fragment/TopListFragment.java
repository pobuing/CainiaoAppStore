package com.xinw.cainiaoappstore.ui.fragment;


import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xinw.cainiaoappstore.R;
import com.xinw.cainiaoappstore.bean.AppInfo;
import com.xinw.cainiaoappstore.bean.PageBean;
import com.xinw.cainiaoappstore.di.component.AppComponent;
import com.xinw.cainiaoappstore.di.component.DaggerAppInfoComponent;
import com.xinw.cainiaoappstore.di.module.AppInfoModule;
import com.xinw.cainiaoappstore.presenter.AppInfoPresenter;
import com.xinw.cainiaoappstore.presenter.contract.AppInfoContract;
import com.xinw.cainiaoappstore.ui.adapter.AppInfoAdapter;
import com.xinw.cainiaoappstore.ui.widget.DividerItemDecoration;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Ivan on 16/9/26.
 */

public class TopListFragment extends ProgressFragment<AppInfoPresenter> implements AppInfoContract.AppInfoView, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;

    @Inject
    AppInfoPresenter appInfoPresenter;

    private AppInfoAdapter mAdapter;
    // TODO: 查询页面
    private int page;

    @Override
    protected void init() {
        appInfoPresenter.requestData(page);

        initRecyclerView();
    }


    @Override
    protected int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder().appComponent(appComponent)
                .appInfoModule(new AppInfoModule(this)).build().injectTopListFragment(this);
    }

    private void initRecyclerView() {
        // TODO: 设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // TODO: 动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mAdapter = AppInfoAdapter.build().showPosition(true).showShowBrief(false).showCategoryName(true)
                .build();
        mAdapter.setOnLoadMoreListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showError(String displayMessage) {

    }

    @Override
    public void onPermissionGranted() {

    }

    @Override
    public void onPermissionDisgranted() {

    }

    @Override
    public void showResult(PageBean<AppInfo> pageBean) {
        mAdapter.addData(pageBean.getDatas());
        if (pageBean.isHasMore()) {
            page++;
        }
        mAdapter.setEnableLoadMore(pageBean.isHasMore());
    }

    @Override
    public void onLoadMoreComplete() {
        mAdapter.loadMoreComplete();
    }

    @Override
    public void onLoadMoreRequested() {
        appInfoPresenter.requestData(page);
    }
}
