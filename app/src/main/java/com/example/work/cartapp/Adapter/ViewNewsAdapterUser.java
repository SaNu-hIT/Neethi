package com.example.work.cartapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.example.work.cartapp.Extras.ExternLib.ClickableViewPager;

import com.example.work.cartapp.Model.ListItemsShop.Datum;
import com.example.work.cartapp.R;

import java.util.List;

/**
 * Created by work on 10/4/2017.
 */

public class ViewNewsAdapterUser extends RecyclerView.Adapter<ViewNewsAdapterUser.NavigationViewHolder> {

    private Context context;
    List<Datum> listNewsItem;
//    List<NewsImage> newsImg;
    public ViewNewsAdapterUser(Context context, List<Datum> listNewsItem) {
        this.context = context;
        this.listNewsItem = listNewsItem;


    }

    @Override
    public NavigationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_view_news_user, parent, false);
        return new NavigationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NavigationViewHolder holder, final int positionParent) {
        holder.setIsRecyclable(false);


//        CustomPagerAdapter customPagerAdapter = new CustomPagerAdapter(context, newsImg);
//        holder.mViewPager.setAdapter(customPagerAdapter);



    }

    @Override
    public int getItemCount() {
        return listNewsItem.size();
    }


    class NavigationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_Titile, tv_description,tv_Date;
        FrameLayout frm_Image;

        ClickableViewPager mViewPager;

        public NavigationViewHolder(View itemView) {
            super(itemView);

        }

        @Override
        public void onClick(View v) {

        }
    }

}