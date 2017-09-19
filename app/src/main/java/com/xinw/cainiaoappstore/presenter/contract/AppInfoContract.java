package com.xinw.cainiaoappstore.presenter.contract;

import com.xinw.cainiaoappstore.bean.AppInfo;
import com.xinw.cainiaoappstore.bean.IndexBean;
import com.xinw.cainiaoappstore.bean.PageBean;
import com.xinw.cainiaoappstore.ui.activity.BaseView;

/**
 * byD9ing on 2017/8/10.
 * Describe:
 * good luck
 */

public interface AppInfoContract {
    /**
     * 推荐页面 View
     */
    interface ReView extends BaseView {
        /**
         * 显示结果
         */
        void showResult(IndexBean indexBean);

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
    interface  AppDetailView extends BaseView{

        void showAppDetail(AppInfo appInfo);
    }

    interface AppInfoView extends BaseView {

        void showResult(PageBean<AppInfo> pageBean);

        void onLoadMoreComplete();
    }
}
