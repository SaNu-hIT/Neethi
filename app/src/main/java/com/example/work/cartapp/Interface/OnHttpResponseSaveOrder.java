package com.example.work.cartapp.Interface;

/**
 * Created by work on 7/28/2017.
 */

public interface OnHttpResponseSaveOrder {
    void OnSuccessSaveOrder(boolean stautus, String message);
    void OnFailedSaveOrder(String message);
}
