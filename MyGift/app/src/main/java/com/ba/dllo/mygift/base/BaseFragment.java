package com.ba.dllo.mygift.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ${巴为焱} on 16/5/17.
 */
public abstract class BaseFragment extends Fragment {
    protected Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(initLayout(), container, false);
    }

    //此方法用于fragment加载布局
    protected abstract int initLayout();


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    //用来加载组件
    protected abstract void initView(View view);

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    //用来加载数据
    protected abstract void initData();

    //这个方法使组件在实例化的时候不需要转型
    //使用方法：TextView textView = bindView(R.id.tv);
    protected <T extends View> T bindView(int id) {
        return (T) getView().findViewById(id);
    }

}
