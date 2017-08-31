package com.xinw.cainiaoappstore.ui.activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.xinw.cainiaoappstore.R;
import com.xinw.cainiaoappstore.bean.LoginBean;
import com.xinw.cainiaoappstore.di.component.AppComponent;
import com.xinw.cainiaoappstore.di.component.DaggerLoginComponent;
import com.xinw.cainiaoappstore.di.module.LoginModule;
import com.xinw.cainiaoappstore.presenter.LoginPresenter;
import com.xinw.cainiaoappstore.presenter.contract.LoginContract;
import com.xinw.cainiaoappstore.ui.widget.LoadingButton;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.LoginView {


    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.txt_mobi)
    EditText txtMobi;
    @BindView(R.id.view_mobi_wrapper)
    TextInputLayout mViewMobiWrapper;
    @BindView(R.id.txt_password)
    EditText txtPassword;
    @BindView(R.id.view_password_wrapper)
    TextInputLayout viewPasswordWrapper;
    @BindView(R.id.btn_login)
    LoadingButton mBtnLogin;
    @BindView(R.id.activity_login)
    LinearLayout activityLogin;


    @Override
    protected void init() {
        initView();
    }

    /**
     * view init
     */
    private void initView() {
        Observable<CharSequence> mobiObservable = RxTextView.textChanges(txtMobi);
        Observable<CharSequence> passwdObservable = RxTextView.textChanges(txtPassword);
        // TODO: Rx事件
        Observable.combineLatest(mobiObservable, passwdObservable, new Func2<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean call(CharSequence mobi, CharSequence passwd) {
                return isPhoneValid(mobi.toString()) && isPasswordValid(passwd.toString());
            }
        }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                RxView.enabled(mBtnLogin).call(aBoolean);
            }
        });
        RxView.clicks(mBtnLogin).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                mPresenter.login(txtMobi.getText().toString().trim(), txtPassword.getText().toString().trim());
            }
        });
    }

    private boolean isPhoneValid(String phone) {
        return phone.length() == 11;
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent.builder().appComponent(appComponent)
                .loginModule(new LoginModule(this)).build().inject(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    public void showLoading() {
        mBtnLogin.showLoading();

    }

    @Override
    public void dismissLoading() {
        mBtnLogin.showButtonText();

    }

    @Override
    public void showError(String displayMessage) {
        mBtnLogin.showButtonText();

    }

    @Override
    public void checkPhoneError() {
        mViewMobiWrapper.setError("手机号格式不正确");

    }

    @Override
    public void checkPhoneSuccess() {
        mViewMobiWrapper.setError("");
        mViewMobiWrapper.setErrorEnabled(false);
    }

    @Override
    public void loginSuccess(LoginBean bean) {
        this.finish();
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }
}
