package com.example.work.cartapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.work.cartapp.Model.ListModel.DetailsList.ItemsDetails;
import com.example.work.cartapp.Model.ListModel.DetailsList.SalesOrderDetailsList;
import com.example.work.cartapp.Interface.OnClickClose;
import com.example.work.cartapp.R;
import com.example.work.cartapp.Extras.TextAquino;

import java.util.List;


/**
 * Created by user on 8/2/2017.
 */

public class BillListAdapetr extends RecyclerView.Adapter<BillListAdapetr.ViewHolder> {
    private List<SalesOrderDetailsList>catrecycles;
    private Context context;
    OnClickClose onClickClose;
    public BillListAdapetr(List<SalesOrderDetailsList> categl, Context context){
        catrecycles=categl;
        context=context;

    }
    public void SetListnerMon(OnClickClose onClickClose)
    {
       this.onClickClose=onClickClose;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_row,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        SalesOrderDetailsList cycle=catrecycles.get(position);
        ItemsDetails itemdetils=cycle.getShopItemData().getItemsDetails();
        holder.ItemName.setText(""+cycle.getShopItemData().getItemsDetails().getItemName());
        holder.edtQuantity.setText(""+cycle.getQty());
        holder.edtPrice.setText(""+cycle.getShopItemData().getItemsDetails().getSellingPrice());
        holder.code.setText(""+itemdetils.getItemCode());
        int ss=position+1;
        holder.Itemone.setText("ITEM-"+ss);

        holder.Titalamount.setText(""+cycle.getTotalValue());
        holder.discount.setText(""+cycle.getDiscountAmnt());


    }

    @Override
    public int getItemCount() {
        return catrecycles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView code;
        public TextView ItemName;
        public TextView edtPrice;
        TextAquino Itemone;
        public TextView edtQuantity,textDiscount,Titalamount,discuntamount,discount,lensstyle,lenstype;
        ImageView close_icons;
        public ViewHolder(View itemView)
        {

            super(itemView);
            code=itemView.findViewById(R.id.code);
            ItemName=itemView.findViewById(R.id.itemname);
            edtQuantity=itemView.findViewById(R.id.quantity);
            textDiscount=itemView.findViewById(R.id.textDiscount);
            discuntamount=itemView.findViewById(R.id.discuntamount);
            Titalamount=itemView.findViewById(R.id.total_value);
            discount=itemView.findViewById(R.id.discount);
            lenstype=itemView.findViewById(R.id.lenstype);
            Itemone=itemView.findViewById(R.id.Itemone);
            lensstyle=itemView.findViewById(R.id.lensstyle);
            edtPrice=itemView.findViewById(R.id.price);
            close_icons=itemView.findViewById(R.id.close_icons);

        }
    }
}
