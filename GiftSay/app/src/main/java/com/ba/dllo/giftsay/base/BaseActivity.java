package com.ba.dllo.giftsay.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by ${巴为焱} on 16/5/9.
 */
public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
        initData();
    }
    /**
     * 这是加载布局的抽象方法
     */
    protected abstract int getLayout();


    /**
     * 这个是加载组件的抽象方法
     */
    protected abstract void initView();


    /**
     * 这个是加载数据的抽象方法
     */
    protected abstract void initData();

    /**
     * 这个方法使组件实例化不需要转型
     * <p/>
     * 使用方式：
     * TextView textView = bindView(R.id.tv);
     * 这样的话使用这个方法的时候是不需要强转的
     */
    protected <T extends View> T bindView(int id) {
        return (T) findViewById(id);
    }
}
