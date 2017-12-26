
package com.example.work.cartapp.Model.ListModel;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ParameterListOrder {

        @SerializedName("SalesOrderId")
    private Long mSalesOrderId;

        @SerializedName("shopId")
    private Long shopId;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getSalesOrderId() {
        return mSalesOrderId;
    }

    public void setSalesOrderId(Long SalesOrderId) {
        mSalesOrderId = SalesOrderId;
    }

}
