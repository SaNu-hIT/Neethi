package com.example.work.cartapp.Interface;

import com.example.work.cartapp.Model.ListModel.ItemModel.Data;

/**
 * Created by work on 7/24/2017.
 */

public interface OnHttpResponsegetItem {
    void OnSuccessItem(String message, Data mData);
    void OnFailedItem(String message);
}
