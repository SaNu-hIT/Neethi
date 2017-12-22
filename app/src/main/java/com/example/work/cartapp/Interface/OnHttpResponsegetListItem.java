package com.example.work.cartapp.Interface;

import com.example.work.cartapp.Model.ListModel.Datum;

import java.util.List;

/**
 * Created by work on 7/24/2017.
 */

public interface OnHttpResponsegetListItem {
    void OnSuccessListItem (String message, List<Datum> mData);
    void OnFailedListItem (String message);
}
