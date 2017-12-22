package com.example.work.cartapp.Retrofit;


import com.example.work.cartapp.Mod.DownModel;
import com.example.work.cartapp.Mod.SaveDataToServerBean.SaveDataModel;
import com.example.work.cartapp.Mod.SingleItemModel.SingleStockItme;
import com.example.work.cartapp.Model.ListItemsShop.ListItembyShopBean;
import com.example.work.cartapp.Model.ListModel.DetailsList.DetailsListItemModel;
import com.example.work.cartapp.Model.ListModel.ItemListModel;
import com.example.work.cartapp.Model.ListModel.ItemModel.ItemModel;

import com.example.work.cartapp.Model.ListModel.ListOrderNewModel.ListOrderNewModelList;
import com.example.work.cartapp.Model.ListModel.ParameterListOrder;
import com.example.work.cartapp.Model.ListModel.SaveRespo.SaveOrder;
import com.example.work.cartapp.Model.LoginModel.LogResp;
import com.example.work.cartapp.Model.POST.GetListItembyShopmodel;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by intellyelabs on 09/03/17.
 */

public interface ApiInterfaces {

    @FormUrlEncoded
    @POST("Settings/ListItems")
    Call<ItemListModel> getItemList(@Field("itemId") String itemId);

    @FormUrlEncoded
    @POST("Login/Login")
    Call<LogResp> getlogin(@Field("UserName") String UserName, @Field("Password") String Password);

    @FormUrlEncoded
    @POST("Settings/ListItems")
    Call<ItemModel> getItem(@Field("itemId") String itemId);

    @POST("Order/ListOrders")
    Call<ListOrderNewModelList> getListOrders(@Body ParameterListOrder parameterListOrder);


  @POST("Order/ListOrders")
    Call<DetailsListItemModel> getListOrdersnew(@Body ParameterListOrder parameterListOrder);


//    @POST("Order/AddSalesOrder_Summary_Multiple")
//    Call<SaveOrder> saveOrder(@Body OrderBeans dataJson);
//

    @POST("Order/AddSalesOrder_Summary_Multiple")
    Call<SaveOrder> saveOrderToServer(@Body SaveDataModel dataJson);

    @POST("Settings/ListShop_Items")
    Call<ListItembyShopBean> getItemListByShop(@Body GetListItembyShopmodel getListItembyShopmodel);

 @POST("Settings/ListShop_Items")
    Call<SingleStockItme> getItemListByShopSingle(@Body GetListItembyShopmodel getListItembyShopmodel);


@POST("")
    @Streaming
    Call<ResponseBody> downloadFile(@Url String url, @Body DownModel downModel);

}
