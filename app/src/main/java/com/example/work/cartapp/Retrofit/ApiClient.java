package com.example.work.cartapp.Retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by intellyelabs on 09/03/17.
 */

public class ApiClient {
//    public static final String BASE_URL="http://neethidemo-001-site1.atempurl.com";
    public static final String BASE_URL="http://neethidemo1-001-site1.itempurl.com";
//    public static final String BASE_URL="http://saneeshvs-001-site1.itempurl.com";
    public static Retrofit retrofit=null;
    public static Retrofit getClient()
    {
        if (retrofit==null)
        {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100, TimeUnit.SECONDS).build();
            retrofit=new Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
