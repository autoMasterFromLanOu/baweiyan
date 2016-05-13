package com.ba.dllo.giftsay.homepage;

import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ba.dllo.giftsay.R;
import com.ba.dllo.giftsay.main.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * Created by ${巴为焱} on 16/5/13.
 */
public class TabFragment extends Fragment {

    private ListView tabLv;
    private LayoutInflater inflater;
    private ViewPager myViewPager;
    //用来存放小圆点图片
    private List<ImageView> dotViewList;
    //用来存放轮播效果的图片
    private List<ImageView> imageViewList;
    //用来存放listView中的图片
//    private List<ImageView> imgDatas;
    private List<Integer> imgDatas;
    LinearLayout dotLayout;
    //当前显示的页面 初始值为0
    private int currentItem = 0;

    //是否轮播
    boolean isAutoPlay = true;
    private ScheduledExecutorService scheduledExecutorService;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                myViewPager.setCurrentItem(currentItem);
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLv = (ListView) view.findViewById(R.id.tab_lv);
        //绑定listView的头布局
        View listHeaderView = getLayoutInflater(null).inflate(R.layout.item_listviewheader, null);
        tabLv.addHeaderView(listHeaderView);

        myViewPager = (ViewPager) view.findViewById(R.id.myViewPager);
        dotLayout = (LinearLayout) view.findViewById(R.id.dotLayout);
        inflater = LayoutInflater.from(getContext());
        dotLayout.removeAllViews();
        initView();
        if (isAutoPlay) {
            startPlay();
        }

    }

    private void initView() {

        dotViewList = new ArrayList<ImageView>();
        imageViewList = new ArrayList<ImageView>();
        //给listView添加假数据
        imgDatas = new ArrayList<>();
        imgDatas.add(R.mipmap.ic_launcher);
        imgDatas.add(R.mipmap.ic_tab_classify_normal);

        for (int i = 0; i < 4; i++) {
            ImageView dotView = new ImageView(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            params.leftMargin = 15;//设置小圆点的外边距
            params.rightMargin = 15;

            params.height = 40;//设置小圆点的大小
            params.width = 40;
            if (i == 0) {
                dotView.setBackgroundResource(R.mipmap.ic_launcher);
            } else {
                dotView.setBackgroundResource(R.mipmap.ic_launcher);
            }
            dotLayout.addView(dotView, params);
            dotViewList.add(dotView);
            //上面是动态添加了四个小圆点
        }
        ImageView img1 = (ImageView) inflater.inflate(R.layout.scroll_view_item, null);
        ImageView img2 = (ImageView) inflater.inflate(R.layout.scroll_view_item, null);
        ImageView img3 = (ImageView) inflater.inflate(R.layout.scroll_view_item, null);
        ImageView img4 = (ImageView) inflater.inflate(R.layout.scroll_view_item, null);

        img1.setBackgroundResource(R.mipmap.ic_launcher);
        img2.setBackgroundResource(R.mipmap.ic_launcher);
        img3.setBackgroundResource(R.mipmap.ic_launcher);
        img4.setBackgroundResource(R.mipmap.ic_launcher);

        imageViewList.add(img1);
        imageViewList.add(img2);
        imageViewList.add(img3);
        imageViewList.add(img4);


        //头布局是用ViewPager显示的轮播图 绑定ViewPager的适配器
        TabViewPagerAdapter tabViewPagerAdapter = new TabViewPagerAdapter((ArrayList<ImageView>) imageViewList);
        myViewPager.setAdapter(tabViewPagerAdapter);
        myViewPager.setCurrentItem(0);
        myViewPager.setOnPageChangeListener(new MyPageChangeListener());

        //listView的适配器
        TabAdapter tabAdapter = new TabAdapter(getContext());
        tabLv.setAdapter(tabAdapter);
        tabAdapter.setImgDatas(imgDatas);

    }


    /**
     * 开始轮播图切换
     */
    private void startPlay() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1, 4, TimeUnit.SECONDS);
    }

    /**
     * 执行轮播图切换任务
     */
    private class SlideShowTask implements Runnable {


        @Override
        public void run() {
            synchronized (myViewPager) {
                currentItem = (currentItem + 1) % imageViewList.size();
                handler.sendEmptyMessage(100);
            }
        }
    }

    /**
     * ViewPager的监听器
     * 当ViewPager中页面的状态发生改变时调用
     */

    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {
        boolean isAutoPlay = false;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


        }

        @Override
        public void onPageSelected(int position) {
            //从这里面动态改变小圆点的背景，来实现效果
            currentItem = position;
            for (int i = 0; i < dotViewList.size(); i++) {
                if (i == position) {
                    dotViewList.get(position).setBackgroundResource(R.mipmap.ic_launcher);
                } else {

                    dotViewList.get(position).setBackgroundResource(R.mipmap.ic_launcher);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            switch (state) {
                case 1:// 手势滑动，空闲中
                    isAutoPlay = false;
                    System.out.println(" 手势滑动，空闲中");
                    break;
                case 2:// 界面切换中
                    isAutoPlay = true;
                    System.out.println(" 界面切换中");
                    break;
                case 0:// 滑动结束，即切换完毕或者加载完毕
                    // 当前为最后一张，此时从右向左滑，则切换到第一张
                    if (myViewPager.getCurrentItem() == myViewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
                        myViewPager.setCurrentItem(0);
                        System.out.println(" 滑动到最后一张");
                    }
                    // 当前为第一张，此时从左向右滑，则切换到最后一张
                    else if (myViewPager.getCurrentItem() == 0 && !isAutoPlay) {
                        myViewPager.setCurrentItem(myViewPager.getAdapter().getCount() - 1);
                        System.out.println(" 滑动到第一张");
                    }
                    break;
            }
        }
    }
}
