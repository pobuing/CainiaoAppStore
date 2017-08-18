package com.xinw.cainiaoappstore.ui.activity;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.xinw.cainiaoappstore.R;
import com.xinw.cainiaoappstore.common.util.PermissionUtil;
import com.xinw.cainiaoappstore.di.component.AppComponent;
import com.xinw.cainiaoappstore.ui.adapter.ViewPageAdapter;

import butterknife.BindView;
import rx.functions.Action1;

public class MainActivity extends BaseActivity {


    @BindView(R.id.dr_layout)
    DrawerLayout drLayout;
    @BindView(R.id.navigaview_left)
    NavigationView navigaviewLeft;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.vp_main)
    ViewPager vpMain;

    private View headView;
    private ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void init() {
        PermissionUtil.requestPermisson(this, Manifest.permission.READ_PHONE_STATE).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                // TODO: permission true
                if (aBoolean) {
                    initDrawerLayout();
                    initTabLayout();
                    // TODO: false
                }else {

                }
            }
        });


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
        toolbar.inflateMenu(R.menu.toolbar_menu);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drLayout, toolbar, R.string.open, R.string.close);
        // TODO: 同步状态
        actionBarDrawerToggle.syncState();
        drLayout.addDrawerListener(actionBarDrawerToggle);
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
        headView = navigaviewLeft.getHeaderView(0);
        headView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        navigaviewLeft.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_app_update:
                        Toast.makeText(MainActivity.this, "更新", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_message:
                        Toast.makeText(MainActivity.this, "消息", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_setting:
                        Toast.makeText(MainActivity.this, "设置", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }
}
