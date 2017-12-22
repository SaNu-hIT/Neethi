
package com.example.work.cartapp.Mod;


import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DownModel {

    @SerializedName("pageOrientation")
    private String mPageOrientation;
    @SerializedName("pageSize")
    private String mPageSize;
    @SerializedName("type")
    private String mType;
    @SerializedName("url")
    private String mUrl;

    public String getPageOrientation() {
        return mPageOrientation;
    }

    public void setPageOrientation(String pageOrientation) {
        mPageOrientation = pageOrientation;
    }

    public String getPageSize() {
        return mPageSize;
    }

    public void setPageSize(String pageSize) {
        mPageSize = pageSize;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

}
