package com.example.work.cartapp.Connection;


import android.util.Log;

import com.example.work.cartapp.Interface.OnHttpResponseListOrder;

import com.example.work.cartapp.Model.ListModel.ListOrderNewModel.Datum;
import com.example.work.cartapp.Model.ListModel.ListOrderNewModel.ListOrderNewModelList;
import com.example.work.cartapp.Model.ListModel.ParameterListOrder;
import com.example.work.cartapp.Retrofit.ApiClient;
import com.example.work.cartapp.Retrofit.ApiInterfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by intellyelabs on 29/06/17.
 */

public class HttpRequestForListOrder {
    OnHttpResponseListOrder onHttpRespoceForEvents;

    public HttpRequestForListOrder(OnHttpResponseListOrder onHttpRespoceForEvents) {
        this.onHttpRespoceForEvents = onHttpRespoceForEvents;
    }

    public void getItem(ParameterListOrder  parameterListOrder)
    {
        Log.e("getItem: ","success" );

        ApiInterfaces apiInterfaces= ApiClient.getClient().create(ApiInterfaces.class);
        Call<ListOrderNewModelList> call=apiInterfaces.getListOrders(parameterListOrder);
        call.enqueue(new Callback<ListOrderNewModelList>() {
            @Override
            public void onResponse(Call<ListOrderNewModelList> call, Response<ListOrderNewModelList> response) {
                if(response.message().equals("OK"))
                {

                    Log.e("onResponse: ","hey lo" );

                    if(response.body().getSuccess().equals(true))
                    {


                        Log.e("onResponse: ","hey lo" );
                    List<Datum> events=response.body().getData();
                        onHttpRespoceForEvents.OnSuccessListOrder("Suceess",events);


                    }
                    else
                    {
                        Log.e("getItem: ","No data found" );
                        onHttpRespoceForEvents.OnFailedListOrder("No data found");
                    }

                }
                else
                {
                    Log.e("getItem: ","error api call" );
                    onHttpRespoceForEvents.OnFailedListOrder("Failed");
                }

            }

            @Override
            public void onFailure(Call<ListOrderNewModelList> call, Throwable t) {
                Log.e("getItem: ","Server error" );
                onHttpRespoceForEvents.OnFailedListOrder("Server error");
                Log.e( "onFailure: ", t.getMessage().toString());
            }
        });
    }

}
