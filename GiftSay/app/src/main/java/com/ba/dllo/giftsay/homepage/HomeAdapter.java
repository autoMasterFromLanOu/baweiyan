package com.ba.dllo.giftsay.homepage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${巴为焱} on 16/5/9.
 */
public class HomeAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private String[] titles = {"精选", "一周最热", "天天种草", "完美礼物", "谈个恋爱", "送女票", "送爸妈", "送基友", "送闺蜜",
            "送宝贝", "设计感", "科技范", "创意生活", "文艺风", "奇葩搞怪", "萌萌哒"};
//    private List<String> titles;


    public HomeAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titles[position];
    }
}
