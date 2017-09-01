package com.xinw.cainiaoappstore.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinw.cainiaoappstore.R;
import com.xinw.cainiaoappstore.bean.Category;
import com.xinw.cainiaoappstore.common.constant.Constant;
import com.xinw.cainiaoappstore.common.imageloader.ImageLoader;

/**
 * byD9ing on 2017/9/1.
 * Describe:
 * good luck
 */

public class CategoryAdapter extends BaseQuickAdapter<Category, BaseViewHolder> {
    public CategoryAdapter() {
        super(R.layout.template_category);
    }

    @Override
    protected void convert(BaseViewHolder helper, Category item) {
        helper.setText(R.id.text_name, item.getName());

        ImageLoader.load(Constant.BASE_IMG_URL + item.getIcon(), (ImageView) helper.getView(R.id.img_icon));
    }
}
