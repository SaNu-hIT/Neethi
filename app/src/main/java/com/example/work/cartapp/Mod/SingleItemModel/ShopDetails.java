
package com.example.work.cartapp.Mod.SingleItemModel;


import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class ShopDetails {

    @SerializedName("Address")
    private String mAddress;
    @SerializedName("Email")
    private String mEmail;
    @SerializedName("GSTIN")
    private String mGSTIN;
    @SerializedName("LastModifiedBy")
    private Long mLastModifiedBy;
    @SerializedName("LastModified_IP")
    private Object mLastModifiedIP;
    @SerializedName("LastModifiedOn")
    private String mLastModifiedOn;
    @SerializedName("Password")
    private String mPassword;
    @SerializedName("PhoneNo")
    private String mPhoneNo;
    @SerializedName("Shop_Id")
    private Long mShopId;
    @SerializedName("Shop_Name")
    private String mShopName;
    @SerializedName("UploadedBy")
    private Object mUploadedBy;
    @SerializedName("Uploaded_IP")
    private Object mUploadedIP;
    @SerializedName("UploadedOn")
    private Object mUploadedOn;

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

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String Password) {
        mPassword = Password;
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

    public Object getUploadedBy() {
        return mUploadedBy;
    }

    public void setUploadedBy(Object UploadedBy) {
        mUploadedBy = UploadedBy;
    }

    public Object getUploadedIP() {
        return mUploadedIP;
    }

    public void setUploadedIP(Object UploadedIP) {
        mUploadedIP = UploadedIP;
    }

    public Object getUploadedOn() {
        return mUploadedOn;
    }

    public void setUploadedOn(Object UploadedOn) {
        mUploadedOn = UploadedOn;
    }

}
