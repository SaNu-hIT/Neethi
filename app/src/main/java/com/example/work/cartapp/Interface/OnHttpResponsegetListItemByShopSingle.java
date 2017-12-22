package com.example.work.cartapp.Interface;



import com.example.work.cartapp.Mod.SingleItemModel.Data;
import com.example.work.cartapp.Model.ListItemsShop.Datum;

import java.util.List;

/**
 * Created by work on 7/24/2017.
 */

public interface OnHttpResponsegetListItemByShopSingle {
    void OnSuccessListItemByShopSingle(String message, Data mData);
    void OnFailedListItemByShopSingle(String message);
}
