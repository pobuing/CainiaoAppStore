package com.xinw.cainiaoappstore.di.component;

import com.xinw.cainiaoappstore.di.module.AppDetailModule;
import com.xinw.cainiaoappstore.di.scope.ScopeFragment;
import com.xinw.cainiaoappstore.ui.fragment.AppDetailFragment;

import dagger.Component;

/**
 * byD9ing on 2017/9/19.
 * Describe:
 * good luck
 */
@ScopeFragment
@Component(modules = AppDetailModule.class, dependencies = AppComponent.class)
public interface AppDetailComponent {
    void inject(AppDetailFragment fragment);

}
