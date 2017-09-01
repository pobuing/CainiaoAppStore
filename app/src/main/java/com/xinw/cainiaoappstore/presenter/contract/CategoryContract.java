package com.xinw.cainiaoappstore.presenter.contract;

import com.xinw.cainiaoappstore.bean.BaseBean;
import com.xinw.cainiaoappstore.bean.Category;
import com.xinw.cainiaoappstore.ui.activity.BaseView;

import java.util.List;

import rx.Observable;


/**
 * byD9ing on 2017/8/25.
 * Describe:Login Contract
 * good luck
 */

public interface CategoryContract {
    interface ICategoryModel {
        Observable<BaseBean<List<Category>>> getCategories();

    }

    interface CategoryView extends BaseView {

        public void showData(List<Category> categories);

    }
}
