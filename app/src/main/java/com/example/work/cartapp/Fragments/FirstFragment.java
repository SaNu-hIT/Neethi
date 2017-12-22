package com.example.work.cartapp.Fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.work.cartapp.Activitys.ScannerActivity;
import com.example.work.cartapp.Adapter.ItemListAdapter;

import com.example.work.cartapp.Connection.HttpRequestForGetListItemByShopSingle;
import com.example.work.cartapp.Connection.HttpRequestSaveOrderToServer;
import com.example.work.cartapp.Interface.OnHttpResponseSaveOrder;
import com.example.work.cartapp.Interface.OnHttpResponsegetItem;
import com.example.work.cartapp.Extras.ExternLib.PassEvent;
import com.example.work.cartapp.Interface.OnHttpResponsegetListItemByShop;
import com.example.work.cartapp.Interface.OnHttpResponsegetListItemByShopSingle;
import com.example.work.cartapp.Mod.SalesOrderDetailsListForRecyclerView;


import com.example.work.cartapp.Mod.SaveDataToServerBean.SalesOrderDetailsList;
import com.example.work.cartapp.Mod.SaveDataToServerBean.SaveDataModel;
import com.example.work.cartapp.Model.ListItemsShop.Datum;
import com.example.work.cartapp.Model.ListModel.Cat_recycler;
import com.example.work.cartapp.Model.ListModel.ItemModel.Data;

import com.example.work.cartapp.Interface.OnClickClose;
import com.example.work.cartapp.Model.POST.GetListItembyShopmodel;

import com.example.work.cartapp.R;
import com.example.work.cartapp.Extras.SessionManager.SessionManager;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.squareup.otto.Subscribe;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.refactor.lib.colordialog.PromptDialog;


public class FirstFragment extends Fragment implements OnHttpResponsegetItem, OnClickClose, OnHttpResponseSaveOrder, OnHttpResponsegetListItemByShop, OnHttpResponsegetListItemByShopSingle {

    Button btnScan, generateinvoice, btnAddItem;
    ImageView btnAddd;
    TextView tv_ErrorMessage;
    SessionManager sessionManager;
    EditText left_cl_axis, left_cl_cl, left_cl_sph, left_add_sph, left_n_va, left_n_axis, left_n_cyl, left_n_sph, left_d_va, left_d_axis, left_d_cyl, left_d_sph, right_cl_va, right_cl_axis, right_cl_cl, left_cl_va, right_cl_sph, right_add_sph, right_n_va, right_n_axis, right_d_cyl, right_n_cyl, right_n_sph, right_d_va, right_d_sph, right_d_axis, Gradnd_Total, TotalAmount, CESS_Total, IGST_Total, GST_Total, SGST_Total, edtName, CGST_Total, edtPhone, edtQuantity, edtPrice, edtEmail, edtCode, edtProductName, editDiscount;  // lenstype, llenscode
    private ItemListAdapter categoruAdapter;
    private RecyclerView recyclerView;
    private ProgressDialog progressBar;
    double final_total;
    double gross_total;
    int itmid_varialble;
    List<Double> totalvaluearray;
    List<Double> grossvaluearray;
    LinearLayout header_text_item;
    List<SalesOrderDetailsListForRecyclerView> salesOrderDetailsListForRecyclerViews;
    private ArrayList<Cat_recycler> catg;
    private ArrayList<SalesOrderDetailsListForRecyclerView> listSalesOrderDetailsList;
    private ArrayList<SalesOrderDetailsList> listSalesOrderDetailsList_new;
    private ArrayList<ArrayList<Cat_recycler>> full_cate;

    public static FirstFragment newInstance() {
        FirstFragment fragment = new FirstFragment();
        return fragment;
    }

    private LayoutInflater mInflater;


    @Override
    public void onResume() {
        super.onResume();
        String id = sessionManager.getPId();
        Log.e("onResume: ", "" + id);
        if (!id.equals("")) {
            getProdutcDetails(id);
        }


    }

