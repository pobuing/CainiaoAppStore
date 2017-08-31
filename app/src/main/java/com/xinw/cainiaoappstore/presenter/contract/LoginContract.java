package com.xinw.cainiaoappstore.presenter.contract;

import com.xinw.cainiaoappstore.bean.BaseBean;
import com.xinw.cainiaoappstore.bean.LoginBean;
import com.xinw.cainiaoappstore.ui.activity.BaseView;

import rx.Observable;


/**
 * byD9ing on 2017/8/25.
 * Describe:Login Contract
 * good luck
 */

public interface LoginContract {
    interface IloginModel {
        Observable<BaseBean<LoginBean>> login(String phone, String passwd);
    }

    interface LoginView extends BaseView {
        void checkPhoneError();

        void checkPhoneSuccess();

        void loginSuccess(LoginBean bean);

    }
}
