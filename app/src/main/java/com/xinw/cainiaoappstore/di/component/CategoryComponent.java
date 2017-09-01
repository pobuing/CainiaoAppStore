package com.xinw.cainiaoappstore.di.component;

import com.xinw.cainiaoappstore.di.module.CategoryModule;
import com.xinw.cainiaoappstore.di.scope.ScopeFragment;
import com.xinw.cainiaoappstore.ui.fragment.CategoryFragment;

import dagger.Component;

/**
 * byD9ing on 2017/8/25.
 * Describe:
 * good luck
 */
@ScopeFragment
@Component(modules = CategoryModule.class, dependencies = AppComponent.class)
public interface CategoryComponent {
    void inject(CategoryFragment fragment);
}
