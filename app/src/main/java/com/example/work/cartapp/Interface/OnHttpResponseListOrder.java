package com.example.work.cartapp.Interface;



import com.example.work.cartapp.Model.ListModel.ListOrderNewModel.Datum;

import java.util.List;

/**
 * Created by work on 7/24/2017.
 */

public interface OnHttpResponseListOrder {
    void OnSuccessListOrder (String message, List<Datum> mData);
    void OnFailedListOrder (String message);
}
