package com.xinw.cainiaoappstore.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.xinw.cainiaoappstore.ui.bean.FragmentInfo;
import com.xinw.cainiaoappstore.ui.fragment.CategoryFragment;
import com.xinw.cainiaoappstore.ui.fragment.GamesFragment;
import com.xinw.cainiaoappstore.ui.fragment.RankingFragment;
import com.xinw.cainiaoappstore.ui.fragment.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * byD9ing on 2017/8/9.
 * Describe:
 * good luck
 */

public class ViewPageAdapter extends FragmentStatePagerAdapter {

    private List<FragmentInfo> mFragments = new ArrayList<FragmentInfo>();


    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
        initFragments();
    }

    /**
     * add fragments
     */
    private void initFragments() {

        mFragments.add(new FragmentInfo("推荐", RecommendFragment.class));
        mFragments.add(new FragmentInfo("排行", RankingFragment.class));
        mFragments.add(new FragmentInfo("游戏", GamesFragment.class));
        mFragments.add(new FragmentInfo("分类", CategoryFragment.class));
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) mFragments.get(position).getFragment().newInstance();
            return fragment;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }

}
