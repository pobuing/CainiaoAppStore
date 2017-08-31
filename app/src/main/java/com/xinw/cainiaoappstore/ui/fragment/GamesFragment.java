package com.xinw.cainiaoappstore.ui.fragment;

import com.xinw.cainiaoappstore.di.component.AppComponent;
import com.xinw.cainiaoappstore.di.component.DaggerAppInfoComponent;
import com.xinw.cainiaoappstore.di.module.AppInfoModule;
import com.xinw.cainiaoappstore.presenter.AppInfoPresenter;
import com.xinw.cainiaoappstore.ui.adapter.AppInfoAdapter;


/**
 * Created by Ivan on 16/9/26.
 */

public class GamesFragment extends BaseAppInfoFragment {


    @Override
    int type() {
        return AppInfoPresenter.GAME;
    }

    @Override
    protected AppInfoAdapter buildApapter() {
        return AppInfoAdapter.build().showPosition(false)
                .showShowBrief(true)
                .showCategoryName(true).build();
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder().appComponent(appComponent)
                .appInfoModule(new AppInfoModule(this))
                .build().injectGameFragment(this);
    }
}
