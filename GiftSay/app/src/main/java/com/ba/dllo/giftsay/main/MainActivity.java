package com.ba.dllo.giftsay.main;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import com.ba.dllo.giftsay.R;
import com.ba.dllo.giftsay.base.BaseActivity;
import com.ba.dllo.giftsay.classify.ClassifyFragment;
import com.ba.dllo.giftsay.homepage.HomeFragment;
import com.ba.dllo.giftsay.hot.HotFragment;
import com.ba.dllo.giftsay.person.PersonFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private RadioButton homeBtn, hotBtn, classifyBtn, personBtn;

    //绑定布局
    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    //加载组件
    @Override
    protected void initView() {

        homeBtn = (RadioButton) findViewById(R.id.radio_homeBtn);
        hotBtn = (RadioButton) findViewById(R.id.radio_hotBtn);
        classifyBtn = (RadioButton) findViewById(R.id.radio_classifyBtn);
        personBtn = (RadioButton) findViewById(R.id.radio_personBtn);

        homeBtn.setOnClickListener(this);
        classifyBtn.setOnClickListener(this);
        personBtn.setOnClickListener(this);
        hotBtn.setOnClickListener(this);

    }

    //添加数据
    @Override
    protected void initData() {
        homeBtn.setChecked(true);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.replace_lay, new HomeFragment());
        transaction.commit();
    }


    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTra = fragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.radio_homeBtn:
                fragmentTra.replace(R.id.replace_lay, new HomeFragment());
                break;
            case R.id.radio_hotBtn:
                fragmentTra.replace(R.id.replace_lay, new HotFragment());
                break;
            case R.id.radio_classifyBtn:
                fragmentTra.replace(R.id.replace_lay, new ClassifyFragment());
                break;
            case R.id.radio_personBtn:
                fragmentTra.replace(R.id.replace_lay, new PersonFragment());
                break;
        }
        fragmentTra.commit();
    }
}
