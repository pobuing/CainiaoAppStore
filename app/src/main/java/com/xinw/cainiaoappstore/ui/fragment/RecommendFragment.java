package com.xinw.cainiaoappstore.ui.fragment;


import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.xinw.cainiaoappstore.R;
import com.xinw.cainiaoappstore.bean.AppInfo;
import com.xinw.cainiaoappstore.di.component.AppComponent;
import com.xinw.cainiaoappstore.di.component.DaggerRecommendComponent;
import com.xinw.cainiaoappstore.di.module.RecommendModule;
import com.xinw.cainiaoappstore.presenter.RecommendPresenter;
import com.xinw.cainiaoappstore.presenter.contract.RecommendContract;
import com.xinw.cainiaoappstore.ui.adapter.RecomendAppAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Ivan on 16/9/26.
 */

public class RecommendFragment extends ProgressFragment<RecommendPresenter> implements RecommendContract.ReView {

    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;


    private RecomendAppAdapter reAdapter;

    @Inject
    RecommendPresenter recommendPresenter;


    @Override
    protected void init() {
        initData();

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_recomend;
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

    private void initRecycleView(List<AppInfo> mDatas) {
        Log.d("123", "initRecycleView: " + mDatas.size());
        // TODO: 创建RecyclerView Adapter
        reAdapter = new RecomendAppAdapter(getActivity(), mDatas);
        // TODO: 设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // TODO: 设置下划线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
        // TODO: 动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setAdapter(reAdapter);

    }


    @Override
    public void showResult(List<AppInfo> datas) {
        initRecycleView(datas);
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
