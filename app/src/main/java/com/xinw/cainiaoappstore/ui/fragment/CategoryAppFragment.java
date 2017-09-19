package com.xinw.cainiaoappstore.ui.fragment;

import android.annotation.SuppressLint;

import com.xinw.cainiaoappstore.di.component.AppComponent;
import com.xinw.cainiaoappstore.di.component.DaggerAppInfoComponent;
import com.xinw.cainiaoappstore.di.module.AppInfoModule;
import com.xinw.cainiaoappstore.ui.adapter.AppInfoAdapter;

/**
 * byD9ing on 2017/9/18.
 * Describe:
 * good luck
 */

@SuppressLint("ValidFragment")
public class CategoryAppFragment extends BaseAppInfoFragment {
    private int categoryId;
    private int mFlagType;

    @SuppressLint("ValidFragment")
    public CategoryAppFragment(int categoryId, int mFlagType) {
        this.categoryId = categoryId;
        this.mFlagType = mFlagType;
    }

    @Override
    protected void init() {
        mPresenter.requestCategoryApps(categoryId, page, mFlagType);
        initRecyclerView();

    }

    @Override
    int type() {
        return 0;
    }

    @Override
    protected AppInfoAdapter buildApapter() {
        return AppInfoAdapter.build().showPosition(false).showShowBrief(true).showCategoryName(false).build();
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder().appComponent(appComponent)
                .appInfoModule(new AppInfoModule(this)).build()
                .injectCategoryAppFragment(this);
    }
}
