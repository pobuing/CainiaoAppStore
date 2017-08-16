package com.xinw.cainiaoappstore.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.xinw.cainiaoappstore.R;
import com.xinw.cainiaoappstore.common.constant.Constant;
import com.xinw.cainiaoappstore.common.util.ACache;
import com.xinw.cainiaoappstore.ui.adapter.GuideFragmentAdapter;
import com.xinw.cainiaoappstore.ui.fragment.GuideFragment;
import com.xinw.cainiaoappstore.ui.widget.CircleIndicator;
import com.xinw.cainiaoappstore.ui.widget.DepthPageTransformer;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.btn_enter)
    Button btnEnter;
    @BindView(R.id.indicator)
    CircleIndicator indicator;
    @BindView(R.id.activity_guide)
    RelativeLayout activityGuide;
    private GuideFragmentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        // TODO: 注入
        ButterKnife.bind(this);
        initData();
        btnEnter.setOnClickListener(this);
    }

    /**
     * 数据初始化
     */
    private void initData() {
        // TODO: fragments list
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(GuideFragment.newInstance(R.drawable.guide_1, R.color.color_bg_guide1, R.string.guide_1));
        fragments.add(GuideFragment.newInstance(R.drawable.guide_2, R.color.color_bg_guide2, R.string.guide_2));
        fragments.add(GuideFragment.newInstance(R.drawable.guide_3, R.color.color_bg_guide3, R.string.guide_3));
        // TODO: construct adapter
        mAdapter = new GuideFragmentAdapter(getSupportFragmentManager());
        mAdapter.setFragments(fragments);
        viewpager.setCurrentItem(0);
        viewpager.setOffscreenPageLimit(mAdapter.getCount());
        viewpager.setAdapter(mAdapter);
        viewpager.addOnPageChangeListener(this);
        // TODO: set Apdater transfor
        viewpager.setPageTransformer(true, new DepthPageTransformer());
        indicator.setViewPager(viewpager);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        btnEnter.setVisibility(position == mAdapter.getCount() - 1 ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_enter:
                ACache.get(this).put(Constant.IS_SHOW_GUIDE, "0");
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }
}
