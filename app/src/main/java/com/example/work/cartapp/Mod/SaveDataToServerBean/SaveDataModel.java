
package com.example.work.cartapp.Mod.SaveDataToServerBean;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class SaveDataModel {

    @SerializedName("SalesOrder_DetailsList")
    private List<com.example.work.cartapp.Mod.SaveDataToServerBean.SalesOrderDetailsList> mSalesOrderDetailsList;
    @SerializedName("SalesOrderSummary")
    private com.example.work.cartapp.Mod.SaveDataToServerBean.SalesOrderSummary mSalesOrderSummary;

    public List<com.example.work.cartapp.Mod.SaveDataToServerBean.SalesOrderDetailsList> getSalesOrderDetailsList() {
        return mSalesOrderDetailsList;
    }

    public void setSalesOrderDetailsList(List<com.example.work.cartapp.Mod.SaveDataToServerBean.SalesOrderDetailsList> SalesOrderDetailsList) {
        mSalesOrderDetailsList = SalesOrderDetailsList;
    }

    public com.example.work.cartapp.Mod.SaveDataToServerBean.SalesOrderSummary getSalesOrderSummary() {
        return mSalesOrderSummary;
    }

    public void setSalesOrderSummary(com.example.work.cartapp.Mod.SaveDataToServerBean.SalesOrderSummary SalesOrderSummary) {
        mSalesOrderSummary = SalesOrderSummary;
    }

}
