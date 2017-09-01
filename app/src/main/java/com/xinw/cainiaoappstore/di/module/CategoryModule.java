package com.xinw.cainiaoappstore.di.module;

import com.xinw.cainiaoappstore.data.CategoryModel;
import com.xinw.cainiaoappstore.data.http.ApiService;
import com.xinw.cainiaoappstore.presenter.contract.CategoryContract;

import dagger.Module;
import dagger.Provides;

/**
 * byD9ing on 2017/8/25.
 * Describe: di ---> Login
 * good luck
 */
@Module
public class CategoryModule {
    private CategoryContract.CategoryView mView;

    public CategoryModule(CategoryContract.CategoryView mView) {
        this.mView = mView;
    }

    @Provides
    public CategoryContract.CategoryView provideLoginView() {
        return mView;
    }

    @Provides
    public CategoryContract.ICategoryModel provideICategoryModel(ApiService apiService) {
        return new CategoryModel(apiService);
    }

}
