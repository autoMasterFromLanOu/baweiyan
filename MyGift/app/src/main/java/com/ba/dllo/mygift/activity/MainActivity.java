package com.ba.dllo.mygift.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.ba.dllo.mygift.R;
import com.ba.dllo.mygift.base.BaseActivity;
import com.ba.dllo.mygift.fragment.ClassifyFragment;
import com.ba.dllo.mygift.fragment.HomeFragment;
import com.ba.dllo.mygift.fragment.HotFragment;
import com.ba.dllo.mygift.fragment.PersonFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    private RadioButton homeBtn, hotBtn, classifyBtn, personBtn;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        homeBtn = bindView(R.id.radio_home);
        hotBtn = bindView(R.id.radio_hot);
        classifyBtn = bindView(R.id.radio_classify);
        personBtn = bindView(R.id.radio_person);

        homeBtn.setOnClickListener(this);
        hotBtn.setOnClickListener(this);
        classifyBtn.setOnClickListener(this);
        personBtn.setOnClickListener(this);


    }

    @Override
    protected void initData() {
        homeBtn.setChecked(true);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.replace_fl, new HomeFragment());
        fragmentTransaction.commit();

    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        switch (v.getId()) {
            case R.id.radio_home:
                ft.replace(R.id.replace_fl, new HomeFragment());
                break;
            case R.id.radio_hot:
                ft.replace(R.id.replace_fl, new HotFragment());
                break;
            case R.id.radio_classify:
                ft.replace(R.id.replace_fl, new ClassifyFragment());
                break;
            case R.id.radio_person:
                ft.replace(R.id.replace_fl, new PersonFragment());
                break;
        }
        ft.commit();
    }
}
