package com.example.work.cartapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.work.cartapp.ExternalLib.AutofitTextViewCustomFont;
import com.example.work.cartapp.ExternalLib.TextViewRobotoRegular;
import com.example.work.cartapp.Extras.TextAquino;
import com.example.work.cartapp.Model.ListModel.Cat_recycler;
import com.example.work.cartapp.Interface.OnClickClose;
import com.example.work.cartapp.R;

import java.util.ArrayList;


/**
 * Created by user on 8/2/2017.
 */

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {
    private ArrayList<Cat_recycler> catrecycles;
    private Context context;
    OnClickClose onClickClose;

    public ItemListAdapter(ArrayList<Cat_recycler> categl, Context context) {
        catrecycles = categl;
        context = context;

    }

    public void SetListnerMon(OnClickClose onClickClose) {
        this.onClickClose = onClickClose;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow_forallitem, parent, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Cat_recycler cycle = catrecycles.get(position);

        holder.ItemName.setText("Item Name : "+ cycle.getProduct_name());
        holder.edtQuantity.setText("No :"+ cycle.getQuantity());
        holder.edtPrice.setText("Price : ₹ " + cycle.getPrice());
        holder.code.setText("Code :"+ cycle.getCode());
        int itm=position+1;
        holder.itemname_heading.setText("ITEM :"+itm);
        holder.textDiscount.setText("Discount Per (" + cycle.getModel() + "%)");
        holder.discuntamount.setText("Discount amount single : ₹ " + cycle.getDiscountamount());
        holder.total_discount.setText("Total Discount : ₹ " + cycle.getTotaldiscount());
        holder.discuntamount.setVisibility(View.INVISIBLE);
        holder.Titalamount.setText("Grand Total : ₹ " + cycle.getTotal());
        long price= Long.parseLong(cycle.getPrice());
        long qty= Long.parseLong(cycle.getQuantity());
        long total=price*qty;
        holder.totalamt.setText("Sub Total : ₹ " + total);

//        holder.lenstype.setText(cycle.getLenstype());
//        holder.lensstyle.setText(cycle.getLenstype());


        holder.left_n_va.setText(cycle.getLeft_n_va());
        holder.left_n_axis.setText(cycle.getLeft_n_axis());
        holder.left_n_sph.setText(cycle.getLeft_n_sph());
        holder.left_n_cyl.setText(cycle.getLeft_n_cyl());


        holder.right_d_sph.setText(cycle.getRight_d_sph());
        holder.right_d_cyl.setText(cycle.getRight_d_cyl());
        holder.right_d_axis.setText(cycle.getRight_d_axis());
        holder.right_d_va.setText(cycle.getRight_d_va());

        holder.close_icons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                catrecycles.remove(position);
                onClickClose.CloseItems(position);
            }
        });


        if(cycle.getCode().equals("0")&&cycle.getQuantity().equals("0")&&cycle.getPrice().equals("0")&&cycle.getCode().equals("0"))
        {
            holder.productdetails.setVisibility(View.GONE);
        }

        if (cycle.getRight_cl_va().equals("")&&cycle.getLeft_d_sph().equals("")
                &&cycle.getLeft_d_cyl().equals("")
                &&cycle.getLeft_d_axis().equals("")&&
                cycle.getLeft_d_va().equals("")&&cycle.getLeft_cl_va().equals("")&&cycle.getLeft_cl_axis().equals("")
                &&cycle.getLeft_cl_cl().equals("")&&

                cycle.getLeft_cl_sph().equals("")&&
                cycle.getLeft_add_sph().equals("")&&
                cycle.getLeft_n_va().equals("")&&
                cycle.getLeft_n_axis().equals("")&&
                cycle.getLeft_n_sph().equals("")&&
                cycle.getLeft_n_cyl().equals("")&&
                cycle.getRight_d_sph().equals("")&&
                cycle.getRight_d_cyl().equals("")&&
                cycle.getRight_d_axis().equals("")&&
                cycle.getRight_d_va().equals("")&&
                cycle.getRight_n_sph().equals("")&&
                cycle.getRight_n_cyl().equals("")&&
                cycle.getRight_n_axis().equals("")&&
                cycle.getRight_n_va().equals("")&&
                cycle.getRight_add_sph().equals("")&&
                cycle.getRight_cl_sph().equals("")&&
                cycle.getRight_cl_cl().equals("")&&
                cycle.getRight_cl_axis().equals("")
                )
        {

//            holder.left_side.setVisibility(View.GONE);
//            holder.right_side.setVisibility(View.GONE);
            holder.lenslayout.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return catrecycles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextViewRobotoRegular code;
        public TextViewRobotoRegular ItemName;
        TextViewRobotoRegular itemname_heading;
        public AutofitTextViewCustomFont edtPrice;
        AutofitTextViewCustomFont edtQuantity;
        LinearLayout lenslayout;
        public AutofitTextViewCustomFont  textDiscount,totalamt;
        TextView Titalamount, discuntamount, total_discount, lensstyle, lenstype;
        ImageView close_icons;
        TextView left_d_sph, left_d_cyl, left_d_axis, left_d_va, left_cl_va, left_cl_axis, left_cl_cl, left_cl_sph, left_add_sph, left_n_va, left_n_axis, left_n_cyl, left_n_sph;
        TextView right_d_sph, right_d_cyl, right_d_axis, right_d_va, right_n_sph, right_n_cyl, right_n_axis, right_n_va, right_add_sph, right_cl_sph, right_cl_cl, right_cl_axis, right_cl_va;
LinearLayout productdetails;
        public ViewHolder(View itemView) {

            super(itemView);

//            left_side=itemView.findViewById(R.id.left_side);
//            right_side=itemView.findViewById(R.id.right_side);
            productdetails=itemView.findViewById(R.id.frame_heading);
            itemname_heading=itemView.findViewById(R.id.itemname_heading);

            code = itemView.findViewById(R.id.code);
            ItemName = itemView.findViewById(R.id.ItemName);
            left_n_sph = itemView.findViewById(R.id.left_n_sph);
            left_n_cyl = itemView.findViewById(R.id.left_n_cyl);
            left_n_axis = itemView.findViewById(R.id.left_n_axis);
            left_n_va = itemView.findViewById(R.id.left_n_va);

            right_d_axis = itemView.findViewById(R.id.right_d_axis);
            right_d_cyl = itemView.findViewById(R.id.right_d_cyl);
            right_d_va = itemView.findViewById(R.id.right_d_va);
            right_d_sph = itemView.findViewById(R.id.right_d_sph);

            edtQuantity = itemView.findViewById(R.id.edtQuantity);
            textDiscount = itemView.findViewById(R.id.textDiscount);
            discuntamount = itemView.findViewById(R.id.discuntamount);
            Titalamount = itemView.findViewById(R.id.Titalamount);
            totalamt = itemView.findViewById(R.id.totalamt);
            total_discount = itemView.findViewById(R.id.total_discount);
//            lenstype = itemView.findViewById(R.id.lenstype);
//            lensstyle = itemView.findViewById(R.id.lensstyle);
            edtPrice = itemView.findViewById(R.id.edtPrice);
            close_icons = itemView.findViewById(R.id.close_icons);
            lenslayout = itemView.findViewById(R.id.lenslayout);

        }
    }
}
