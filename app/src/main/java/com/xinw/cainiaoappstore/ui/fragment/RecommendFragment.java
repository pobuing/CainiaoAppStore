package com.xinw.cainiaoappstore.ui.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xinw.cainiaoappstore.R;
import com.xinw.cainiaoappstore.bean.AppInfo;
import com.xinw.cainiaoappstore.presenter.RecommendPresenter;
import com.xinw.cainiaoappstore.presenter.contract.RecommendContract;
import com.xinw.cainiaoappstore.ui.adapter.RecomendAppAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ivan on 16/9/26.
 */

public class RecommendFragment extends Fragment implements RecommendContract.ReView {

    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;


    private ProgressDialog mProgressDialog;
    private RecomendAppAdapter reAdapter;
    private RecommendPresenter recommendPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_recomend, container, false);
        ButterKnife.bind(this, view);

        mProgressDialog = new ProgressDialog(getActivity());
        recommendPresenter = new RecommendPresenter(this);

        initData();
        return view;


    }

    /**
     * 加载App信心
     */
    private void initData() {
        recommendPresenter.requestDatas();

    }

    private void initRecycleView(List<AppInfo> mDatas) {
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
    public void showLoading() {
        mProgressDialog.show();
    }

    @Override
    public void dismissLoading() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
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
    public void showError(String msg) {
        Toast.makeText(getActivity(), "数据错误", Toast.LENGTH_SHORT).show();

    }
}
