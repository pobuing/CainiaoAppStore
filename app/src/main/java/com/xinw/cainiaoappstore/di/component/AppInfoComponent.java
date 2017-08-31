package com.xinw.cainiaoappstore.di.component;

import com.xinw.cainiaoappstore.di.module.AppInfoModule;
import com.xinw.cainiaoappstore.di.scope.ScopeFragment;
import com.xinw.cainiaoappstore.ui.fragment.GamesFragment;
import com.xinw.cainiaoappstore.ui.fragment.TopListFragment;

import dagger.Component;

/**
 * byD9ing on 2017/8/23.
 * Describe:
 * good luck
 */
@ScopeFragment
@Component(modules = AppInfoModule.class, dependencies = AppComponent.class)
public interface AppInfoComponent {

    void injectTopListFragment(TopListFragment fragment);

    void injectGameFragment(GamesFragment fragment);
}
