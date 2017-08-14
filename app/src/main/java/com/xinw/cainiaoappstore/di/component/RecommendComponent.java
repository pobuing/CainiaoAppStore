package com.xinw.cainiaoappstore.di.component;

import com.xinw.cainiaoappstore.di.module.RecommendModule;
import com.xinw.cainiaoappstore.di.scope.ScopeFragment;
import com.xinw.cainiaoappstore.ui.fragment.RecommendFragment;

import dagger.Component;

/**
 * byD9ing on 2017/8/11.
 * Describe: Recommend 注入器
 * good luck
 */
@ScopeFragment
@Component(modules = RecommendModule.class, dependencies = AppComponent.class)
public interface RecommendComponent {
    void inject(RecommendFragment fragment);

}
