package com.ba.dllo.giftsay.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ${巴为焱} on 16/5/9.
 */
public abstract class BaseFragment extends Fragment {
    protected Context context;


    //context从依附的Activity上获取context对象
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    //初始化视图
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(initLayout(), container, false);
    }

    //加载组件
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    protected abstract void initView(View view);

    //加载数据
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract void initData();

    

    protected abstract int initLayout();

    /**
     * 这个方法使组件实例化不需要转型
     * <p/>
     * 使用方式：
     * TextView textView = bindView(R.id.tv);
     * 这样的话使用这个方法的时候是不需要强转的
     */
    protected <T extends View> T bindView(int id) {
        return (T) getView().findViewById(id);
    }
}
