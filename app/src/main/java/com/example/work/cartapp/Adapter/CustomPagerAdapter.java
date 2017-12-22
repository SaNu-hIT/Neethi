package com.example.work.cartapp.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.work.cartapp.Model.ListModel.NewsImage;
import com.example.work.cartapp.R;

import java.util.List;

/**
 * Created by work on 10/4/2017.
 */

public class CustomPagerAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    List<NewsImage> newsImage;

    public CustomPagerAdapter(Context context, List<NewsImage> newsImage) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.newsImage = newsImage;
    }

    @Override
    public int getCount() {
        return newsImage.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);

        imageView.setImageResource(newsImage.get(position).getImagess());

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
