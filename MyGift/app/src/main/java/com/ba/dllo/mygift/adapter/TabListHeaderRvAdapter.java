package com.ba.dllo.mygift.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ba.dllo.mygift.R;
import com.ba.dllo.mygift.bean.TabRcBean;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by ${巴为焱} on 16/5/18.
 */
public class TabListHeaderRvAdapter extends RecyclerView.Adapter<TabListHeaderRvAdapter.TabHeaderRvHodler> {
    private Context context;
    private ImageView rvImg;
    private List<TabRcBean.DataBean.SecondaryBannersBean> tabReBeans;
//    private TabRcBean tabReBeans;


    public TabListHeaderRvAdapter(Context context) {
        this.context = context;
    }


    public void setTabReBeans(List<TabRcBean.DataBean.SecondaryBannersBean> tabReBeans) {
        this.tabReBeans = tabReBeans;
        notifyDataSetChanged();
    }

//
//    public void setTabReBeans(TabRcBean tabReBeans) {
//        this.tabReBeans = tabReBeans;
//        notifyDataSetChanged();
//    }

    @Override
    public TabHeaderRvHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tabrvimg, parent, false);
        TabHeaderRvHodler tabHeaderRvHodler = new TabHeaderRvHodler(view);
        return tabHeaderRvHodler;
    }

    @Override
    public void onBindViewHolder(TabHeaderRvHodler holder, int position) {
        String url = tabReBeans.get(position).getWebp_url();
        Picasso.with(context).load(url).into(holder.rvImg);
//        Picasso.with(context).load(tabReBeans.getData().getSecondary_banners().get(position).getWebp_url()).into(holder.rvImg);
    }

    @Override
    public int getItemCount() {
        return tabReBeans == null ? 0 : tabReBeans.size();
    }

    class TabHeaderRvHodler extends RecyclerView.ViewHolder {
        ImageView rvImg;

        public TabHeaderRvHodler(View itemView) {
            super(itemView);
            rvImg = (ImageView) itemView.findViewById(R.id.item_tablistrv_img);
        }
    }
}
