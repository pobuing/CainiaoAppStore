package com.xinw.cainiaoappstore.presenter;

import android.util.Log;

import com.hwangjr.rxbus.RxBus;
import com.xinw.cainiaoappstore.application.MyApplication;
import com.xinw.cainiaoappstore.bean.LoginBean;
import com.xinw.cainiaoappstore.common.constant.Constant;
import com.xinw.cainiaoappstore.common.rx.RxHttpReponseCompat;
import com.xinw.cainiaoappstore.common.rx.subscriber.ErrorHandlerSubscriber;
import com.xinw.cainiaoappstore.common.util.ACache;
import com.xinw.cainiaoappstore.common.util.VerificationUtils;
import com.xinw.cainiaoappstore.presenter.contract.LoginContract;

import javax.inject.Inject;

/**
 * byD9ing on 2017/8/25.
 * Describe:
 * good luck
 */

public class LoginPresenter extends BasePresenter<LoginContract.IloginModel, LoginContract.LoginView> {

    @Inject
    public LoginPresenter(LoginContract.IloginModel mModel, LoginContract.LoginView mView) {
        super(mModel, mView);
    }

    public void login(String phone, String pwd) {
        Log.d(MyApplication.TAG, "login: " + phone);
        Log.d(MyApplication.TAG, "login: " + pwd);
        if (!VerificationUtils.matcherPhoneNum(phone)) {
            mView.checkPhoneError();
        } else {
            mView.checkPhoneSuccess();
        }
        mModel.login(phone, pwd)
                .compose(RxHttpReponseCompat.<LoginBean>compatResult())
                .subscribe(new ErrorHandlerSubscriber<LoginBean>(mContext) {
                    @Override
                    public void onStart() {
                        mView.showLoading();

                    }

                    @Override
                    public void onCompleted() {
                        mView.dismissLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.dismissLoading();
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        mView.loginSuccess(loginBean);
                        saveUser(loginBean);
                        RxBus.get().post(loginBean.getUser());
                    }
                });
    }

    /**
     * save User
     *
     * @param loginBean
     */
    private void saveUser(LoginBean loginBean) {
        ACache aCache = ACache.get(mContext);
        aCache.put(Constant.TOKEN, loginBean.getToken());
        aCache.put(Constant.USER, loginBean.getUser());
    }


}
