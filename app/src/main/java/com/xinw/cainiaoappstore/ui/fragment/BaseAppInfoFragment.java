package com.xinw.cainiaoappstore.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xinw.cainiaoappstore.R;
import com.xinw.cainiaoappstore.bean.AppInfo;
import com.xinw.cainiaoappstore.bean.PageBean;
import com.xinw.cainiaoappstore.presenter.AppInfoPresenter;
import com.xinw.cainiaoappstore.presenter.contract.AppInfoContract;
import com.xinw.cainiaoappstore.ui.activity.AppDetailActivity;
import com.xinw.cainiaoappstore.ui.adapter.AppInfoAdapter;
import com.xinw.cainiaoappstore.ui.widget.DividerItemDecoration;

import butterknife.BindView;

/**
 * byD9ing on 2017/8/23.
 * Describe:
 * good luck
 */

public abstract class BaseAppInfoFragment extends ProgressFragment<AppInfoPresenter> implements AppInfoContract.AppInfoView,
        BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;

    private AppInfoAdapter mAdapter;

    protected int page = 0;

    @Override
    public void onLoadMoreRequested() {
        mPresenter.requestData(type(), page);
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
    protected void init() {
        mPresenter.requestData(type(), page);
        initRecyclerView();
    }

    abstract int type();

    @Override
    protected int setLayout() {
        return R.layout.template_recycler_view;
    }


    protected void initRecyclerView() {
        // TODO: 设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // TODO: 动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mAdapter = buildApapter();
        mAdapter.setOnLoadMoreListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                // TODO: 存储视图
                mApplication.setmView(view);
                Intent intent = new Intent(getActivity(), AppDetailActivity.class);
                AppInfo appInfo = mAdapter.getItem(position);
                intent.putExtra("appinfo", appInfo);
                startActivity(intent);
            }
        });
    }

    protected abstract AppInfoAdapter buildApapter();
}
