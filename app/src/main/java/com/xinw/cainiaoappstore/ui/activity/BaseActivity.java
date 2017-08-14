package com.xinw.cainiaoappstore.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xinw.cainiaoappstore.application.MyApplication;
import com.xinw.cainiaoappstore.di.component.AppComponent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * byD9ing on 2017/8/14.
 * Describe: BaseActivity
 * good luck
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mUnBind;
    private MyApplication mApplication;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: 抽象布局设置
        setContentView(setLayoutId());
        // TODO: 组件依赖注入
        mUnBind = ButterKnife.bind(this);
        mApplication = ((MyApplication) getApplication());
        setupActivityComponent(mApplication.getmAppComponent());
        init();
    }

    /**
     * 初始化方法
     */
    protected abstract void init();

    /**
     * 设置Activity Component
     * @param appComponent
     */
    protected abstract void setupActivityComponent(AppComponent appComponent);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBind != null) {
            // TODO: 取消注入
            mUnBind.unbind();
        }
    }

    protected abstract int setLayoutId();

}
