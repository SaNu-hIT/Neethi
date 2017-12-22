package com.example.work.cartapp.Adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.work.cartapp.ExternalLib.AutofitTextViewCustomFont;
import com.example.work.cartapp.ExternalLib.TextViewRobotoRegular;
import com.example.work.cartapp.Fragments.ItemListFragment.OnListFragmentInteractionListener;


import com.example.work.cartapp.Model.ListItemsShop.Datum;
import com.example.work.cartapp.R;
import com.example.work.cartapp.Extras.TextAquino;
import com.example.work.cartapp.Extras.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> implements Filterable {

    private List<Datum> mValues;
    private List<Datum> valueFilterd;
    private final OnListFragmentInteractionListener mListener;

    public MyItemRecyclerViewAdapter(List<Datum> mValues, OnListFragmentInteractionListener listener) {
        this.mValues = mValues;
        valueFilterd = mValues;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.fragment_item, parent, false);
   View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row_grid, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

//        holder.ItemName.setText("" + mValues.get(position).getItemsDetails().getItemName());
//        holder.Model.setText(mValues.get(position).getItemsDetails().getItemCode());
//        holder.edtPrice.setText("$ " + mValues.get(position).getItemsDetails().getSellingPrice());
//        holder.manufacture.setText("" + mValues.get(position).getItemsDetails().getManufaturer());
//        holder.unit.setText("" + mValues.get(position).getItemsDetails().getUnit());
//        holder.stock.setText("in stock " + mValues.get(position).getItemsDetails().getQuantity());
//        holder.id.setText(mValues.get(position).getItemId().toString());
//        long stock = mValues.get(position).getItemsDetails().getQuantity();
//        long cutoff = mValues.get(position).getItemsDetails().getLowStockAlert();
//
//
//        if (stock <cutoff) {
//            holder.indicatiorstock.setVisibility(View.VISIBLE);
//            holder.indicatiorstock.setText("LOW STOCK");
//
//
//        }
//        else if (stock == cutoff) {
//            holder.indicatiorstock.setVisibility(View.VISIBLE);
//            holder.indicatiorstock.setText("NO STOCK");
//
//
//        }
//        else {
//            holder.indicatiorstock.setVisibility(View.INVISIBLE);
//
//        }



         holder.tvTitle.setText("" + valueFilterd.get(position).getItemsDetails().getItemName());

         holder.tvPrice.setText(" " + valueFilterd.get(position).getItemsDetails().getItemCode());
        holder.tv_Rate.setText("₹ " + valueFilterd.get(position).getItemsDetails().getSellingPrice());

        long stock = valueFilterd.get(position).getItemsDetails().getQuantity();
        long cutoff = valueFilterd.get(position).getItemsDetails().getLowStockAlert();


        if (stock <cutoff) {
//            holder.indicatiorstock.setVisibility(View.VISIBLE);\
//            holder.stock_id.setBackgroundColor(R.color.red_btn_bg_color);
//            holder.tv_Site.setTextColor(R.color.white);
            holder.tv_Site.setText("LOW STOCK");


        }
//        else if (stock == cutoff) {
//            holder.tv_Site.setTextColor(R.color.red_btn_bg_color);
////            holder.indicatiorstock.setVisibility(View.VISIBLE);
//            holder.tv_Site.setText("NO STOCK");
//
//
//        }
        else {
//            holder.indicatiorstock.setVisibility(View.INVISIBLE);
            holder.stock_id.setBackgroundColor(R.color.gray);
            holder.tv_Site.setText(""+stock);

        }
//        holder.tvCutOff.setText(" " + mValues.get(position).getItemsDetails().());
//        holder.tv_pOff.setText("" + mValues.get(position).getItemsDetails().getSellingPrice());
//
//        holder.tv_Site.setText("" + mValues.get(position).getItemsDetails().getSellingPrice());



    }

    @Override
    public int getItemCount() {
        return valueFilterd.size();
    }

    @Override
    public Filter getFilter() {
        Log.e( "Size inside filter: ", ""+ mValues.size());

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                Log.e( "performFiltering: ",""+charString );
                if (charString.isEmpty()) {
                    Log.e( "hey lo: ","inside data" );
                    valueFilterd = mValues;
                } else {
                    Log.e( "row: ","" );
                    List<Datum> filteredList = new ArrayList<>();
                    for (Datum row : valueFilterd) {
                        Log.e( "row: ",""+row );
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getItemsDetails().getItemName().toLowerCase().contains(charString.toLowerCase()) || row.getItemsDetails().getItemCode().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    valueFilterd = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = valueFilterd;
                Log.e( "performFiltering: ",""+filterResults.values);
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                valueFilterd = (ArrayList<Datum>) filterResults.values;
                Log.e( "performFiltering: ",""+valueFilterd);

                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        public final View mView;
//        public final TextView ItemName;
//        public final TextView Model;
//        public final TextView edtPrice;
//        public final TextView stock;
//        public final TextView id;
//        public final TextView manufacture;
//        public final TextAquino indicatiorstock;
//        public final TextView unit;
//        public DummyItem mItem;


        TextViewRobotoRegular tvTitle;

        AutofitTextViewCustomFont tvPrice;
        AutofitTextViewCustomFont tvCutOff;
        AutofitTextViewCustomFont tv_pOff;
        TextViewRobotoRegular tv_Rate;
        TextViewRobotoRegular tv_Site;
        LinearLayout stock_id;
        public ViewHolder(View view) {
            super(view);
//            mView = view;
//            ItemName = (TextView) view.findViewById(R.id.ItemName);
//            edtPrice = (TextView) view.findViewById(R.id.edtPrice);
//            Model = (TextView) view.findViewById(R.id.Model);
//            stock = (TextView) view.findViewById(R.id.stock);
//            id = (TextView) view.findViewById(R.id.id);
//            manufacture = (TextView) view.findViewById(R.id.manufacture);
//            indicatiorstock = (TextAquino) view.findViewById(R.id.indicatiorstock);
//            unit = (TextView) view.findViewById(R.id.unit);
            tvTitle = (TextViewRobotoRegular) view.findViewById(R.id.tvTitle);
            tvCutOff = (AutofitTextViewCustomFont) view.findViewById(R.id.tvCutOff);
            tvPrice = (AutofitTextViewCustomFont) view.findViewById(R.id.tvPrice);
            tv_pOff = (AutofitTextViewCustomFont) view.findViewById(R.id.tv_pOff);
            tv_Rate = (TextViewRobotoRegular) view.findViewById(R.id.tv_Rate);
            tv_Site = (TextViewRobotoRegular) view.findViewById(R.id.tv_Site);
            stock_id = (LinearLayout) view.findViewById(R.id.stock_id);




        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvPrice.getText() + "'";
        }
    }
}
