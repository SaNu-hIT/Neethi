
package com.example.work.cartapp.Mod.SingleItemModel;


import com.google.gson.annotations.SerializedName;


public class Data {

    @SerializedName("Item_DataId")
    private Long mItemDataId;
    @SerializedName("Item_Id")
    private Long mItemId;
    @SerializedName("Items_Details")
    private com.example.work.cartapp.Mod.SingleItemModel.ItemsDetails mItemsDetails;
    @SerializedName("LastModifiedBy")
    private Long mLastModifiedBy;
    @SerializedName("LastModified_IP")
    private Object mLastModifiedIP;
    @SerializedName("LastModifiedOn")
    private String mLastModifiedOn;
    @SerializedName("Quantity")
    private Long mQuantity;
    @SerializedName("Shop_Details")
    private com.example.work.cartapp.Mod.SingleItemModel.ShopDetails mShopDetails;
    @SerializedName("Shop_Id")
    private Long mShopId;
    @SerializedName("UploadedBy")
    private Long mUploadedBy;
    @SerializedName("Uploaded_IP")
    private Object mUploadedIP;
    @SerializedName("UploadedOn")
    private String mUploadedOn;

    public Long getItemDataId() {
        return mItemDataId;
    }

    public void setItemDataId(Long ItemDataId) {
        mItemDataId = ItemDataId;
    }

    public Long getItemId() {
        return mItemId;
    }

    public void setItemId(Long ItemId) {
        mItemId = ItemId;
    }

    public com.example.work.cartapp.Mod.SingleItemModel.ItemsDetails getItemsDetails() {
        return mItemsDetails;
    }

    public void setItemsDetails(com.example.work.cartapp.Mod.SingleItemModel.ItemsDetails ItemsDetails) {
        mItemsDetails = ItemsDetails;
    }

    public Long getLastModifiedBy() {
        return mLastModifiedBy;
    }

    public void setLastModifiedBy(Long LastModifiedBy) {
        mLastModifiedBy = LastModifiedBy;
    }

    public Object getLastModifiedIP() {
        return mLastModifiedIP;
    }

    public void setLastModifiedIP(Object LastModifiedIP) {
        mLastModifiedIP = LastModifiedIP;
    }

    public String getLastModifiedOn() {
        return mLastModifiedOn;
    }

    public void setLastModifiedOn(String LastModifiedOn) {
        mLastModifiedOn = LastModifiedOn;
    }

    public Long getQuantity() {
        return mQuantity;
    }

    public void setQuantity(Long Quantity) {
        mQuantity = Quantity;
    }

    public com.example.work.cartapp.Mod.SingleItemModel.ShopDetails getShopDetails() {
        return mShopDetails;
    }

    public void setShopDetails(com.example.work.cartapp.Mod.SingleItemModel.ShopDetails ShopDetails) {
        mShopDetails = ShopDetails;
    }

    public Long getShopId() {
        return mShopId;
    }

    public void setShopId(Long ShopId) {
        mShopId = ShopId;
    }

    public Long getUploadedBy() {
        return mUploadedBy;
    }

    public void setUploadedBy(Long UploadedBy) {
        mUploadedBy = UploadedBy;
    }

    public Object getUploadedIP() {
        return mUploadedIP;
    }

    public void setUploadedIP(Object UploadedIP) {
        mUploadedIP = UploadedIP;
    }

    public String getUploadedOn() {
        return mUploadedOn;
    }

    public void setUploadedOn(String UploadedOn) {
        mUploadedOn = UploadedOn;
    }

}