    private void getProdutcDetails(String id) {

        Log.e("getProdutcDetails: ", "" + id);
        progressBar.show();
        SessionManager sessionManager = new SessionManager(getActivity());
        Long shopid = sessionManager.getUserId();
        GetListItembyShopmodel getListItembyShopmodel = new GetListItembyShopmodel();
        getListItembyShopmodel.setItemDataId((long) 0);
        getListItembyShopmodel.setShopId(shopid);
        getListItembyShopmodel.setQr_ITemId(Long.valueOf(id));
        HttpRequestForGetListItemByShopSingle httpRequestForGetListItemByShop = new HttpRequestForGetListItemByShopSingle(this);
        httpRequestForGetListItemByShop.getItemSingle(getListItembyShopmodel);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        sessionManager = new SessionManager(getActivity());
        mInflater = LayoutInflater.from(getActivity());
        progressBar = new ProgressDialog(getActivity(),
                ProgressDialog.STYLE_SPINNER);
        progressBar.setIndeterminate(false);
        progressBar.setMessage("Loading...");
        progressBar.setCancelable(false);
        InitilizeObjectsAndList();


        View rootView = inflater.inflate(R.layout.fragment_first, container, false);

        btnAddd = (ImageView) rootView.findViewById(R.id.btnAddd);
        generateinvoice = (Button) rootView.findViewById(R.id.generateinvoice);

        Gradnd_Total = (EditText) rootView.findViewById(R.id.Gradnd_Total);
        TotalAmount = (EditText) rootView.findViewById(R.id.TotalAmount);

        GST_Total = (EditText) rootView.findViewById(R.id.GST_Total);
        CESS_Total = (EditText) rootView.findViewById(R.id.CESS_Total);
        IGST_Total = (EditText) rootView.findViewById(R.id.IGST_Total);
        SGST_Total = (EditText) rootView.findViewById(R.id.SGST_Total);
        CGST_Total = (EditText) rootView.findViewById(R.id.CGST_Total);


        header_text_item = (LinearLayout) rootView.findViewById(R.id.header_text_item);


        edtName = (EditText) rootView.findViewById(R.id.edtName);
        edtPhone = (EditText) rootView.findViewById(R.id.edtPhone);
        edtEmail = (EditText) rootView.findViewById(R.id.edtEmail);


        recyclerView = rootView.findViewById(R.id.recyclerview);
        sessionManager.setPId("");


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        generateinvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Validate()) {
                    String name = edtName.getText().toString().trim();
                    String phone = edtPhone.getText().toString().trim();
                    String qty = edtQuantity.getText().toString().trim();
                    String code = edtCode.getText().toString().trim();
                    String pName = edtProductName.getText().toString().trim();
                    String price = edtPrice.getText().toString().trim();
                    String mail = edtEmail.getText().toString().trim();
                    String GradndTotal = Gradnd_Total.getText().toString().trim();
                    String TotaAmount = TotalAmount.getText().toString().trim();
                    String GSTTotal = GST_Total.getText().toString().trim();
                    String CESSTotal = CESS_Total.getText().toString().trim();
                    String IGSTTotal = IGST_Total.getText().toString().trim();
                    String SGSTTotal = SGST_Total.getText().toString().trim();
                    String CGSTTotal = CGST_Total.getText().toString().trim();
                    progressBar.show();
                    Log.e("itmid_varialble: ", "" + itmid_varialble);
//                    SaveDataBeanNew saveDataBeanNew = new SaveDataBeanNew();
//                    SalesOrderSummary salesOrderSUmmary = new SalesOrderSummary();
//                    salesOrderSUmmary.setCustomerName(name);
//                    salesOrderSUmmary.setCustomerPhno(phone);
//                    salesOrderSUmmary.setCustomerAddress(mail);
//                    salesOrderSUmmary.setCESSTotal(CESSTotal);
//                    salesOrderSUmmary.setCGSTTotal(CGSTTotal);
//                    salesOrderSUmmary.setGSTTotal(GSTTotal);
//                    salesOrderSUmmary.setIGSTTotal(IGSTTotal);
//                    salesOrderSUmmary.setSGSTTotal(SGSTTotal);
//                    salesOrderSUmmary.setTotalOrder(TotaAmount);
//                    salesOrderSUmmary.setGrandTotalOrder(GradndTotal);
//                    salesOrderSUmmary.setSalesOrderId("0");
//                    SessionManager sessionManager = new SessionManager(getActivity());
//                    String shopid = sessionManager.getUserId().toString();
//                    salesOrderSUmmary.setShopId(shopid);
//                    salesOrderSUmmary.setSoldDate("10/11/2015");
//                    salesOrderSUmmary.setDiscount("0");
//                    salesOrderSUmmary.setCustomerAddress("0");
//
//                    saveDataBeanNew.setSalesOrderSummary(salesOrderSUmmary);
//                    saveDataBeanNew.setSalesOrderDetailsList(listSalesOrderDetailsList_new);
//                    SaveDataToApi(saveDataBeanNew);
                }
            }
        });

        btnAddd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)

            {

//                getProdutcDetails("355"); //for testing replace it by scanner


                Intent intent = new Intent(getActivity(), ScannerActivity.class);
                startActivity(intent);
//                showDialogOforeder();


            }
        });
        return rootView;
    }

    private void SaveDataToApi(SaveDataModel saveDataBeanNew) {
        Gson gson = new Gson();
        String json = gson.toJson(saveDataBeanNew);
        int s = saveDataBeanNew.getSalesOrderDetailsList().size();

        HttpRequestSaveOrderToServer httpRequestSaveOrderToServer = new HttpRequestSaveOrderToServer(this);
        httpRequestSaveOrderToServer.SaveOrder(saveDataBeanNew);
    }

    private void showDialogOforeder() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.add_order);
        dialog.setTitle("NEETHI");
        left_cl_axis = (EditText) dialog.findViewById(R.id.left_cl_axis);
        left_cl_cl = (EditText) dialog.findViewById(R.id.left_cl_cl);
        left_cl_sph = (EditText) dialog.findViewById(R.id.left_cl_sph);
        left_cl_va = (EditText) dialog.findViewById(R.id.left_cl_va);
        left_add_sph = (EditText) dialog.findViewById(R.id.left_add_sph);
        left_n_va = (EditText) dialog.findViewById(R.id.left_n_va);
        left_n_axis = (EditText) dialog.findViewById(R.id.left_n_axis);
        left_n_cyl = (EditText) dialog.findViewById(R.id.left_n_cyl);
        left_n_sph = (EditText) dialog.findViewById(R.id.left_n_sph);
        left_d_va = (EditText) dialog.findViewById(R.id.left_d_va);
        left_d_axis = (EditText) dialog.findViewById(R.id.left_d_axis);
        right_cl_sph = (EditText) dialog.findViewById(R.id.right_cl_sph);
        right_add_sph = (EditText) dialog.findViewById(R.id.right_add_sph);
        right_n_va = (EditText) dialog.findViewById(R.id.right_n_va);
        right_n_axis = (EditText) dialog.findViewById(R.id.right_n_axis);
        right_d_cyl = (EditText) dialog.findViewById(R.id.right_d_cyl);
        right_n_sph = (EditText) dialog.findViewById(R.id.right_n_sph);
        right_d_va = (EditText) dialog.findViewById(R.id.right_d_va);
        right_n_cyl = (EditText) dialog.findViewById(R.id.right_n_cyl);
        right_d_axis = (EditText) dialog.findViewById(R.id.right_d_axis);
        right_d_sph = (EditText) dialog.findViewById(R.id.right_d_sph);
        right_cl_cl = (EditText) dialog.findViewById(R.id.right_cl_cl);
        right_cl_axis = (EditText) dialog.findViewById(R.id.right_cl_axis);
        right_cl_va = (EditText) dialog.findViewById(R.id.right_cl_va);
        left_d_sph = (EditText) dialog.findViewById(R.id.left_d_sph);
        left_d_cyl = (EditText) dialog.findViewById(R.id.left_d_cyl);
        edtCode = (EditText) dialog.findViewById(R.id.edtCode);
        edtPrice = (EditText) dialog.findViewById(R.id.edtPrice);
        editDiscount = (EditText) dialog.findViewById(R.id.editDiscount);
        edtProductName = (EditText) dialog.findViewById(R.id.edtProductName);
        edtQuantity = (EditText) dialog.findViewById(R.id.edtQuantity);
        btnAddItem = (Button) dialog.findViewById(R.id.btnAddItem);
