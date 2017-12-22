package com.example.work.cartapp.Model.LoginModel;

import android.util.Log;

import com.example.work.cartapp.Model.LoginModel.LogResp;
import com.example.work.cartapp.Model.LoginModel.OnHttpResponceForLogin;
import com.example.work.cartapp.Retrofit.ApiClient;
import com.example.work.cartapp.Retrofit.ApiInterfaces;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by intellyelabs on 13/03/17.
 */

public class HttpRequestForLogin {

OnHttpResponceForLogin onHttpResponceForLogin;

    public HttpRequestForLogin(OnHttpResponceForLogin onHttpResponceForLogin) {
        this.onHttpResponceForLogin = onHttpResponceForLogin;
    }

    public void LoginUser(String username, String password)
    {


        ApiInterfaces apiClient= ApiClient.getClient().create(ApiInterfaces.class);
        Call<LogResp> call=apiClient.getlogin(username,password);
        call.enqueue(new Callback<LogResp>() {
            @Override
            public void onResponse(Call<LogResp> call, Response<LogResp> response) {


                String responce_string=response.toString();
                Log.d("HttpRequestForLogin",responce_string);
                if(response.message().equals("OK"))
                {
                    if(response.body().getSuccess()  )
                    {

                        onHttpResponceForLogin.OnLoginSuccess(response.body().getMessage(),response.body().getData());
                    }
                    else
                    {
                        onHttpResponceForLogin.OnLoginFaild(response.body().getMessage());
                    }

                }else
                {
                    onHttpResponceForLogin.OnLoginFaild("Server Error !");

                }



            }

            @Override
            public void onFailure(Call<LogResp> call, Throwable t) {
                onHttpResponceForLogin.OnLoginFaild("Server Error !");
            }
        });

    }
}
