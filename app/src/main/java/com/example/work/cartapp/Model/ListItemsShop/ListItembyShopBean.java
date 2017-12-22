
package com.example.work.cartapp.Model.ListItemsShop;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class ListItembyShopBean {

    @SerializedName("Data")
    private List<Datum> mData;
    @SerializedName("Message")
    private String mMessage;
    @SerializedName("Status")
    private Long mStatus;
    @SerializedName("Success")
    private Boolean mSuccess;
    @SerializedName("Token")
    private Object mToken;

    public List<Datum> getData() {
        return mData;
    }

    public void setData(List<Datum> Data) {
        mData = Data;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String Message) {
        mMessage = Message;
    }

    public Long getStatus() {
        return mStatus;
    }

    public void setStatus(Long Status) {
        mStatus = Status;
    }

    public Boolean getSuccess() {
        return mSuccess;
    }

    public void setSuccess(Boolean Success) {
        mSuccess = Success;
    }

    public Object getToken() {
        return mToken;
    }

    public void setToken(Object Token) {
        mToken = Token;
    }

}
