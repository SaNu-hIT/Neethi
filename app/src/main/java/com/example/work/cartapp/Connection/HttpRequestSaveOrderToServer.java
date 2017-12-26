package com.example.work.cartapp.Connection;


import android.util.Log;

import com.example.work.cartapp.Interface.OnHttpResponseSaveOrder;
import com.example.work.cartapp.Mod.SaveDataToServerBean.SaveDataModel;
import com.example.work.cartapp.Model.ListModel.SaveRespo.Data;
import com.example.work.cartapp.Model.ListModel.SaveRespo.SaveOrder;

import com.example.work.cartapp.Retrofit.ApiClient;
import com.example.work.cartapp.Retrofit.ApiInterfaces;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by work on 7/28/2017.
 */

public class HttpRequestSaveOrderToServer {
    OnHttpResponseSaveOrder onHttpResponseUpdateNews;

    public HttpRequestSaveOrderToServer(OnHttpResponseSaveOrder onHttpResponseUpdateNews) {
        this.onHttpResponseUpdateNews = onHttpResponseUpdateNews;
    }


    public void SaveOrder(SaveDataModel dataJson) {

        Log.e( "SaveOrder: ",""+dataJson.getSalesOrderDetailsList().toString() );
        ApiInterfaces apiClient = ApiClient.getClient().create(ApiInterfaces.class);
        Call<SaveOrder> call = apiClient.saveOrderToServer(dataJson);
        call.enqueue(new Callback<SaveOrder>() {
            @Override
            public void onResponse(Call<SaveOrder> call, Response<SaveOrder> response) {

                String responce_string = response.toString();

                Log.e("Savedata", "onResponse: "+response.body().getMessage() );
                Log.e("Savedata", "onResponse: "+response.body().getStatus() );
                Log.e("Savedata", "onResponse: "+response.body().getSuccess() );
                if (response.message().equals("OK")) {



                    if (response.body().getSuccess()) {


                        Data data=response.body().getData();



                        onHttpResponseUpdateNews.OnSuccessSaveOrder(response.body().getSuccess(), response.body().getData().getSalesOrderId().toString());
                    } else {
                        onHttpResponseUpdateNews.OnFailedSaveOrder(response.body().getMessage());
                    }

                } else {
                    onHttpResponseUpdateNews.OnFailedSaveOrder("Server Error");

                }


            }

            @Override
            public void onFailure(Call<SaveOrder> call, Throwable t) {
                onHttpResponseUpdateNews.OnFailedSaveOrder(t.getMessage());
                System.out.println("errroe" + t.getMessage());
            }
        });
    }
}
