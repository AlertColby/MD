package com.hywa.mddemo.tab;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * 作者:  陈庆松
 * 创建时间: 2019\9\10 0010 11:47
 * 邮箱:chen510470614@163.com
 */
public class TabViewPagerAdapter extends FragmentPagerAdapter {

    private Fragment fragments[];

    public TabViewPagerAdapter(FragmentManager fm, Fragment[] fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragments[position].getArguments().getString("title");
    }

    @Override
    public Fragment getItem(int i) {
        return fragments[i];
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.length;
    }
}
