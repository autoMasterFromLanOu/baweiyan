package com.ba.dllo.giftsay.homepage;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ba.dllo.giftsay.R;

import java.util.ArrayList;
import java.util.List;

import static com.ba.dllo.giftsay.R.mipmap.ic_launcher;

/**
 * Created by ${巴为焱} on 16/5/13.
 */
public class TabAdapter extends BaseAdapter {
    private Context context;
    private List<Integer> imgDatas;

    public TabAdapter() {
    }
//    private List<ImageView> imgDatas;
//
//    public void setImgDatas(List<ImageView> imgDatas) {
//        this.imgDatas = imgDatas;
//        notifyDataSetChanged();
//    }


    public TabAdapter(Context context) {
        this.context = context;
    }

    public void setImgDatas(List<Integer> imgDatas) {
        this.imgDatas = imgDatas;
    }

    @Override
    public int getCount() {
        return imgDatas == null ? 0 : imgDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Integer imageView = imgDatas.get(position);
        holder.imageView.setImageResource(imageView);
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;

        public ViewHolder(View convertView) {
            imageView = (ImageView) convertView.findViewById(R.id.tab_img);

        }
    }
}
