package com.example.work.cartapp.Interface;

import okhttp3.ResponseBody;

/**
 * Created by work on 7/24/2017.
 */

public interface OnHttpResponseDownloadItem {
    void OnSuccessItem(String message, ResponseBody body);
    void OnFailedItem(String message);
}
