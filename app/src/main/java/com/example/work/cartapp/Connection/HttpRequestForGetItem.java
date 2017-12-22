package com.example.work.cartapp.Connection;


import android.util.Log;

import com.example.work.cartapp.Interface.OnHttpResponsegetItem;
import com.example.work.cartapp.Model.ListModel.ItemModel.Data;
import com.example.work.cartapp.Model.ListModel.ItemModel.ItemModel;
import com.example.work.cartapp.Retrofit.ApiClient;
import com.example.work.cartapp.Retrofit.ApiInterfaces;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by intellyelabs on 29/06/17.
 */

public class HttpRequestForGetItem {
    OnHttpResponsegetItem onHttpRespoceForEvents;

    public HttpRequestForGetItem(OnHttpResponsegetItem onHttpRespoceForEvents) {
        this.onHttpRespoceForEvents = onHttpRespoceForEvents;
    }

    public void getItem(String eventid)
    {

        ApiInterfaces apiInterfaces= ApiClient.getClient().create(ApiInterfaces.class);
        Call<ItemModel> call=apiInterfaces.getItem(eventid);
        call.enqueue(new Callback<ItemModel>() {
            @Override
            public void onResponse(Call<ItemModel> call, Response<ItemModel> response) {
                if(response.message().equals("OK"))
                {

                    Log.e("onResponse: ","hey lo" );

                    if(response.body().getSuccess().equals(true))
                    {


                        Log.e("onResponse: ","hey lo" );
                        Data events=response.body().getData();
                        onHttpRespoceForEvents.OnSuccessItem("Suceess",events);


                    }
                    else
                    {
                        onHttpRespoceForEvents.OnFailedItem("No data found");
                    }

                }
                else
                {
                    onHttpRespoceForEvents.OnFailedItem("Failed");
                }

            }

            @Override
            public void onFailure(Call<ItemModel> call, Throwable t) {

                onHttpRespoceForEvents.OnFailedItem("Server error");
            }
        });
    }

}
