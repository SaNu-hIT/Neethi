
package com.example.work.cartapp.Mod.SaveDataToServerBean;

import com.google.gson.annotations.SerializedName;

public class SalesOrderSummary {

    @SerializedName("Customer_Address")
    private String mCustomerAddress;
    @SerializedName("Customer_Name")
    private String mCustomerName;
    @SerializedName("Customer_Phno")
    private String mCustomerPhno;
    @SerializedName("Sales_Order_Id")
    private Long mSalesOrderId;
    @SerializedName("Shop_Id")
    private String mShopId;
    @SerializedName("SoldDate")
    private String mSoldDate;

    public String getCustomerAddress() {
        return mCustomerAddress;
    }

    public void setCustomerAddress(String CustomerAddress) {
        mCustomerAddress = CustomerAddress;
    }

    public String getCustomerName() {
        return mCustomerName;
    }

    public void setCustomerName(String CustomerName) {
        mCustomerName = CustomerName;
    }

    public String getCustomerPhno() {
        return mCustomerPhno;
    }

    public void setCustomerPhno(String CustomerPhno) {
        mCustomerPhno = CustomerPhno;
    }

    public Long getSalesOrderId() {
        return mSalesOrderId;
    }

    public void setSalesOrderId(Long SalesOrderId) {
        mSalesOrderId = SalesOrderId;
    }

    public String getShopId() {
        return mShopId;
    }

    public void setShopId(String ShopId) {
        mShopId = ShopId;
    }

    public String getSoldDate() {
        return mSoldDate;
    }

    public void setSoldDate(String SoldDate) {
        mSoldDate = SoldDate;
    }

}
