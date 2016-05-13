package com.ba.dllo.giftsay.homepage;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.ba.dllo.giftsay.R;
import com.ba.dllo.giftsay.base.BaseFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${巴为焱} on 16/5/9.
 */
public class HomeFragment extends BaseFragment {
    private TabLayout homeTl;
    private ViewPager homeVp;
    private List<Fragment> fragments;
    private List<String> titles;
    private HomeAdapter homeAdapter;


    //加载数据
    @Override
    protected void initData() {

    }

    //加载组件
    @Override
    protected void initView(View view) {
        homeTl = (TabLayout) view.findViewById(R.id.home_frament_tl);
        homeVp = (ViewPager) view.findViewById(R.id.home_fragment_vp);
        initFragment();
        homeAdapter = new HomeAdapter(getChildFragmentManager());
        homeAdapter.setFragments(fragments);
        homeVp.setAdapter(homeAdapter);
        homeTl.setupWithViewPager(homeVp);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new TabFragment());
        fragments.add(new TabFragment());
        fragments.add(new TabFragment());
        fragments.add(new TabFragment());
        fragments.add(new TabFragment());
        fragments.add(new TabFragment());
        fragments.add(new TabFragment());
        fragments.add(new TabFragment());
        fragments.add(new TabFragment());
        fragments.add(new TabFragment());
        fragments.add(new TabFragment());
        fragments.add(new TabFragment());
        fragments.add(new TabFragment());
        fragments.add(new TabFragment());
        fragments.add(new TabFragment());
        fragments.add(new TabFragment());
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }
}
