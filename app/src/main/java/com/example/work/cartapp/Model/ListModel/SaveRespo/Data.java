
package com.example.work.cartapp.Model.ListModel.SaveRespo;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Data {

    @SerializedName("CESS")
    private Long mCESS;
    @SerializedName("CGST")
    private Long mCGST;
    @SerializedName("Discount")
    private Long mDiscount;
    @SerializedName("IGST")
    private Long mIGST;
    @SerializedName("Item_Id")
    private Long mItemId;
    @SerializedName("Items_Details")
    private Object mItemsDetails;
    @SerializedName("Qty")
    private Long mQty;
    @SerializedName("SGST")
    private Long mSGST;
    @SerializedName("Sales_Id")
    private Long mSalesId;
    @SerializedName("Sales_Order_Id")
    private Long mSalesOrderId;

    public Long getCESS() {
        return mCESS;
    }

    public void setCESS(Long CESS) {
        mCESS = CESS;
    }

    public Long getCGST() {
        return mCGST;
    }

    public void setCGST(Long CGST) {
        mCGST = CGST;
    }

    public Long getDiscount() {
        return mDiscount;
    }

    public void setDiscount(Long Discount) {
        mDiscount = Discount;
    }

    public Long getIGST() {
        return mIGST;
    }

    public void setIGST(Long IGST) {
        mIGST = IGST;
    }

    public Long getItemId() {
        return mItemId;
    }

    public void setItemId(Long ItemId) {
        mItemId = ItemId;
    }

    public Object getItemsDetails() {
        return mItemsDetails;
    }

    public void setItemsDetails(Object ItemsDetails) {
        mItemsDetails = ItemsDetails;
    }

    public Long getQty() {
        return mQty;
    }

    public void setQty(Long Qty) {
        mQty = Qty;
    }

    public Long getSGST() {
        return mSGST;
    }

    public void setSGST(Long SGST) {
        mSGST = SGST;
    }

    public Long getSalesId() {
        return mSalesId;
    }

    public void setSalesId(Long SalesId) {
        mSalesId = SalesId;
    }

    public Long getSalesOrderId() {
        return mSalesOrderId;
    }

    public void setSalesOrderId(Long SalesOrderId) {
        mSalesOrderId = SalesOrderId;
    }

}
