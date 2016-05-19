package com.ba.dllo.mygift.base;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ba.dllo.mygift.R;

/**
 * Created by ${巴为焱} on 16/5/17.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
        initData();
    }

    //加载布局的抽象方法
    protected abstract int getLayout();

    //加载组件的抽象方法
    protected abstract void initView();

    //加载数据的抽象方法
    protected abstract void initData();

    //这个方法使组件在实例化的时候不需要转型
    //使用方法：TextView textView = bindView(R.id.tv);
    protected <T extends View> T bindView(int id) {
        return (T) findViewById(id);
    }

}
