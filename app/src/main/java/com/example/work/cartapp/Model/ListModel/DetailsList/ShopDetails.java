
package com.example.work.cartapp.Model.ListModel.DetailsList;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class ShopDetails {

    @SerializedName("Shop_Id")
    private Integer shopId;
    @SerializedName("Shop_Name")
    private String shopName;
    @SerializedName("Address")
    private String address;
    @SerializedName("GSTIN")
    private String gSTIN;
    @SerializedName("PhoneNo")
    private String phoneNo;
    @SerializedName("Email")
    private String email;
    @SerializedName("UploadedBy")
    private Object uploadedBy;
    @SerializedName("UploadedOn")
    private Object uploadedOn;
    @SerializedName("Uploaded_IP")
    private Object uploadedIP;
    @SerializedName("LastModifiedBy")
    private Integer lastModifiedBy;
    @SerializedName("LastModified_IP")
    private Object lastModifiedIP;
    @SerializedName("LastModifiedOn")
    private String lastModifiedOn;
    @SerializedName("Password")
   
    private String password;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGSTIN() {
        return gSTIN;
    }

    public void setGSTIN(String gSTIN) {
        this.gSTIN = gSTIN;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(Object uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public Object getUploadedOn() {
        return uploadedOn;
    }

    public void setUploadedOn(Object uploadedOn) {
        this.uploadedOn = uploadedOn;
    }

    public Object getUploadedIP() {
        return uploadedIP;
    }

    public void setUploadedIP(Object uploadedIP) {
        this.uploadedIP = uploadedIP;
    }

    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Object getLastModifiedIP() {
        return lastModifiedIP;
    }

    public void setLastModifiedIP(Object lastModifiedIP) {
        this.lastModifiedIP = lastModifiedIP;
    }

    public String getLastModifiedOn() {
        return lastModifiedOn;
    }

    public void setLastModifiedOn(String lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
