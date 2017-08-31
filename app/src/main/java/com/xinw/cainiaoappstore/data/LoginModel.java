package com.xinw.cainiaoappstore.data;

import com.xinw.cainiaoappstore.bean.BaseBean;
import com.xinw.cainiaoappstore.bean.LoginBean;
import com.xinw.cainiaoappstore.bean.requestbean.LoginRequestBean;
import com.xinw.cainiaoappstore.data.http.ApiService;
import com.xinw.cainiaoappstore.presenter.contract.LoginContract;

import rx.Observable;

/**
 * byD9ing on 2017/8/25.
 * Describe:
 * good luck
 */

public class LoginModel implements LoginContract.IloginModel {
    private ApiService apiService;

    public LoginModel(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<BaseBean<LoginBean>> login(String phone, String passwd) {
        LoginRequestBean param = new LoginRequestBean();
        param.setEmail(phone);
        param.setPassword(passwd);
        return apiService.login(param);
    }
}
