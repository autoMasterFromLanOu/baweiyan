package com.ba.dllo.mygift.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.ba.dllo.mygift.R;
import com.ba.dllo.mygift.adapter.HotAdapter;
import com.ba.dllo.mygift.base.BaseFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${巴为焱} on 16/5/18.
 */
public class HotFragment extends BaseFragment {
    private RecyclerView hotRv;
    private HotAdapter hotAdapter;
    private List<Integer> imgDatas;

    @Override
    protected int initLayout() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView(View view) {
        hotRv = bindView(R.id.fragment_hot_rv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        hotRv.setLayoutManager(gridLayoutManager);

    }

    @Override
    protected void initData() {
        imgDatas = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            imgDatas.add(R.mipmap.ic_launcher);
        }
        hotAdapter.setImgDatas(imgDatas);
        hotAdapter = new HotAdapter(context);
        hotRv.setAdapter(hotAdapter);

    }
}
