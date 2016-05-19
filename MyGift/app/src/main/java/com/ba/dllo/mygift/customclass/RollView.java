//package com.ba.dllo.mygift.customclass;
//
//import android.content.Context;
//import android.os.AsyncTask;
//import android.os.Handler;
//import android.os.Message;
//import android.support.v4.view.ViewPager;
//import android.text.Html;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import com.ba.dllo.mygift.R;
//import com.ba.dllo.mygift.adapter.MyPagerAdapter;
//import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
//import com.nostra13.universalimageloader.core.ImageLoader;
//import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
//import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//
///**
// * ViewPager实现的轮播图广告自定义视图
// * 可以自动轮播也支持手动滑动切换页面
// * Created by ${巴为焱} on 16/5/18.
// */
//public class RollView extends FrameLayout {
//    //定义自动轮播启动的开关
//    private final static boolean isAutoPlay = true;
//
//    //自定义轮播图的资源
//    private String[] imgUrls;
//
//    //放轮播图片的ImageView的list
//    private List<ImageView> imageViewList;
//
//    //用来放远点的View的list
//    private List<View> dotViewsList;
//
//    private ViewPager viewPager;
//
//    //设置ViewPager的当前页数
//    private int currentItem = 0;
//
//    //定时任务
//    private ScheduledExecutorService scheduledExecutorService;
//
//    private Context context;
//
//    private MyPagerAdapter myPagerAdapter;
//
//    //这里定义一个Handler来处理ViewPager的轮播 最后看看接受message的handler：
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            viewPager.setCurrentItem(currentItem);
//        }
//    };
//
//
//    public RollView(Context context) {
//        this(context, null);
//    }
//
//    public RollView(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public RollView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        this.context = context;
//
//        initImageLoader(context);
//
//        inidData();
//        if (isAutoPlay) {
//            startPlay();
//        }
//    }
//
//    /**
//     * 开始轮播切换
//     */
//    //startPlay方法创建单线程化的线程池，并延时发送message来不断的切换ViewPager的item
//    private void startPlay() {
//        //  scheduleAtFixedRate(TimerTask task,long delay,long period)
//        //task--这是被调度的任务。
//        //delay--这是以毫秒为单位的延迟之前的任务执行。
//        //period--这是在连续执行任务之间的毫秒的时间。
//        //创建只有一条线程的线程池,他可以在指定延迟后执行线程任务
//        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//        scheduledExecutorService.scheduleAtFixedRate(new RollTask(), 1, 4, TimeUnit.SECONDS);
//
//    }
//
//    private void inidData() {
//        imageViewList = new ArrayList<>();
//        dotViewsList = new ArrayList<>();
//        myPagerAdapter = new MyPagerAdapter();
//        //异步任务获取图片
//        new GetListTast().execute("");
//
//    }
//
//    //初始化Views等UI
//    private void initUI(final Context context) {
//        if (imgUrls == null || imgUrls.length == 0)
//            return;
//
//        LayoutInflater.from(context).inflate(R.layout.layout_rollview, this, true);
//        LinearLayout dotLayout = (LinearLayout) findViewById(R.id.dotLayout);
//        dotLayout.removeAllViews();
//
//        for (int i = 0; i < imgUrls.length; i++) {
//            ImageView view = new ImageView(context);
//            //加Tag区分
//            view.setTag(imgUrls[i]);
//            view.setScaleType(ImageView.ScaleType.FIT_XY);
//            imageViewList.add(view);
//
//            ImageView dotView = new ImageView(context);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//            params.leftMargin = 4;
//            params.rightMargin = 4;
//            dotLayout.addView(dotView, params);
//            dotViewsList.add(dotView);
//        }
//
//        viewPager = (ViewPager) findViewById(R.id.vp);
//        viewPager.setFocusable(true);
//        myPagerAdapter.setImageViewList(imageViewList);
//        viewPager.setAdapter(myPagerAdapter);
//        viewPager.setOnPageChangeListener(new MyPageChangeListener());
//    }
//
//
//    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {
//        boolean isAutoPlay = false;
//
//
//        @Override
//        public void onPageSelected(int position) {
//            switch (position) {
//                case 1:
//                    //空闲中
//                    isAutoPlay = false;
//                    break;
//                case 2:
//                    //界面切换中
//                    isAutoPlay = true;
//                    break;
//                case 3:
//                    // 滑动结束，即切换完毕或者加载完毕
//                    // 当前为最后一张，此时从右向左滑，则切换到第一张
//                    if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
//                        viewPager.setCurrentItem(3);
//                    }
//                    //当前为第一张，此时从左向右滑动,则切换到最后一张
//                    else if (viewPager.getCurrentItem() == 0 && !isAutoPlay) {
//                        viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 1);
//                    }
//                    break;
//            }
//        }
//
//        @Override
//        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        }
//
//        @Override
//        public void onPageScrollStateChanged(int state) {
//            currentItem = state;
//            for (int i = 0; i < dotViewsList.size(); i++) {
//                if (i == state) {
//                    ((View) dotViewsList.get(state)).setBackgroundResource(R.mipmap.dot_focus);
//                } else {
//                    ((View) dotViewsList.get(i)).setBackgroundResource(R.mipmap.dot_blur);
//                }
//            }
//        }
//    }
//
//    /**
//     * 执行轮播图切换任务
//     */
//    //SlideShowTask中不断的发送message：
//    private class RollTask implements Runnable {
//
//        @Override
//        public void run() {
//            synchronized (viewPager) {
//                currentItem = (currentItem + 1) % imageViewList.size();
//                handler.obtainMessage().sendToTarget();
//            }
//        }
//    }
//
//    /**
//     * 异步任务 ，获取数据
//     */
//    class GetListTast extends AsyncTask<String, Integer, Boolean> {
//
//
//        @Override
//        protected Boolean doInBackground(String... params) {
//
//            imgUrls = new String[]{
//                    "http://photos.breadtrip.com/covers_2016_05_05_3c2aa2725763a19c2f83e73b68b63fea.png?imageView/2/w/960/",
//                    "http://photos.breadtrip.com/covers_2016_05_05_7cf8bd1819a74beed9d4aa8256aab0c1.png?imageView/2/w/960/",
//                    "http://photos.breadtrip.com/covers_2016_04_17_13b4e370794446258aff31bf8747b65b.png",
//                    "http://photos.breadtrip.com/covers_2016_04_16_6c5f70ca4f16e8e1b37a5c84c41c30bb.png?imageView/2/w/960"
//            };
//            return true;
//        }
//
//        @Override
//        protected void onPostExecute(Boolean aBoolean) {
//            super.onPostExecute(aBoolean);
//            if (aBoolean) {
//
//                initUI(context);
//
//            }
//        }
//    }
//
//    /**
//     * 图片组件初始化
//     */
//    private static void initImageLoader(Context context) {
//
//        //  ImageLoader 具体下载图片，缓存图片，显示图片的具体执行类，
//        //  ImageLoaderConfiguration：图片缓存的全局配置，主要有线程类、缓存大小、磁盘大小、图片下载与解析、日志方面的配置
////线程优先级threadPriority
////下载和显示的工作队列排序  QueueProcessingType.LIFO
//
//        //
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).
//                threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory().
//                discCacheFileNameGenerator(new Md5FileNameGenerator()).
//                tasksProcessingOrder(QueueProcessingType.FIFO).writeDebugLogs().build();
//
//        ImageLoader.getInstance().init(config);
//    }
//}
