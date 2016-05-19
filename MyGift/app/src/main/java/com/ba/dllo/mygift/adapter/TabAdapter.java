package com.ba.dllo.mygift.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ba.dllo.mygift.R;
import com.ba.dllo.mygift.bean.TabLvBean;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by ${巴为焱} on 16/5/18.
 */
public class TabAdapter extends BaseAdapter {
    private Context context;
    //    private List<Integer> imgDatas;
    private List<TabLvBean.DataBean.ItemsBean> imgDatas;

    public TabAdapter(Context context) {
        this.context = context;
    }

//    public void setImgDatas(List<Integer> imgDatas) {
//        this.imgDatas = imgDatas;
//        notifyDataSetChanged();
//    }


    public void setImgDatas(List<TabLvBean.DataBean.ItemsBean> imgDatas) {
        this.imgDatas = imgDatas;
        notifyDataSetChanged();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_tabfragment, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(imgDatas.get(position).getCover_image_url()).into(holder.listImg);
        return convertView;
    }

    class ViewHolder {
        ImageView listImg;

        public ViewHolder(View convertView) {
            listImg = (ImageView) convertView.findViewById(R.id.item_tablist_img);
        }
    }
}
