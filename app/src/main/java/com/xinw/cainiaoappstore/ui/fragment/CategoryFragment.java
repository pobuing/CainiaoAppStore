package com.xinw.cainiaoappstore.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xinw.cainiaoappstore.R;
import com.xinw.cainiaoappstore.bean.Category;
import com.xinw.cainiaoappstore.common.constant.Constant;
import com.xinw.cainiaoappstore.di.component.AppComponent;
import com.xinw.cainiaoappstore.di.component.DaggerCategoryComponent;
import com.xinw.cainiaoappstore.di.module.CategoryModule;
import com.xinw.cainiaoappstore.presenter.CategoryPresenter;
import com.xinw.cainiaoappstore.presenter.contract.CategoryContract;
import com.xinw.cainiaoappstore.ui.activity.CategoryAppActivity;
import com.xinw.cainiaoappstore.ui.adapter.CategoryAdapter;
import com.xinw.cainiaoappstore.ui.widget.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;


public class CategoryFragment extends ProgressFragment<CategoryPresenter> implements CategoryContract.CategoryView {


    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;
    private CategoryAdapter mAdapter;

    @Override
    protected void init() {
        initRecyclerView();
        mPresenter.getAllCategory();
    }

    @Override
    protected int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerCategoryComponent.builder().appComponent(appComponent)
                .categoryModule(new CategoryModule(this)).build().inject(this);
    }

    @Override
    public void showData(List<Category> categories) {
        mAdapter.addData(categories);

    }

    private void initRecyclerView() {


        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);

        mRecycleView.addItemDecoration(itemDecoration);
        mAdapter = new CategoryAdapter();

        mRecycleView.setAdapter(mAdapter);

        mRecycleView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), CategoryAppActivity.class);
                intent.putExtra(Constant.CATEGORY, mAdapter.getData().get(position));
                startActivity(intent);
            }
        });

    }
}
