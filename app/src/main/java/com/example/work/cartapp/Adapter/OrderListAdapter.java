package com.example.work.cartapp.Adapter;

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
import com.example.work.cartapp.Fragments.OrderListFragemnt.OnListFragmentInteractionListener;
import com.example.work.cartapp.Model.ListModel.ListOrderNewModel.Datum;
import com.example.work.cartapp.R;
import com.example.work.cartapp.Extras.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder>implements Filterable {

    private  List<Datum> mValues;
    private  List<Datum> valueFilterd;
    private final OnListFragmentInteractionListener mListener;

    public OrderListAdapter(List<Datum> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        valueFilterd = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.orderlist_new_design, parent, false);
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_orderlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

//        holder.mIdView.setText(mValues.get(position).getCustomerName());
//        holder.mContentView.setText(mValues.get(position).getCustomerPhno());
//        holder.Customer_Address.setText(mValues.get(position).getCustomerAddress());
//        holder.totals.setText("₹ "+mValues.get(position).getTotalOrder());
//        Log.e( "getTotalOrder: ",""+mValues.get(position).getSalesOrderId());
//
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(valueFilterd.get(position).getSalesOrderId().toString());

                    Log.e( "Item id: ",""+valueFilterd.get(position).getSalesOrderId());

                }
            }
        });
        holder.tvTitle.setText(valueFilterd.get(position).getCustomerName());
        holder.tvPrice.setText(valueFilterd.get(position).getCustomerPhno());
//        holder.Customer_Address.setText(mValues.get(position).getCustomerAddress());
        holder.tv_Rate.setText("₹ "+valueFilterd.get(position).getTotalOrder());



    }

    @Override
    public int getItemCount() {
        return valueFilterd.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
//        public final TextView mIdView;
//        public final TextView mContentView,Customer_Address,totals;
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
            mView = view;
//            mIdView = (TextView) view.findViewById(R.id.id);
//            mContentView = (TextView) view.findViewById(R.id.content);
//            Customer_Address = (TextView) view.findViewById(R.id.Customer_Address);
//            totals = (TextView) view.findViewById(R.id.totals);
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
                        if (row.getCustomerName().toLowerCase().contains(charString.toLowerCase()) || row.getCustomerPhno().contains(charSequence)) {
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
}
