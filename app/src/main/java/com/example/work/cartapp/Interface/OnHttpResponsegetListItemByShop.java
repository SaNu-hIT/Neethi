package com.example.work.cartapp.Interface;



import com.example.work.cartapp.Model.ListItemsShop.Datum;

import java.util.List;

/**
 * Created by work on 7/24/2017.
 */

public interface OnHttpResponsegetListItemByShop {
    void OnSuccessListItemByShop(String message, List<Datum> mData);
    void OnFailedListItemByShop(String message);
}
