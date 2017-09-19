package com.xinw.cainiaoappstore.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.xinw.cainiaoappstore.ui.fragment.CategoryAppFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * byD9ing on 2017/9/18.
 * Describe:
 * good luck
 */

public class CategoryAppViewPagerAdapter extends FragmentStatePagerAdapter {

    private final int categoryId;
    private List<String> titles = new ArrayList<String>();

    public CategoryAppViewPagerAdapter(FragmentManager fm, int categoryId) {
        super(fm);
        this.categoryId = categoryId;
        // TODO: set List data
        titles.add("精品");
        titles.add("排行");
        titles.add("新品");
    }

    @Override
    public Fragment getItem(int position) {
        return new CategoryAppFragment(categoryId, position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
