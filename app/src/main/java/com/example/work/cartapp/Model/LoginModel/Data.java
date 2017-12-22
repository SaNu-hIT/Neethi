
package com.example.work.cartapp.Model.LoginModel;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Data {

    @SerializedName("Address")
    private String mAddress;
    @SerializedName("Email")
    private String mEmail;
    @SerializedName("GSTIN")
    private String mGSTIN;
    @SerializedName("PhoneNo")
    private String mPhoneNo;
    @SerializedName("Shop_Id")
    private Long mShopId;
    @SerializedName("Shop_Name")
    private String mShopName;

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String Address) {
        mAddress = Address;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String Email) {
        mEmail = Email;
    }

    public String getGSTIN() {
        return mGSTIN;
    }

    public void setGSTIN(String GSTIN) {
        mGSTIN = GSTIN;
    }

    public String getPhoneNo() {
        return mPhoneNo;
    }

    public void setPhoneNo(String PhoneNo) {
        mPhoneNo = PhoneNo;
    }

    public Long getShopId() {
        return mShopId;
    }

    public void setShopId(Long ShopId) {
        mShopId = ShopId;
    }

    public String getShopName() {
        return mShopName;
    }

    public void setShopName(String ShopName) {
        mShopName = ShopName;
    }

}
