package com.example.work.cartapp.Connection;


import android.util.Log;

import com.example.work.cartapp.Interface.OnHttpResponseDownloadItem;
import com.example.work.cartapp.Interface.OnHttpResponsegetItem;
import com.example.work.cartapp.Mod.DownModel;
import com.example.work.cartapp.Retrofit.ApiClient;
import com.example.work.cartapp.Retrofit.ApiInterfaces;

import java.io.File;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by intellyelabs on 29/06/17.
 */

public class HttpRequestForDownLoadtem {
    OnHttpResponseDownloadItem onHttpRespoceForEvents;

    public HttpRequestForDownLoadtem(OnHttpResponseDownloadItem onHttpRespoceForEvents) {
        this.onHttpRespoceForEvents = onHttpRespoceForEvents;
    }

    public void download(String url, DownModel downModel)
    {




        ApiInterfaces apiInterfaces= ApiClient.getClient().create(ApiInterfaces.class);
        Call<ResponseBody> call=apiInterfaces.downloadFile(url,downModel);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e( "onResponse: ",""+response );
                Log.e( "onResponse: ",""+response.body() );

                if(response.message().equals("OK"))
                {

                     onHttpRespoceForEvents.OnSuccessItem("success",response.body());
                }
                else
                {
                    onHttpRespoceForEvents.OnFailedItem("Failed");
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                onHttpRespoceForEvents.OnFailedItem("Server error");
            }
        });
    }

}
