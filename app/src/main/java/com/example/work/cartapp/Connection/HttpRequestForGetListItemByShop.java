package com.example.work.cartapp.Connection;


import android.util.Log;


import com.example.work.cartapp.Interface.OnHttpResponsegetListItemByShop;
import com.example.work.cartapp.Model.ListItemsShop.Datum;

import com.example.work.cartapp.Model.ListItemsShop.ListItembyShopBean;

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

public class HttpRequestForGetListItemByShop {
    OnHttpResponsegetListItemByShop onHttpRespoceForEvents;

    public HttpRequestForGetListItemByShop(OnHttpResponsegetListItemByShop onHttpRespoceForEvents) {
        this.onHttpRespoceForEvents = onHttpRespoceForEvents;
    }

    public void getEvents(GetListItembyShopmodel getListItembyShopmodel )
    {

        ApiInterfaces apiInterfaces= ApiClient.getClient().create(ApiInterfaces.class);
        Call<ListItembyShopBean> call=apiInterfaces.getItemListByShop(getListItembyShopmodel);
        call.enqueue(new Callback<ListItembyShopBean>() {
            @Override
            public void onResponse(Call<ListItembyShopBean> call, Response<ListItembyShopBean> response) {
                if(response.message().equals("OK"))
                {

                    Log.e("onResponse: ","hey lo" );

                    if(response.body().getSuccess().equals(true))
                    {


                        Log.e("onResponse: ","hey successful" );
                        List<Datum> events=response.body().getData();
                        onHttpRespoceForEvents.OnSuccessListItemByShop("Suceess",events);


                    }
                    else
                    {
                        onHttpRespoceForEvents.OnFailedListItemByShop("No data found");
                    }

                }
                else
                {
                    onHttpRespoceForEvents.OnFailedListItemByShop("Failed");
                }

            }

            @Override
            public void onFailure(Call<ListItembyShopBean> call, Throwable t) {
                Log.e( "onFailure: ",""+t );
                onHttpRespoceForEvents.OnFailedListItemByShop("Server error");
            }
        });
    }

}
