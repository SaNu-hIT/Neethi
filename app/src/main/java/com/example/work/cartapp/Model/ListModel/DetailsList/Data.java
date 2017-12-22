
package com.example.work.cartapp.Model.ListModel.DetailsList;

import java.util.List;

import com.google.gson.annotations.SerializedName;



public class Data {

    @SerializedName("CESS_Total_Amnt")
    private String mCESSTotalAmnt;
    @SerializedName("CGST_Total_Amnt")
    private Double mCGSTTotalAmnt;
    @SerializedName("Customer_Address")
    private String mCustomerAddress;
    @SerializedName("Customer_Name")
    private String mCustomerName;
    @SerializedName("Customer_Phno")
    private String mCustomerPhno;
    @SerializedName("Discount_Amnt")
    private String mDiscountAmnt;
    @SerializedName("GST_Total_Amnt")
    private String mGSTTotalAmnt;
    @SerializedName("Grand_Total_order")
    private String mGrandTotalOrder;
    @SerializedName("Grand_Total_WithoutTax_Amnt")
    private String mGrandTotalWithoutTaxAmnt;
    @SerializedName("IGST_Total_Amnt")
    private String mIGSTTotalAmnt;
    @SerializedName("SGST_Total_Amnt")
    private Double mSGSTTotalAmnt;
    @SerializedName("SalesOrder_Details")
    private List<Object> mSalesOrderDetails;
    @SerializedName("SalesOrder_DetailsList")
    private List<SalesOrderDetailsList> mSalesOrderDetailsList;
    @SerializedName("Sales_Order_Id")
    private String mSalesOrderId;
    @SerializedName("Shop_Details")
    private ShopDetails mShopDetails;
    @SerializedName("Shop_Id")
    private String mShopId;
    @SerializedName("SoldDate")
    private Object mSoldDate;
    @SerializedName("SoldDate_String")
    private String mSoldDateString;
    @SerializedName("Total_Order")
    private String mTotalOrder;

    public String getCESSTotalAmnt() {
        return mCESSTotalAmnt;
    }

    public void setCESSTotalAmnt(String CESSTotalAmnt) {
        mCESSTotalAmnt = CESSTotalAmnt;
    }

    public Double getCGSTTotalAmnt() {
        return mCGSTTotalAmnt;
    }

    public void setCGSTTotalAmnt(Double CGSTTotalAmnt) {
        mCGSTTotalAmnt = CGSTTotalAmnt;
    }

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

    public String getDiscountAmnt() {
        return mDiscountAmnt;
    }

    public void setDiscountAmnt(String DiscountAmnt) {
        mDiscountAmnt = DiscountAmnt;
    }

    public String getGSTTotalAmnt() {
        return mGSTTotalAmnt;
    }

    public void setGSTTotalAmnt(String GSTTotalAmnt) {
        mGSTTotalAmnt = GSTTotalAmnt;
    }

    public String getGrandTotalOrder() {
        return mGrandTotalOrder;
    }

    public void setGrandTotalOrder(String GrandTotalOrder) {
        mGrandTotalOrder = GrandTotalOrder;
    }

    public String getGrandTotalWithoutTaxAmnt() {
        return mGrandTotalWithoutTaxAmnt;
    }

    public void setGrandTotalWithoutTaxAmnt(String GrandTotalWithoutTaxAmnt) {
        mGrandTotalWithoutTaxAmnt = GrandTotalWithoutTaxAmnt;
    }

    public String getIGSTTotalAmnt() {
        return mIGSTTotalAmnt;
    }

    public void setIGSTTotalAmnt(String IGSTTotalAmnt) {
        mIGSTTotalAmnt = IGSTTotalAmnt;
    }

    public Double getSGSTTotalAmnt() {
        return mSGSTTotalAmnt;
    }

    public void setSGSTTotalAmnt(Double SGSTTotalAmnt) {
        mSGSTTotalAmnt = SGSTTotalAmnt;
    }

    public List<Object> getSalesOrderDetails() {
        return mSalesOrderDetails;
    }

    public void setSalesOrderDetails(List<Object> SalesOrderDetails) {
        mSalesOrderDetails = SalesOrderDetails;
    }

    public List<SalesOrderDetailsList> getSalesOrderDetailsList() {
        return mSalesOrderDetailsList;
    }

    public void setSalesOrderDetailsList(List<SalesOrderDetailsList> SalesOrderDetailsList) {
        mSalesOrderDetailsList = SalesOrderDetailsList;
    }

    public String getSalesOrderId() {
        return mSalesOrderId;
    }

    public void setSalesOrderId(String SalesOrderId) {
        mSalesOrderId = SalesOrderId;
    }

    public ShopDetails getShopDetails() {
        return mShopDetails;
    }

    public void setShopDetails(ShopDetails ShopDetails) {
        mShopDetails = ShopDetails;
    }

    public String getShopId() {
        return mShopId;
    }

    public void setShopId(String ShopId) {
        mShopId = ShopId;
    }

    public Object getSoldDate() {
        return mSoldDate;
    }

    public void setSoldDate(Object SoldDate) {
        mSoldDate = SoldDate;
    }

    public String getSoldDateString() {
        return mSoldDateString;
    }

    public void setSoldDateString(String SoldDateString) {
        mSoldDateString = SoldDateString;
    }

    public String getTotalOrder() {
        return mTotalOrder;
    }

    public void setTotalOrder(String TotalOrder) {
        mTotalOrder = TotalOrder;
    }


}
