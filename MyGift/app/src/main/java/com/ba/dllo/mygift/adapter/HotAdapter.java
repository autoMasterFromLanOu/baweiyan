package com.ba.dllo.mygift.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.android.volley.Response;
import com.ba.dllo.mygift.R;
import java.util.List;

/**
 * Created by ${巴为焱} on 16/5/19.
 */
public class HotAdapter extends RecyclerView.Adapter<HotAdapter.HotViewHolder> {
    private Context context;
    private List<Integer> imgDatas;

    public HotAdapter(Context context) {
        this.context = context;
    }

    public void setImgDatas(List<Integer> imgDatas) {
        this.imgDatas = imgDatas;
        notifyDataSetChanged();
    }

    @Override
    public HotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hotrvimg, parent, false);
        HotViewHolder hotViewHolder = new HotViewHolder(view);
        return hotViewHolder;
    }

    @Override
    public void onBindViewHolder(HotViewHolder holder, int position) {
        holder.hotRvImg.setImageResource(position);
    }

    @Override
    public int getItemCount() {
        return imgDatas == null ? 0 : imgDatas.size();
    }

    class HotViewHolder extends RecyclerView.ViewHolder {
        ImageView hotRvImg;

        public HotViewHolder(View itemView) {
            super(itemView);
            hotRvImg = (ImageView) itemView.findViewById(R.id.item_hotrv_img);
        }
    }

}
