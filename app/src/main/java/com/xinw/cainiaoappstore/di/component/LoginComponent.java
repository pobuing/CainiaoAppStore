package com.xinw.cainiaoappstore.di.component;

import com.xinw.cainiaoappstore.di.module.LoginModule;
import com.xinw.cainiaoappstore.di.scope.ScopeFragment;
import com.xinw.cainiaoappstore.ui.activity.LoginActivity;

import dagger.Component;

/**
 * byD9ing on 2017/8/25.
 * Describe:
 * good luck
 */
@ScopeFragment
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}
