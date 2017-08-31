package com.xinw.cainiaoappstore.di.module;

import com.xinw.cainiaoappstore.data.LoginModel;
import com.xinw.cainiaoappstore.data.http.ApiService;
import com.xinw.cainiaoappstore.presenter.contract.LoginContract;

import dagger.Module;
import dagger.Provides;

/**
 * byD9ing on 2017/8/25.
 * Describe: di ---> Login
 * good luck
 */
@Module
public class LoginModule {
    private LoginContract.LoginView mView;

    public LoginModule(LoginContract.LoginView mView) {
        this.mView = mView;
    }

    @Provides
    public LoginContract.LoginView provideLoginView() {
        return mView;
    }

    @Provides
    public LoginContract.IloginModel provideILoginModel(ApiService apiService) {
        return new LoginModel(apiService);
    }

}
