package com.xinw.cainiaoappstore.ui.fragment;


import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.xinw.cainiaoappstore.R;
import com.xinw.cainiaoappstore.bean.IndexBean;
import com.xinw.cainiaoappstore.di.component.AppComponent;
import com.xinw.cainiaoappstore.di.component.DaggerRecommendComponent;
import com.xinw.cainiaoappstore.di.module.RecommendModule;
import com.xinw.cainiaoappstore.presenter.RecommendPresenter;
import com.xinw.cainiaoappstore.presenter.contract.AppInfoContract;
import com.xinw.cainiaoappstore.ui.adapter.IndexMultipleAdapter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Ivan on 16/9/26.
 */

public class RecommendFragment extends ProgressFragment<RecommendPresenter> implements AppInfoContract.ReView {

    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;



    @Inject
    RecommendPresenter recommendPresenter;
    private IndexMultipleAdapter mAdatper;


    @Override
    protected void init() {
        initData();

    }

    @Override
    protected int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerRecommendComponent.builder().appComponent(appComponent)
                .recommendModule(new RecommendModule(this))
                .build().inject(this);
    }

    @Override
    public void onEmptyViewClick() {
        initData();
    }

    /**
     * 加载App信心
     */
    private void initData() {
        recommendPresenter.requestDatas();

    }

    private void initRecycleView() {

        // TODO: 设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // TODO: 动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdatper = new IndexMultipleAdapter(getActivity());
        mRecyclerView.setAdapter(mAdatper);


    }


    @Override
    public void showResult(IndexBean indexBean) {
        initRecycleView();
        mAdatper.setData(indexBean);
    }

    @Override
    public void showNodata() {
        Toast.makeText(getActivity(), "没有数据", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onPermissionGranted() {

    }

    @Override
    public void onPermissionDisgranted() {
        showError("授权失败");
    }
}
