package com.xinw.cainiaoappstore.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.xinw.cainiaoappstore.R;
import com.xinw.cainiaoappstore.bean.User;
import com.xinw.cainiaoappstore.common.constant.Constant;
import com.xinw.cainiaoappstore.common.font.AppStoreFont;
import com.xinw.cainiaoappstore.common.imageloader.GlideCircleTransform;
import com.xinw.cainiaoappstore.common.util.ACache;
import com.xinw.cainiaoappstore.common.util.PermissionUtil;
import com.xinw.cainiaoappstore.di.component.AppComponent;
import com.xinw.cainiaoappstore.ui.adapter.ViewPageAdapter;

import butterknife.BindView;
import rx.functions.Action1;

public class MainActivity extends BaseActivity {


    @BindView(R.id.dr_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navigaview_left)
    NavigationView mNavigationView;
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.vp_main)
    ViewPager vpMain;

    private View headerView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ImageView mUserHeadView;
    private TextView mTextUserName;


    @Override
    protected void init() {
        RxBus.get().register(this);
        PermissionUtil.requestPermisson(this, Manifest.permission.READ_PHONE_STATE).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                // TODO: permission true
                if (aBoolean) {
                    initDrawerLayout();
                    initTabLayout();
                    initUser();
                } else {

                }
            }
        });


    }

    @Subscribe
    public void getUserLogin(User user) {
        initUserHeadView(user);
    }

    private void initUserHeadView(User user) {
        Glide.with(this).load(user.getLogo_url()).transform(new GlideCircleTransform(this)).into(mUserHeadView);

        mTextUserName.setText(user.getUsername());
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }


    /**
     * 初始化TavLayout
     */
    private void initTabLayout() {
        // toolbar填充菜单
        mToolBar.inflateMenu(R.menu.toolbar_menu);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.open, R.string.close);
        // TODO: 同步状态
        actionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager());
        // TODO: 设置适配器
        vpMain.setAdapter(adapter);
        // TODO: 设置ViewPager
        tabLayout.setupWithViewPager(vpMain);
    }

    /**
     * 初始化抽屉布局
     */
    private void initDrawerLayout() {


        headerView = mNavigationView.getHeaderView(0);

        mUserHeadView = (ImageView) headerView.findViewById(R.id.img_avatar);
        mUserHeadView.setImageDrawable(new IconicsDrawable(this, AppStoreFont.Icon.cniao_head).colorRes(R.color.white));

        mTextUserName = (TextView) headerView.findViewById(R.id.txt_username);


        mNavigationView.getMenu().findItem(R.id.menu_app_update).setIcon(new IconicsDrawable(this, Ionicons.Icon.ion_ios_loop));
        mNavigationView.getMenu().findItem(R.id.menu_download_manager).setIcon(new IconicsDrawable(this, AppStoreFont.Icon.cniao_download));
        mNavigationView.getMenu().findItem(R.id.menu_app_uninstall).setIcon(new IconicsDrawable(this, Ionicons.Icon.ion_ios_trash_outline));
        mNavigationView.getMenu().findItem(R.id.menu_setting).setIcon(new IconicsDrawable(this, Ionicons.Icon.ion_ios_gear_outline));

        mNavigationView.getMenu().findItem(R.id.menu_logout).setIcon(new IconicsDrawable(this, AppStoreFont.Icon.cniao_shutdown));

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {

                    case R.id.menu_logout:

                        logout();

                        break;


                }


                return false;
            }
        });


        mToolBar.inflateMenu(R.menu.toolbar_menu);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.open, R.string.close);

        drawerToggle.syncState();

        mDrawerLayout.addDrawerListener(drawerToggle);


    }

    private void initUser() {

        Object objUser = ACache.get(this).getAsObject(Constant.USER);
        // TODO: 判断是否存储，不存储则不跳转
        if (objUser == null) {

            headerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            });

        } else {

            User user = (User) objUser;
            initUserHeadView(user);

        }


    }

    private void logout() {
        ACache aCache = ACache.get(this);

        aCache.put(Constant.TOKEN, "");
        aCache.put(Constant.USER, "");

        mUserHeadView.setImageDrawable(new IconicsDrawable(this, AppStoreFont.Icon.cniao_head).colorRes(R.color.white));
        mTextUserName.setText("未登录");

        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        Toast.makeText(MainActivity.this, "您已退出登录", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }
}