//                text.setText("Android custom dialog example!");
        btnScan = (Button) dialog.findViewById(R.id.btnScan);

//                // if button is clicked, close the custom dialog
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                        edtProductName.setText("");
//                        edtCode.setText("");
//                        edtPrice.setText("");
//
//                        left_cl_axis.setText("");
//                        left_cl_cl.setText("");
//                        left_cl_sph.setText("");
//                        left_add_sph.setText("");
//                        left_n_va.setText("");
//                        left_n_axis.setText("");
//                        left_n_cyl.setText("");
//                        left_n_sph.setText("");
//                        left_d_va.setText("");
//                        left_d_axis.setText("");
//                        left_d_cyl.setText("");
//                        left_d_sph.setText("");
//                        right_cl_va.setText("");
//                        right_cl_axis.setText("");
//                        right_cl_cl.setText("");
//                        left_cl_va.setText("");
//                        right_cl_sph.setText("");
//                        right_add_sph.setText("");
//                        right_n_va.setText("");
//                        right_n_axis.setText("");
//                        right_d_cyl.setText("");
//                        right_n_sph.setText("");
//                        right_d_va.setText("");
//                        right_n_cyl.setText("");
//                        right_d_axis.setText("");


                Intent intent = new Intent(getActivity(), ScannerActivity.class);
                startActivity(intent);
            }
        });

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String left_cl_axis_string = left_cl_axis.getText().toString();
                String left_cl_cl_string = left_cl_cl.getText().toString();
                String left_cl_sph_string = left_cl_sph.getText().toString();
                String left_cl_va_string = left_cl_va.getText().toString();
                String left_add_sph_string = left_add_sph.getText().toString();
                String left_n_va_string = left_n_va.getText().toString();
                String left_n_axis_string = left_n_axis.getText().toString();
                String left_n_cyl_string = left_n_cyl.getText().toString();
                String left_n_sph_string = left_n_sph.getText().toString();
                String left_d_va_string = left_d_va.getText().toString();
                String left_d_axis_string = left_d_axis.getText().toString();


                String right_cl_sph_string = right_cl_sph.getText().toString();
                String right_cl_cl_string = right_cl_cl.getText().toString();
                String right_cl_axis_string = right_cl_axis.getText().toString();
                String right_cl_va_string = right_cl_va.getText().toString();
                String left_d_sph_string = left_d_sph.getText().toString();
                String left_d_cyl_string = left_d_cyl.getText().toString();
                String right_add_sph_string = right_add_sph.getText().toString();
                String right_n_va_string = right_n_va.getText().toString();
                String right_n_axis_string = right_n_axis.getText().toString();
                String right_d_cyl_string = right_d_cyl.getText().toString();
                String right_n_sph_string = right_n_sph.getText().toString();

                String right_d_va_string = right_d_va.getText().toString();
                String right_n_cyl_string = right_n_cyl.getText().toString();
                String right_d_axis_string = right_d_axis.getText().toString();
                String right_d_sph_string = right_d_sph.getText().toString();
                String edtCode_string = edtCode.getText().toString();
                String edtPrice_string = edtPrice.getText().toString();
                String editDiscount_string = editDiscount.getText().toString();
                String edtProductName_string = edtProductName.getText().toString();
                String edtQuantity_string = edtQuantity.getText().toString();

                if (validate(edtProductName_string, edtQuantity_string, editDiscount_string, edtPrice_string, edtCode_string)) {
                    Long price = Long.valueOf(edtPrice_string);
                    Long qty = Long.valueOf(edtQuantity_string);

                    Long amount = price * qty;
                    AddItem(edtProductName_string, edtQuantity_string, editDiscount_string, edtPrice_string, edtCode_string,

                            right_d_sph_string, right_d_axis_string, right_n_cyl_string
                            , right_d_va_string, right_n_sph_string, right_d_cyl_string,
                            right_n_axis_string, right_n_va_string, right_add_sph_string,
                            right_cl_sph_string, left_d_axis_string, left_d_va_string,
                            left_n_sph_string, left_n_cyl_string, left_n_axis_string,
                            left_n_va_string, left_add_sph_string, left_cl_sph_string,
                            left_cl_cl_string, left_cl_axis_string, amount, left_cl_va_string,
                            right_cl_cl_string, right_cl_axis_string, right_cl_va_string,
                            left_d_sph_string, left_d_cyl_string);


                    if (left_d_cyl_string.equals("")) {
                        left_d_cyl_string = "0";
                    }
                    if (left_d_sph_string.equals("")) {
                        left_d_sph_string = "0";
                    }
                    if (right_cl_va_string.equals("")) {
                        right_cl_va_string = "0";
                    }
                    if (right_cl_axis_string.equals("")) {
                        right_cl_axis_string = "0";
                    }
                    if (right_cl_cl_string.equals("")) {
                        right_cl_cl_string = "0";
                    }
                    if (left_cl_va_string.equals("")) {
                        left_cl_va_string = "0";
                    }
                    if (left_cl_axis_string.equals("")) {
                        left_cl_axis_string = "0";
                    }
                    if (left_cl_cl_string.equals("")) {
                        left_cl_cl_string = "0";
                    }
                    if (left_cl_sph_string.equals("")) {
                        left_cl_sph_string = "0";
                    }
                    if (left_add_sph_string.equals("")) {
                        left_add_sph_string = "0";
                    }
                    if (left_n_va_string.equals("")) {
                        left_n_va_string = "0";
                    }
                    if (left_n_axis_string.equals("")) {
                        left_n_axis_string = "0";
                    }
                    if (left_n_cyl_string.equals("")) {
                        left_n_cyl_string = "0";
                    }


                    if (left_n_sph_string.equals("")) {
                        left_n_sph_string = "0";
                    }

                    if (left_d_va_string.equals("")) {
                        left_d_va_string = "0";
                    }
                    if (left_d_axis_string.equals("")) {
                        left_d_axis_string = "0";
                    }
                    if (right_cl_sph_string.equals("")) {
                        right_cl_sph_string = "0";
                    }
                    if (right_add_sph_string.equals("")) {
                        right_add_sph_string = "0";
                    }
                    if (right_n_va_string.equals("")) {
                        right_n_va_string = "0";
                    }

                    if (right_n_axis_string.equals("")) {
                        right_n_axis_string = "0";
                    }


                    if (right_n_sph_string.equals("")) {
                        right_n_sph_string = "0";
                    }
                    if (right_d_cyl_string.equals("")) {
                        right_d_cyl_string = "0";
                    }


                    if (right_d_va_string.equals("")) {
                        right_d_va_string = "0";
                    }

                    if (right_n_cyl_string.equals("")) {
                        right_n_cyl_string = "0";
                    }


                    if (right_d_sph_string.equals("")) {
                        right_d_sph_string = "0";
                    }
                    if (right_d_axis_string.equals("")) {
                        right_d_axis_string = "0";
                    }


                    SalesOrderDetailsList salesOrderDetailsList = new SalesOrderDetailsList();
                    salesOrderDetailsList.setSalesId(Long.valueOf(0));
                    salesOrderDetailsList.setSalesOrderId(Long.valueOf(0));

                    salesOrderDetailsList.setItemId(Long.valueOf(itmid_varialble));


                    salesOrderDetailsList.setQty(Long.valueOf(edtQuantity_string));
                    salesOrderDetailsList.setRate(Long.valueOf(edtPrice_string));
//                    salesOrderDetailsList.setAmnt(Long.valueOf(amount));

                    salesOrderDetailsList.setCGST(Long.valueOf(0));
                    salesOrderDetailsList.setSGST(Long.valueOf(0));
                    salesOrderDetailsList.setIGST(Long.valueOf(0));
                    salesOrderDetailsList.setCESS(Long.valueOf(0));


                    salesOrderDetailsList.setDiscount(Long.valueOf(editDiscount_string));

                    salesOrderDetailsList.setTotalTaxableValue(Long.valueOf("0"));
                    salesOrderDetailsList.setTotalValue(Long.valueOf(1000));


                    salesOrderDetailsList.setRightDSph(Long.valueOf(right_d_sph_string));


                    salesOrderDetailsList.setRightDCyl(Long.valueOf(right_d_cyl_string));

                    salesOrderDetailsList.setRightDAxis(Long.valueOf(right_d_axis_string));


                    salesOrderDetailsList.setRightDVa(Long.valueOf(right_d_va_string));


                    salesOrderDetailsList.setRightNSph(Long.valueOf(right_n_sph_string));


                    salesOrderDetailsList.setRightNCyl(Long.valueOf(right_n_cyl_string));
                    salesOrderDetailsList.setRightNAxis(Long.valueOf(right_n_axis_string));


                    salesOrderDetailsList.setRightNVa(Long.valueOf(right_n_va_string));
                    salesOrderDetailsList.setRightAddSph(Long.valueOf(right_add_sph_string));


                    salesOrderDetailsList.setRightClSph(Long.valueOf(right_cl_sph_string));
                    salesOrderDetailsList.setRightClCl(Long.valueOf(right_cl_cl_string));
                    salesOrderDetailsList.setRightClAxis(Long.valueOf(right_cl_axis_string));
                    salesOrderDetailsList.setRightClVa(Long.valueOf(right_cl_va_string));
                    salesOrderDetailsList.setLeftDSph(Long.valueOf(left_d_sph_string));


                    salesOrderDetailsList.setLeftDCyl(Long.valueOf(left_d_cyl_string));


                    salesOrderDetailsList.setLeftDAxis(Long.valueOf(left_d_axis_string));
                    salesOrderDetailsList.setLeftDVa(Long.valueOf(left_d_va_string));
                    salesOrderDetailsList.setLeftNSph(Long.valueOf(left_n_sph_string));
                    salesOrderDetailsList.setLeftNCyl(Long.valueOf(left_n_cyl_string));


                    salesOrderDetailsList.setLeftNAxis(Long.valueOf(left_n_axis_string));
                    salesOrderDetailsList.setLeftNVa(Long.valueOf(left_n_va_string));


                    salesOrderDetailsList.setLeftAddSph(Long.valueOf(left_add_sph_string));
                    salesOrderDetailsList.setLeftClSph(Long.valueOf(left_cl_sph_string));
                    salesOrderDetailsList.setLeftClCl(Long.valueOf(left_cl_cl_string));


                    salesOrderDetailsList.setLeftClAxis(Long.valueOf(left_cl_axis_string));
                    salesOrderDetailsList.setLeftClVa(Long.valueOf(left_cl_va_string));


                    listSalesOrderDetailsList_new.add(salesOrderDetailsList);

                    dialog.dismiss();


                }


            }
        });

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);



    }

    private boolean validate(String edtProductName_string, String edtQuantity_string, String editDiscount_string, String edtPrice_string, String edtCode_string) {
        boolean flag = true;
        if (edtProductName_string.equals("")) {
            flag = false;
            edtProductName.setError("Required feild");
            edtProductName.setFocusable(true);
        }
        if (edtQuantity_string.equals("")) {
            flag = false;
            edtQuantity.setError("Required feild");
        }
        if (editDiscount_string.equals("")) {
            flag = false;
            editDiscount.setError("Required feild");
        }
        if (edtPrice_string.equals("")) {
            flag = false;
            edtPrice.setError("Required feild");
        }
        if (edtCode_string.equals("")) {
            flag = false;
            edtCode.setError("Required feild");
        }
        return flag;
    }

    private void AddItem(String edtProductName_string, String edtQuantity_string, String editDiscount_string, String edtPrice_string, String edtCode_string, String right_d_sph_string, String right_d_axis_string, String right_n_cyl_string, String right_d_va_string, String right_n_sph_string, String right_d_cyl_string, String right_n_axis_string, String right_n_va_string, String right_add_sph_string, String right_cl_sph_string, String left_d_axis_string, String left_d_va_string, String left_n_sph_string, String left_n_cyl_string, String left_n_axis_string, String left_n_va_string, String left_add_sph_string, String left_cl_sph_string, String left_cl_cl_string, String left_cl_axis_string, Long amount, String left_cl_va_string, String right_cl_cl_string, String right_cl_axis_string, String right_cl_va_string, String left_d_sph_string, String left_d_cyl_string) {


        double price = Double.parseDouble(edtPrice_string);
        double qunatity = Double.parseDouble(edtQuantity_string);
        double discountedamount = price * 32 / 100;
        double actualprice = price - discountedamount;
        double totaldiscount = discountedamount * qunatity;

        double totalprice = actualprice * qunatity;
        grossvaluearray.add(price * qunatity);

        totalvaluearray.add(totalprice);

        Cat_recycler cat_recycler = new Cat_recycler(edtProductName_string, edtQuantity_string, edtCode_string,
                edtPrice_string, editDiscount_string, totalprice, discountedamount,
                totaldiscount, "extra", "extra", right_d_sph_string,
                right_d_cyl_string, right_d_axis_string, right_d_va_string,
                right_n_sph_string, right_n_cyl_string, right_n_axis_string, right_n_va_string, right_add_sph_string, right_cl_sph_string,
                right_cl_cl_string, right_cl_axis_string, right_cl_va_string,
                left_d_sph_string, left_d_cyl_string, left_d_axis_string,
                left_d_va_string, left_n_sph_string, left_n_cyl_string,
                left_n_axis_string, left_n_va_string, left_add_sph_string,
                left_cl_sph_string, left_cl_cl_string, left_cl_axis_string,
                left_cl_va_string);
        catg.add(cat_recycler);
        full_cate.add(catg);
        categoruAdapter = new ItemListAdapter(catg, getActivity());
        header_text_item.setVisibility(View.VISIBLE);

        categoruAdapter.SetListnerMon(this);
        recyclerView.setAdapter(categoruAdapter);
        categoruAdapter.notifyDataSetChanged();
        setTotalValue();


//        SalesOrderDetailsListForRecyclerView salesOrderDetailsList = new SalesOrderDetailsListForRecyclerView();
//        salesOrderDetailsList.setItemId(Long.valueOf(sessionManager.getPId()));
//        salesOrderDetailsList.setQty(Long.valueOf(edtQuantity.getText().toString()));
//        salesOrderDetailsList.setSalesOrderId(Long.valueOf(0));
//        salesOrderDetailsList.setCESS(Long.valueOf(0));
//        salesOrderDetailsList.setCGST(Long.valueOf(0));
//        salesOrderDetailsList.setIGST(Long.valueOf(0));
//        salesOrderDetailsList.setSGST(Long.valueOf(0));
//        salesOrderDetailsList.setDiscount(Long.valueOf(0));
//        salesOrderDetailsList.setSalesId(Long.valueOf(0));
//
//        listSalesOrderDetailsList.add(salesOrderDetailsList);
//        edtProductName.setText("");
//        edtQuantity.setText("");
//        edtCode.setText("");
//        edtPrice.setText("");
//        editDiscount.setText("");
//// totalvalue
//        setTotalValue();


    }


    private void InitilizeObjectsAndList() {


        CGST_list = new ArrayList<>();
        SGST_list = new ArrayList<>();
        IGST_list = new ArrayList<>();
        CESS_list = new ArrayList<>();
        rate_list = new ArrayList<>();
        quantity_list = new ArrayList<>();
        item_code_list = new ArrayList<>();
        itemId_list = new ArrayList<>();

        totalvaluearray = new ArrayList<>();
        grossvaluearray = new ArrayList<>();

        catg = new ArrayList<>();
        full_cate = new ArrayList<>();


        salesOrderDetailsListForRecyclerViews = new ArrayList<>();
        listSalesOrderDetailsList_new = new ArrayList<>();


    }

