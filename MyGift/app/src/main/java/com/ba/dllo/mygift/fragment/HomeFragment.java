package com.ba.dllo.mygift.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ba.dllo.mygift.R;
import com.ba.dllo.mygift.adapter.HomeAdapter;
import com.ba.dllo.mygift.base.BaseFragment;
import com.ba.dllo.mygift.bean.TabTitleBean;
import com.ba.dllo.mygift.utils.VolleySinge;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${巴为焱} on 16/5/18.
 */
public class HomeFragment extends BaseFragment {
    private TabLayout homeTl;
    private ViewPager homeVp;
    private List<Fragment> fragments;
    private HomeAdapter homeAdapter;
    private List<TabTitleBean.DataBean.ChannelsBean> titles;

    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        homeTl = bindView(R.id.fragment_home_tbl);
        homeVp = bindView(R.id.fragment_home_vp);
    }


    @Override
    protected void initData() {
        titles = new ArrayList<>();

        VolleySinge.addRequest("http://api.liwushuo.com/v2/channels/preset?gender=1&generation=4", TabTitleBean.class,
                new Response.Listener<TabTitleBean>() {
                    @Override
                    public void onResponse(TabTitleBean response) {
                        titles = response.getData().getChannels();
                        for (int i = 0; i < titles.size(); i++) {
                            homeTl.addTab(homeTl.newTab().setTag(titles.get(i).getName()));
                        }
                        fragments = new ArrayList<>();
                        for (int i = 0; i < titles.size() - 1; i++) {
                            fragments.add(new TabFragment());
                        }
                        homeAdapter = new HomeAdapter(getChildFragmentManager(), fragments, titles);
                        homeVp.setAdapter(homeAdapter);
                        homeTl.setupWithViewPager(homeVp);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

    }

}
