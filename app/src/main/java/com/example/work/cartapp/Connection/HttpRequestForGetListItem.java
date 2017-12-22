package com.example.work.cartapp.Connection;


import android.util.Log;

import com.example.work.cartapp.Interface.OnHttpResponsegetListItem;
import com.example.work.cartapp.Model.ListModel.Datum;
import com.example.work.cartapp.Model.ListModel.ItemListModel;
import com.example.work.cartapp.Retrofit.ApiClient;
import com.example.work.cartapp.Retrofit.ApiInterfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by intellyelabs on 29/06/17.
 */

public class HttpRequestForGetListItem {
    OnHttpResponsegetListItem onHttpRespoceForEvents;

    public HttpRequestForGetListItem(OnHttpResponsegetListItem onHttpRespoceForEvents) {
        this.onHttpRespoceForEvents = onHttpRespoceForEvents;
    }

    public void getEvents(String eventid)
    {

        ApiInterfaces apiInterfaces= ApiClient.getClient().create(ApiInterfaces.class);
        Call<ItemListModel> call=apiInterfaces.getItemList(eventid);
        call.enqueue(new Callback<ItemListModel>() {
            @Override
            public void onResponse(Call<ItemListModel> call, Response<ItemListModel> response) {
                if(response.message().equals("OK"))
                {

                    Log.e("onResponse: ","hey lo" );

                    if(response.body().getSuccess().equals(true))
                    {


                        Log.e("onResponse: ","hey lo" );
                        List<Datum> events=response.body().getData();
                        onHttpRespoceForEvents.OnSuccessListItem("Suceess",events);


                    }
                    else
                    {
                        onHttpRespoceForEvents.OnFailedListItem("No data found");
                    }

                }
                else
                {
                    onHttpRespoceForEvents.OnFailedListItem("Failed");
                }

            }

            @Override
            public void onFailure(Call<ItemListModel> call, Throwable t) {

                onHttpRespoceForEvents.OnFailedListItem("Server error");
            }
        });
    }

}
