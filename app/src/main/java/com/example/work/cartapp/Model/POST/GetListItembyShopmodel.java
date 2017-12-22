
package com.example.work.cartapp.Model.POST;


import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class GetListItembyShopmodel {



    @SerializedName("Item_DataId")
    private Long mItemDataId;
    @SerializedName("Shop_Id")
    private Long mShopId;

    @SerializedName("Qr_ITemId")
    private Long Qr_ITemId;

    public Long getQr_ITemId() {
        return Qr_ITemId;
    }

    public void setQr_ITemId(Long qr_ITemId) {
        Qr_ITemId = qr_ITemId;
    }

    public Long getItemDataId() {
        return mItemDataId;
    }

    public void setItemDataId(Long ItemDataId) {
        mItemDataId = ItemDataId;
    }

    public Long getShopId() {
        return mShopId;
    }

    public void setShopId(Long ShopId) {
        mShopId = ShopId;
    }

}
