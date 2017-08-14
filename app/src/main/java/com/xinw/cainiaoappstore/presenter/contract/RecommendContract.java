package com.xinw.cainiaoappstore.presenter.contract;

import com.xinw.cainiaoappstore.bean.AppInfo;
import com.xinw.cainiaoappstore.ui.activity.BaseView;

import java.util.List;

/**
 * byD9ing on 2017/8/10.
 * Describe:
 * good luck
 */

public interface RecommendContract {
    /**
     * 推荐页面 View
     */
    interface ReView extends BaseView {
        /**
         * 显示结果
         *
         * @param datas app集合列表
         */
        void showResult(List<AppInfo> datas);

        /**
         * 数据为空
         */
        void showNodata();

        /**
         * 数据加载错误 view
         *
         * @param msg
         */
        void showError(String msg);

    }



}
