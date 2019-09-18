package com.hywa.mddemo.parallelspace;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 作者:  陈庆松
 * 创建时间: 2019\9\17 0017 09:33
 * 邮箱:chen510470614@163.com
 */
public class VPAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public VPAdapter(FragmentManager fm , List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
