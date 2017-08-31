package com.xinw.cainiaoappstore.ui.activity;

/**
 * byD9ing on 2017/8/10.
 * Describe: MVP 架构中的 BaseView
 * good luck
 */

public interface BaseView {
    /**
     * 显示进度对话框
     */
    void showLoading();

    /**
     * 关闭进度对话框
     */
    void dismissLoading();

    /**
     * 显示错误信息
     *
     * @param displayMessage
     */
    void showError(String displayMessage);

}
