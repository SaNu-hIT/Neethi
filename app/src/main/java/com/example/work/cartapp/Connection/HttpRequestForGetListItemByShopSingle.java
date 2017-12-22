package com.example.work.cartapp.Connection;


import android.util.Log;

import com.example.work.cartapp.Interface.OnHttpResponsegetListItemByShop;
import com.example.work.cartapp.Interface.OnHttpResponsegetListItemByShopSingle;
import com.example.work.cartapp.Mod.SingleItemModel.Data;
import com.example.work.cartapp.Mod.SingleItemModel.SingleStockItme;
import com.example.work.cartapp.Model.ListItemsShop.Datum;

import com.example.work.cartapp.Model.POST.GetListItembyShopmodel;
import com.example.work.cartapp.Retrofit.ApiClient;
import com.example.work.cartapp.Retrofit.ApiInterfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by intellyelabs on 29/06/17.
 */

public class HttpRequestForGetListItemByShopSingle {
    OnHttpResponsegetListItemByShopSingle onHttpRespoceForEvents;

    public HttpRequestForGetListItemByShopSingle(OnHttpResponsegetListItemByShopSingle onHttpRespoceForEvents) {
        this.onHttpRespoceForEvents = onHttpRespoceForEvents;
    }

    public void getItemSingle(GetListItembyShopmodel getListItembyShopmodel )
    {

        ApiInterfaces apiInterfaces= ApiClient.getClient().create(ApiInterfaces.class);
        Call<SingleStockItme> call=apiInterfaces.getItemListByShopSingle(getListItembyShopmodel);
        call.enqueue(new Callback<SingleStockItme>() {
            @Override
            public void onResponse(Call<SingleStockItme> call, Response<SingleStockItme> response) {
                if(response.message().equals("OK"))
                {

                    Log.e("onResponse: ","hey lo" );

                    if(response.body().getSuccess().equals(true))
                    {


                        Log.e("onResponse: ","hey successful" );
                       Data events=response.body().getData();
                        onHttpRespoceForEvents.OnSuccessListItemByShopSingle("Suceess",events);


                    }
                    else
                    {
                        onHttpRespoceForEvents.OnFailedListItemByShopSingle("No data found");
                    }

                }
                else
                {
                    onHttpRespoceForEvents.OnFailedListItemByShopSingle("Failed");
                }

            }

            @Override
            public void onFailure(Call<SingleStockItme> call, Throwable t) {
                Log.e( "onFailure: ",""+t );
                onHttpRespoceForEvents.OnFailedListItemByShopSingle("Server error");
            }
        });
    }

}