//    private void SaveApis(OrderBeans orderBeans) {
//        progressBar.show();
//        HttpRequestSaveOrder httpRequestSaveOrder = new HttpRequestSaveOrder(this);
//        httpRequestSaveOrder.SaveOrder(orderBeans);
//    }

    private boolean Validate() {
        String name = edtName.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String qty = edtQuantity.getText().toString().trim();
        String code = edtCode.getText().toString().trim();
        String pName = edtProductName.getText().toString().trim();
        String price = edtPrice.getText().toString().trim();
        String mail = edtEmail.getText().toString().trim();
        String GradndTotal = Gradnd_Total.getText().toString().trim();
        String TotaAmount = TotalAmount.getText().toString().trim();
        String GSTTotal = GST_Total.getText().toString().trim();
        String CESSTotal = CESS_Total.getText().toString().trim();
        String IGSTTotal = IGST_Total.getText().toString().trim();
        String SGSTTotal = SGST_Total.getText().toString().trim();
        String CGSTTotal = CGST_Total.getText().toString().trim();

        if (name.length() == 0 || phone.length() == 0 || CGSTTotal.length() == 0 || GradndTotal.length() == 0 || TotaAmount.length() == 0 || GSTTotal.length() == 0 || CESSTotal.length() == 0 || IGSTTotal.length() == 0 || SGSTTotal.length() == 0) {
            if (name.length() == 0) {
                edtName.setError("Field can't be blank");
                edtName.setFocusable(true);
            }
            if (phone.length() == 0) {
                edtPhone.setError("Field can't be blank");
            }
            if (GradndTotal.length() == 0) {
                Gradnd_Total.setError("Field can't be blank");
            }
            if (TotaAmount.length() == 0) {
                TotalAmount.setError("Field can't be blank");
            }
            if (GSTTotal.length() == 0) {
                GST_Total.setError("Field can't be blank");
            }
            if (CESSTotal.length() == 0) {
                CESS_Total.setError("Field can't be blank");
            }
            if (IGSTTotal.length() == 0) {
                IGST_Total.setError("Field can't be blank");
            }
            if (SGSTTotal.length() == 0) {
                SGST_Total.setError("Field can't be blank");
            }
            if (CGSTTotal.length() == 0) {
                CGST_Total.setError("Field can't be blank");
            }
            return false;
        } else {
            if (phone.length() == 10 || isValidEmail(mail)) {
                return true;
            } else {
                if (phone.length() != 10) {
                    edtPhone.setError("Invalid phone number");
                }
                if (isValidEmail(mail)) {
                    edtEmail.setError("Invalid mail id.");
                }
                return false;
            }
        }

    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    @Subscribe
    public void RefresListEvent(PassEvent event) {
        Toast.makeText(getActivity(), "fragemt" + event.getDataa(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnSuccessItem(String message, Data mData) {
        progressBar.cancel();
//        Toast.makeText(getActivity(), ""+message, Toast.LENGTH_SHORT).show();

        String itemname = mData.getItemName();
        String code = mData.getItemCode();
        String amount = mData.getSellingPrice();
        String model = mData.getManufaturer();
        String CGST = mData.getCGST();
        String SGST = mData.getSGST();
        String IGST = mData.getIGST();
        String CESS = mData.getCESS();


        edtProductName.setText(itemname);
        edtCode.setText(code);
        edtPrice.setText(amount);
        editDiscount.setText("32");


    }

    @Override
    public void OnFailedItem(String message) {
        progressBar.cancel();
        Toast.makeText(getActivity(), "" + message, Toast.LENGTH_SHORT).show();

    }


    private void setTotalValue() {
//        CGST_list.add(CGST);
//        SGST_list.add(SGST);
//        IGST_list.add(IGST);
//        CESS_list.add(CESS);
//        rate_list.add(amount);


//CGST_list_sum total
        double CGST_list_sum = 0;
        for (int s = 0; s < CGST_list.size(); s++) {
            double item = Double.parseDouble(CGST_list.get(s).toString());
            Log.e("CGST LIST: ", "" + item);
            CGST_list_sum = CGST_list_sum + Double.parseDouble(CGST_list.get(s));

        }


        String CGST_list_sums = String.valueOf(CGST_list_sum);
        CGST_Total.setText(CGST_list_sums);

//        SGST_list sum


//        double SGST_list_sum = 0;
//        for (int s = 0; s < SGST_list.size(); s++) {
//
//            SGST_list_sum = Double.parseDouble(SGST_list_sum + SGST_list_sum.get(s));
//
//        }

        double SGST_list_sum = 0;
        for (int s = 0; s < SGST_list.size(); s++) {
            double item = Double.parseDouble(SGST_list.get(s).toString());
            Log.e("CGST LIST: ", "" + item);
            SGST_list_sum = SGST_list_sum + Double.parseDouble(SGST_list.get(s));

        }


        String SGST_list_sums = String.valueOf(SGST_list_sum);
        SGST_Total.setText(SGST_list_sums);


//        SGST_list sum


//        double IGST_list_sum = 0;
//        for (int s = 0; s < IGST_list.size(); s++) {
//            IGST_list_sum = Double.parseDouble(SGST_list_sum + IGST_list.get(s));
//
//        }


        double IGST_list_sum = 0;
        for (int s = 0; s < IGST_list.size(); s++) {
            double item = Double.parseDouble(IGST_list.get(s).toString());
            Log.e("CGST LIST: ", "" + item);
            SGST_list_sum = SGST_list_sum + Double.parseDouble(IGST_list.get(s));

        }


        String IGST_lists_sums = String.valueOf(IGST_list_sum);
        IGST_Total.setText(IGST_lists_sums);

        //        CESS_list sum


//        double CESS_list_sum = 0;
//        for (int s = 0; s < CESS_list.size(); s++) {
//            CESS_list_sum = Double.parseDouble(SGST_list_sum + CESS_list.get(s));
//
//        }


        double CESS_list_sum = 0;
        for (int s = 0; s < CESS_list.size(); s++) {
            double item = Double.parseDouble(CESS_list.get(s).toString());
            Log.e("CGST LIST: ", "" + item);
            CESS_list_sum = CESS_list_sum + Double.parseDouble(CESS_list.get(s));

        }

        String CESS_list_sums = String.valueOf(CESS_list_sum);
        CESS_Total.setText(CESS_list_sums);


        //        CESS_list sum


//        double rate_list_sum = 0;
//        for (int s = 0; s < rate_list.size(); s++) {
//            rate_list_sum = Double.parseDouble(rate_list_sum + rate_list.get(s));
//
//        }


        double rate_list_sum = 0;
        for (int s = 0; s < rate_list.size(); s++) {
            double item = Double.parseDouble(rate_list.get(s).toString());
            Log.e("CGST LIST: ", "" + item);
            rate_list_sum = rate_list_sum + Double.parseDouble(rate_list.get(s));

        }
        String rate_list_sums = String.valueOf(rate_list_sum);
        TotalAmount.setText(rate_list_sums);


        double sum = 0;
        for (int s = 0; s < totalvaluearray.size(); s++) {
            sum = sum + totalvaluearray.get(s);

        }


        String sums = String.valueOf(sum);
        Gradnd_Total.setText(sums);
//
//        //gross value
//        double sum_orginalvalue = 0;
//        for (int s = 0; s < grossvaluearray.size(); s++) {
//            sum_orginalvalue = sum_orginalvalue + grossvaluearray.get(s);
//
//        }
//        String sum_org = String.valueOf(sum_orginalvalue);
//        TotalAmount.setText(sum_org);
    }


    @Override
    public void CloseItems(int position) {
        totalvaluearray.remove(position);
        grossvaluearray.remove(position);
        CGST_list.remove(position);
        SGST_list.remove(position);
        IGST_list.remove(position);
        CESS_list.remove(position);
        rate_list.remove(position);
        item_code_list.remove(position);
        itemId_list.remove(position);
        categoruAdapter.notifyDataSetChanged();
        setTotalValue();
    }

    @Override
    public void OnSuccessSaveOrder(boolean stautus, String message) {
        progressBar.cancel();
        clearAll();
        updateRecycler();

        new PromptDialog(getActivity())
                .setDialogType(PromptDialog.DIALOG_TYPE_SUCCESS)
                .setAnimationEnable(true)
                .setTitleText("Order")
                .setContentText("Order successfully placed")
                .setPositiveListener("OK", new PromptDialog.OnPositiveListener() {
                    @Override
                    public void onClick(PromptDialog dialog) {
                        dialog.dismiss();
                    }
                }).show();
//        Toast.makeText(getActivity(), "succ" + message, Toast.LENGTH_SHORT).show();
    }

    private void updateRecycler() {



    }

    private void clearAll() {

        Gradnd_Total.setText("");
                TotalAmount.setText("");

        GST_Total.setText("");
                CESS_Total.setText("");
                        IGST_Total.setText("");
                                SGST_Total.setText("");
                                        CGST_Total.setText("");

        header_text_item.setVisibility(View.GONE);
        edtName.setText("");
        edtPhone.setText("");
        edtEmail.setText("");
        listSalesOrderDetailsList_new.clear();
        salesOrderDetailsListForRecyclerViews.clear();
        CGST_list.clear();
        SGST_list.clear();
        IGST_list.clear();
        CESS_list.clear();
        rate_list.clear();
        item_code_list.clear();
        itemId_list.clear();
        categoruAdapter.notifyDataSetChanged();
              catg.clear();
              categoruAdapter.notifyDataSetChanged();

    }

    @Override
    public void OnFailedSaveOrder(String message) {
        progressBar.cancel();
        Toast.makeText(getActivity(), "fail" + message, Toast.LENGTH_SHORT).show();
    }


    List<String> CGST_list;
    List<String> SGST_list;
    List<String> IGST_list;
    List<String> CESS_list;
    List<String> rate_list;
    List<String> quantity_list;
    List<String> item_code_list;
    List<String> itemId_list;


    @Override
    public void OnSuccessListItemByShop(String message, List<Datum> mData) {

//        showDialogOforeder();
//        String itemname = mData.get(0).getItemsDetails().getItemName();
//        String code = mData.get(0).getItemsDetails().getItemCode();
//        String amount = mData.get(0).getItemsDetails().getSellingPrice();
//        String model = mData.get(0).getItemsDetails().getManufaturer();
//        String CGST = mData.get(0).getItemsDetails().getCGST();
//        String SGST = mData.get(0).getItemsDetails().getSGST();
//        String IGST = mData.get(0).getItemsDetails().getIGST();
//        String CESS = mData.get(0).getItemsDetails().getCESS();
//
//        String itemId = mData.get(0).getItemsDetails().getItemId().toString();
//        Log.e("item id ", "" + itemId);
//
//
//        edtProductName.setText(itemname);
//        edtCode.setText(code);
//        edtPrice.setText(amount);
//        editDiscount.setText("32");
//        if (CGST.equals("")) {
//            CGST = "0";
//        }
//
//        Log.e("CGST: ", "" + CGST);
//
//
//        CGST_list.add(CGST);
//        SGST_list.add(SGST);
//        IGST_list.add(IGST);
//        CESS_list.add(CESS);
//        rate_list.add(amount);
//        item_code_list.add(code);
//        itemId_list.add(itemId);
//
//        itmid_varialble = Integer.parseInt(itemId);
//
//        Log.e("size of itme ", "" + itemId_list.size());
//
//        progressBar.cancel();


    }

    @Override
    public void OnFailedListItemByShop(String message) {
        Snackbar.make(getView(), ""+message, Snackbar.LENGTH_INDEFINITE)
                .setAction("TryAgain", null).show();
//        progressBar.cancel();
//        Log.e(" failed ", "" + message);
//        Toast.makeText(getActivity(), "" + message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void OnSuccessListItemByShopSingle(String message, com.example.work.cartapp.Mod.SingleItemModel.Data mData) {

        showDialogOforeder();
        String itemname = mData.getItemsDetails().getItemName();
        String code = mData.getItemsDetails().getItemCode();
        String amount = mData.getItemsDetails().getSellingPrice();
        String model = mData.getItemsDetails().getManufaturer();
        String CGST = "" + mData.getItemsDetails().getCGST();
        String SGST = "" + mData.getItemsDetails().getSGST();
        String IGST = "" + mData.getItemsDetails().getIGST();
        String CESS = "" + mData.getItemsDetails().getCESS();
        String itemId = mData.getItemsDetails().getItemId().toString();

        String item_data_ids = mData.getItemDataId().toString();
        Log.e("item_data_ids ", "" + item_data_ids);


        edtProductName.setText(itemname);
        edtCode.setText(code);
        edtPrice.setText(amount);
        editDiscount.setText("32");
        edtQuantity.setFocusable(true);
        if (CGST.equals("null")) {
            CGST = "0";
        }
        if (SGST.equals("null")) {
            SGST = "0";
        }
        if (IGST.equals("null")) {
            IGST = "0";
        }
        if (CESS.equals("null")) {
            CESS = "0";
        }


        Log.e("CGST: ", "" + CGST);

        CGST_list.add(CGST);
        SGST_list.add(SGST);
        IGST_list.add(IGST);
        CESS_list.add(CESS);
        rate_list.add(amount);
        item_code_list.add(code);
        itemId_list.add(itemId);

        itmid_varialble = Integer.parseInt(item_data_ids);

        Log.e("size of itme ", "" + itemId_list.size());
        sessionManager.setPId("");

        progressBar.cancel();

    }

    @Override
    public void OnFailedListItemByShopSingle(String message) {

        progressBar.cancel();
        Log.e(" failed ", "" + message);

        Toast.makeText(getActivity(), "" + message, Toast.LENGTH_SHORT).show();

    }
}