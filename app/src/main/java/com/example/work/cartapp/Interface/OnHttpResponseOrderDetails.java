package com.example.work.cartapp.Interface;

import com.example.work.cartapp.Model.ListModel.DetailsList.Data;

import java.util.List;

/**
 * Created by work on 7/24/2017.
 */

public interface OnHttpResponseOrderDetails {
    void OnSuccessListOrderDetails(String message, Data mData);
    void OnFailedListOrderDetails(String message);
}
