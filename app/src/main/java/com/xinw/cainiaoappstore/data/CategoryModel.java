package com.xinw.cainiaoappstore.data;

import com.xinw.cainiaoappstore.bean.BaseBean;
import com.xinw.cainiaoappstore.bean.Category;
import com.xinw.cainiaoappstore.data.http.ApiService;
import com.xinw.cainiaoappstore.presenter.contract.CategoryContract;

import java.util.List;

import rx.Observable;

/**
 * byD9ing on 2017/8/25.
 * Describe:
 * good luck
 */

public class CategoryModel implements CategoryContract.ICategoryModel {
    private ApiService apiService;

    public CategoryModel(ApiService apiService) {
        this.apiService = apiService;
    }


    @Override
    public Observable<BaseBean<List<Category>>> getCategories() {
        return apiService.getCategories();
    }
}
