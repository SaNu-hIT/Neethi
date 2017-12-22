package com.example.work.cartapp.Connection;


import android.util.Log;

import com.example.work.cartapp.Interface.OnHttpResponseOrderDetails;
import com.example.work.cartapp.Model.ListModel.DetailsList.DetailsListItemModel;
import com.example.work.cartapp.Model.ListModel.DetailsList.Data;

import com.example.work.cartapp.Model.ListModel.ParameterListOrder;
import com.example.work.cartapp.Retrofit.ApiClient;
import com.example.work.cartapp.Retrofit.ApiInterfaces;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by intellyelabs on 29/06/17.
 */

public class HttpRequestForOrder {
    OnHttpResponseOrderDetails onHttpRespoceForEvents;

    public HttpRequestForOrder(OnHttpResponseOrderDetails onHttpRespoceForEvents) {
        this.onHttpRespoceForEvents = onHttpRespoceForEvents;
    }

    public void getItemDetis(ParameterListOrder  parameterListOrder)
    {

        ApiInterfaces apiInterfaces= ApiClient.getClient().create(ApiInterfaces.class);
        Call<DetailsListItemModel> call=apiInterfaces.getListOrdersnew(parameterListOrder);
        call.enqueue(new Callback<DetailsListItemModel>() {
            @Override
            public void onResponse(Call<DetailsListItemModel> call, Response<DetailsListItemModel> response) {
                if(response.message().equals("OK"))
                {

                    Log.e("onResponse: ","hey lo" );

                    if(response.body().getSuccess().equals(true))
                    {


                        Log.e("onResponse: ","hey lo" );
                   Data events=response.body().getData();
                        onHttpRespoceForEvents.OnSuccessListOrderDetails("Suceess",events);


                    }
                    else
                    {
                        onHttpRespoceForEvents.OnFailedListOrderDetails("No data found");
                    }

                }
                else
                {
                    onHttpRespoceForEvents.OnFailedListOrderDetails("Failed");
                }

            }

            @Override
            public void onFailure(Call<DetailsListItemModel> call, Throwable t) {
                Log.e("onResponse: ",""+t.getMessage() );
                onHttpRespoceForEvents.OnFailedListOrderDetails("Server error");
            }
        });
    }

}
