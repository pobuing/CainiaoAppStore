package com.xinw.cainiaoappstore.presenter;

import com.xinw.cainiaoappstore.bean.Category;
import com.xinw.cainiaoappstore.common.rx.RxHttpReponseCompat;
import com.xinw.cainiaoappstore.common.rx.subscriber.ProgressSubscriber;
import com.xinw.cainiaoappstore.presenter.contract.CategoryContract;

import java.util.List;

import javax.inject.Inject;

/**
 * byD9ing on 2017/8/25.
 * Describe:
 * good luck
 */

public class CategoryPresenter extends BasePresenter<CategoryContract.ICategoryModel, CategoryContract.CategoryView> {

    @Inject
    public CategoryPresenter(CategoryContract.ICategoryModel mModel, CategoryContract.CategoryView mView) {
        super(mModel, mView);
    }

    public void getAllCategory() {
        mModel.getCategories().compose(RxHttpReponseCompat.<List<Category>>compatResult())
                .subscribe(new ProgressSubscriber<List<Category>>(mContext, mView) {
                    @Override
                    public void onNext(List<Category> categories) {
                        mView.showData(categories);
                    }
                });
    }

}
