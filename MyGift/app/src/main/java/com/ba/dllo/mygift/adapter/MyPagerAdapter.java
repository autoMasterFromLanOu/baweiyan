//package com.ba.dllo.mygift.adapter;
//
//import android.support.v4.view.PagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import com.nostra13.universalimageloader.core.ImageLoader;
//import java.util.List;
//
///**
// * Created by ${巴为焱} on 16/5/18.
// */
//public class MyPagerAdapter extends PagerAdapter {
//    private List<ImageView> imageViewList;
//    private ImageLoader imageLoader = ImageLoader.getInstance();
//
//    public MyPagerAdapter() {
//    }
//
//
//    public void setImageViewList(List<ImageView> imageViewList) {
//        this.imageViewList = imageViewList;
//        notifyDataSetChanged();
//    }
//
//    //删除页卡方法
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        View view = imageViewList.get(position);
//        container.removeView(view);
//    }
//
//    //增加页卡方法
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        ImageView imageView = imageViewList.get(position);
//
//        //加载图片
//        imageLoader.displayImage(imageView.getTag() + "", imageView);
//        ((ViewPager) container).addView(imageViewList.get(position));
//        return imageViewList.get(position);
//    }
//
//    @Override
//    public int getCount() {
//        return imageViewList == null ? 0 : imageViewList.size();
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view == object;
//    }
//
//
//}
