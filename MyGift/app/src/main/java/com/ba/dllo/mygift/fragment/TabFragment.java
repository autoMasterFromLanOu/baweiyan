package com.ba.dllo.mygift.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ba.dllo.mygift.R;
import com.ba.dllo.mygift.adapter.TabAdapter;
import com.ba.dllo.mygift.adapter.TabListHeaderRvAdapter;
import com.ba.dllo.mygift.base.BaseFragment;
import com.ba.dllo.mygift.bean.TabLvBean;
import com.ba.dllo.mygift.bean.TabRcBean;
import com.ba.dllo.mygift.utils.VolleySinge;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ${巴为焱} on 16/5/18.
 */
public class TabFragment extends BaseFragment {
    private ListView tabLv;
    private TabAdapter tabAdapter;
    private RecyclerView tabListHeaderRv;
    private TabListHeaderRvAdapter tabListHeaderRvAdapter;
    private List<TabRcBean.DataBean.SecondaryBannersBean> tabRcBeans;
    //    private TabRcBean tabRcBeans;
    private List<TabLvBean.DataBean.ItemsBean> tabLvDatas;


    @Override
    protected int initLayout() {
        return R.layout.fragment_tab;
    }

    @Override
    protected void initView(View view) {
        tabLv = bindView(R.id.framgent_tab_lv);

//        //将轮播图作为头布局添加到listView中
//        View TabListHeaderVpView = LayoutInflater.from(context).inflate(R.layout.item_tablistheadervp, null);
//        tabLv.addHeaderView(TabListHeaderVpView);

        //将横向的RecyclerView所在的布局文件作为头布局添加到ListView中
        View TabListHeaderRvView = LayoutInflater.from(context).inflate(R.layout.item_tablistheaderrv, null);
        tabLv.addHeaderView(TabListHeaderRvView);
        //生成一个横向的RecycleView
        tabListHeaderRv = (RecyclerView) TabListHeaderRvView.findViewById(R.id.item_tablist_headerrv);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        tabListHeaderRv.setLayoutManager(manager);

    }


    @Override
    protected void initData() {
        tabLvDatas = new ArrayList<>();
        tabRcBeans = new ArrayList<>();
        //这是横向RecycleView头布局获取网络数据
        VolleySinge.addRequest("http://api.liwushuo.com/v2/secondary_banners?gender=2&generation=1",
                TabRcBean.class, new Response.Listener<TabRcBean>() {
                    @Override
                    public void onResponse(TabRcBean response) {
                        tabRcBeans = response.getData().getSecondary_banners();
                        tabListHeaderRvAdapter = new TabListHeaderRvAdapter(context);
                        //将获取的数据添加到适配器
                        tabListHeaderRvAdapter.setTabReBeans(tabRcBeans);
                        tabListHeaderRv.setAdapter(tabListHeaderRvAdapter);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        //ListView获取网络数据
        VolleySinge.addRequest("http://api.liwushuo.com/v2/channels/103/items?limit=20&ad=2&gender=2&offset=0&generation=1"
                , TabLvBean.class, new Response.Listener<TabLvBean>() {
                    @Override
                    public void onResponse(TabLvBean response) {
                        tabLvDatas = response.getData().getItems();
                        tabAdapter = new TabAdapter(context);
                        tabAdapter.setImgDatas(tabLvDatas);
                        tabLv.setAdapter(tabAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });


    }


}
