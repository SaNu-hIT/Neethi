package com.example.work.cartapp.Activitys;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.work.cartapp.Adapter.ItemListAdapter;
import com.example.work.cartapp.Adapter.MyEditTextMarwen;
import com.example.work.cartapp.Adapter.MyTextViewMarven;
import com.example.work.cartapp.Connection.HttpRequestForGetListItemByShop;
import com.example.work.cartapp.Connection.HttpRequestForGetListItemByShopSingle;
import com.example.work.cartapp.Connection.HttpRequestSaveOrderToServer;
import com.example.work.cartapp.Extras.SessionManager.SessionManager;
import com.example.work.cartapp.Interface.OnClickClose;
import com.example.work.cartapp.Interface.OnHttpResponseSaveOrder;
import com.example.work.cartapp.Interface.OnHttpResponsegetListItemByShop;
import com.example.work.cartapp.Interface.OnHttpResponsegetListItemByShopSingle;
import com.example.work.cartapp.Mod.SalesOrderDetailsListForRecyclerView;
import com.example.work.cartapp.Mod.SaveDataToServerBean.SalesOrderDetailsList;
import com.example.work.cartapp.Mod.SaveDataToServerBean.SalesOrderSummary;
import com.example.work.cartapp.Mod.SaveDataToServerBean.SaveDataModel;
import com.example.work.cartapp.Model.ListItemsShop.Datum;
import com.example.work.cartapp.Model.ListModel.Cat_recycler;
import com.example.work.cartapp.Model.POST.GetListItembyShopmodel;
import com.example.work.cartapp.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
public class TakeOder extends AppCompatActivity implements View.OnClickListener, OnClickClose, OnHttpResponsegetListItemByShopSingle, OnHttpResponsegetListItemByShop, OnHttpResponseSaveOrder {
    List<String> CGST_list;
    List<String> SGST_list;
    List<String> IGST_list;
    List<String> CESS_list;
    List<String> rate_list;
    List<String> quantity_list;
    List<String> item_code_list;
    List<String> itemId_list;
    MyTextViewMarven subtotal_total, gst_total, total_discount_amount, grandtotal_value;
    MyEditTextMarwen discuntamount_total,finalamount;
    Button both, lens, Frame;
    Button btnScan, generateinvoice, btnAddItem;
    ImageView btnAddd;
    TextView tv_ErrorMessage;
    SessionManager sessionManager;
    EditText left_cl_axis, left_cl_cl, left_cl_sph, left_add_sph, left_n_va, left_n_axis, left_n_cyl, left_n_sph, left_d_va, left_d_axis, left_d_cyl, left_d_sph, right_cl_va, right_cl_axis, right_cl_cl, left_cl_va, right_cl_sph, right_add_sph, right_n_va, right_n_axis, right_d_cyl, right_n_cyl, right_n_sph, right_d_va, right_d_sph, right_d_axis, Gradnd_Total, TotalAmount, CESS_Total, IGST_Total, GST_Total, SGST_Total,edtName, CGST_Total, edtPhone, edtQuantity, edtPrice, edtEmail, edtCode, edtProductName, editDiscount;  // lenstype, llenscode
    private ItemListAdapter categoruAdapter;
    private RecyclerView recyclerView;
    private ProgressDialog progressBar;
    double final_total;
    double gross_total;
    int itmid_varialble;
    List<Double> grosslvaluearray;
    List<Double> subtotalvaluearray;
    List<Double> totaldiscountamount;
    LinearLayout header_text_item;
    List<SalesOrderDetailsListForRecyclerView> salesOrderDetailsListForRecyclerViews;
    private ArrayList<Cat_recycler> catg;
    private ArrayList<SalesOrderDetailsListForRecyclerView> listSalesOrderDetailsList;
    private ArrayList<SalesOrderDetailsList> listSalesOrderDetailsList_new;
    private ArrayList<ArrayList<Cat_recycler>> full_cate;
    private ArrayList<String> listItemName;
    private ArrayList<String> listItemCode;
    private EditText total_lens;
    private EditText right_sph,right_cyl,right_axis,right_va,left_sph,left__cyl,left_axis ,left_va ;



    @Override
    public void onResume() {
        super.onResume();
        String id = sessionManager.getPId();
        Log.e("id : ", "" + id);
        if (!id.equals("")) {
            getProdutcDetails(id);
        }
    }
    com.example.work.cartapp.Mod.SaveDataToServerBean.SalesOrderDetailsList salesOrderDetailsList;
    private void getProdutcDetails(String id) {

        Log.e("getProdutcDetails: ", "" + id);
        progressBar.show();
        SessionManager sessionManager = new SessionManager(this);
        Long shopid = sessionManager.getUserId();
        GetListItembyShopmodel getListItembyShopmodel = new GetListItembyShopmodel();
        getListItembyShopmodel.setItemDataId((long) 0);
        getListItembyShopmodel.setShopId(shopid);
        getListItembyShopmodel.setQr_ITemId(Long.valueOf(id));
        HttpRequestForGetListItemByShopSingle httpRequestForGetListItemByShop = new HttpRequestForGetListItemByShopSingle(this);
        httpRequestForGetListItemByShop.getItemSingle(getListItembyShopmodel);

    }
    Spinner spinnerStockItems, lensfor, lenstype;
    Button saveorder;
    LinearLayout visible_button_andrate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_oder);
        this.setFinishOnTouchOutside(false);
        sessionManager = new SessionManager(this);
        progressBar = new ProgressDialog(this,
                ProgressDialog.STYLE_SPINNER);
        progressBar.setIndeterminate(false);
        progressBar.setMessage("Loading...");
        progressBar.setCancelable(false);
        edtName = (EditText) findViewById(R.id.edtName);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        discuntamount_total =  findViewById(R.id.discuntamount_total);
        finalamount =  findViewById(R.id.finalamount);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        subtotal_total = (MyTextViewMarven) findViewById(R.id.subtotal_total);
        gst_total = (MyTextViewMarven) findViewById(R.id.gst_total);
        total_discount_amount = (MyTextViewMarven) findViewById(R.id.total_discount_amount);
        grandtotal_value = (MyTextViewMarven) findViewById(R.id.grandtotal_value);


        visible_button_andrate = (LinearLayout) findViewById(R.id.visible_button_andrate);
        edtName.setText("Test name");
        edtPhone.setText("8086479017");
        edtEmail.setText("Test address for the cusomer");
        both = findViewById(R.id.both);
        lens = findViewById(R.id.lens);
        Frame = findViewById(R.id.frame);
        recyclerView = findViewById(R.id.recycerview_list);
        saveorder = findViewById(R.id.saveorder);
        visible_button_andrate.setVisibility(View.GONE);
        both.setOnClickListener(this);
        lens.setOnClickListener(this);
        Frame.setOnClickListener(this);
        InitilizeObjectsAndList();

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            //your codes here

        }

        saveorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveDataModel saveDataModel=new SaveDataModel();

                Calendar c = Calendar.getInstance();
                System.out.println("Current time => " + c.getTime());

                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c.getTime());


                String shopid=sessionManager.getUserId().toString();
                String custname=edtName.getText().toString();
                String phonenumber=edtPhone.getText().toString();
                String address=edtEmail.getText().toString();
                String discuntamount=discuntamount_total.getText().toString();


                SalesOrderSummary salesOrderSummary=new SalesOrderSummary();
                salesOrderSummary.setCustomerName(custname);
                salesOrderSummary.setCustomerPhno(phonenumber);
                salesOrderSummary.setCustomerAddress(address);
                salesOrderSummary.setShopId(shopid);
                salesOrderSummary.setSoldDate(formattedDate);
                salesOrderSummary.setSalesOrderId(Long.valueOf(0));
                salesOrderSummary.setmRound_Off(discuntamount);

                saveDataModel.setSalesOrderDetailsList(listSalesOrderDetailsList_new);
                saveDataModel.setSalesOrderSummary(salesOrderSummary);

                Gson gson = new Gson();
                String json = gson.toJson(saveDataModel);
                Log.e( "Final data for send: ",""+json );
                SaveDataToApi(saveDataModel);




            }
        });
    }

    private void SaveDataToApi(SaveDataModel saveDataBeanNew) {
        progressBar.setMessage("Please wait");
        progressBar.show();
        Gson gson = new Gson();
        String json = gson.toJson(saveDataBeanNew);
        Log.e( "SaveDataToApi: ",""+json );
        int s = saveDataBeanNew.getSalesOrderDetailsList().size();

        HttpRequestSaveOrderToServer httpRequestSaveOrderToServer = new HttpRequestSaveOrderToServer(this);
        httpRequestSaveOrderToServer.SaveOrder(saveDataBeanNew);
    }

//    //only layout is changing
//    private void showDialogOforederForLensandFrame() {
//
//
//        final Dialog dialog = new Dialog(this);
//        dialog.setContentView(R.layout.add_order);
//        dialog.setTitle("NEETHI");
//        lensfor = (Spinner) dialog.findViewById(R.id.lensfor);
//        lenstype = (Spinner) dialog.findViewById(R.id.lenstype);
//        spinnerStockItems=dialog.findViewById(R.id.spinner_item);
//        left_cl_axis = (EditText) dialog.findViewById(R.id.left_cl_axis);
//        left_cl_cl = (EditText) dialog.findViewById(R.id.left_cl_cl);
//        left_cl_sph = (EditText) dialog.findViewById(R.id.left_cl_sph);
//        left_cl_va = (EditText) dialog.findViewById(R.id.left_cl_va);
//        left_add_sph = (EditText) dialog.findViewById(R.id.left_add_sph);
//        left_n_va = (EditText) dialog.findViewById(R.id.left_n_va);
//        left_n_axis = (EditText) dialog.findViewById(R.id.left_n_axis);
//        left_n_cyl = (EditText) dialog.findViewById(R.id.left_n_cyl);
//        left_n_sph = (EditText) dialog.findViewById(R.id.left_n_sph);
//        left_d_va = (EditText) dialog.findViewById(R.id.left_d_va);
//        left_d_axis = (EditText) dialog.findViewById(R.id.left_d_axis);
//        right_cl_sph = (EditText) dialog.findViewById(R.id.right_cl_sph);
//        right_add_sph = (EditText) dialog.findViewById(R.id.right_add_sph);
//        right_n_va = (EditText) dialog.findViewById(R.id.right_n_va);
//        right_n_axis = (EditText) dialog.findViewById(R.id.right_n_axis);
//        right_d_cyl = (EditText) dialog.findViewById(R.id.right_d_cyl);
//        right_n_sph = (EditText) dialog.findViewById(R.id.right_n_sph);
//        right_d_va = (EditText) dialog.findViewById(R.id.right_d_va);
//        right_n_cyl = (EditText) dialog.findViewById(R.id.right_n_cyl);
//        right_d_axis = (EditText) dialog.findViewById(R.id.right_d_axis);
//        right_d_sph = (EditText) dialog.findViewById(R.id.right_d_sph);
//        right_cl_cl = (EditText) dialog.findViewById(R.id.right_cl_cl);
//        right_cl_axis = (EditText) dialog.findViewById(R.id.right_cl_axis);
//        right_cl_va = (EditText) dialog.findViewById(R.id.right_cl_va);
//        left_d_sph = (EditText) dialog.findViewById(R.id.left_d_sph);
//        left_d_cyl = (EditText) dialog.findViewById(R.id.left_d_cyl);
//        edtCode = (EditText) dialog.findViewById(R.id.edtCode);
//        edtPrice = (EditText) dialog.findViewById(R.id.edtPrice);
//        editDiscount = (EditText) dialog.findViewById(R.id.editDiscount);
//        edtProductName = (EditText) dialog.findViewById(R.id.edtProductName);
//        edtQuantity = (EditText) dialog.findViewById(R.id.edtQuantity);
//        btnAddItem = (Button) dialog.findViewById(R.id.btnAddItem);
////                text.setText("Android custom dialog example!");
//        btnScan = (Button) dialog.findViewById(R.id.btnScan);
//        setSpinner();
//        setSpinnerForLensTypeAndLensfor();
//
//        spinnerStockItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?>arg0, View view, int position, long id) {
//
//                String selected_val=spinnerStockItems.getSelectedItem().toString();
//
//                String itemid=listItemCode.get(position).toString();
//
//                getProdutcDetails(itemid);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> arg0) {
//                // TODO Auto-generated method stub
//
//            }
//        });
//
//
//
//        lensfor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String lensfor_string=lensfor.getSelectedItem().toString();
//                edtProductName.setText(lensfor_string);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                edtProductName.setText("");
//
//            }
//        });
//
//        lenstype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String lenstype_string=lenstype.getSelectedItem().toString();
//                edtCode.setText(lenstype_string);
//
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//
////                // if button is clicked, close the custom dialog
//        btnScan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                        edtProductName.setText("");
////                        edtCode.setText("");
////                        edtPrice.setText("");
////
////                        left_cl_axis.setText("");
////                        left_cl_cl.setText("");
////                        left_cl_sph.setText("");
////                        left_add_sph.setText("");
////                        left_n_va.setText("");
////                        left_n_axis.setText("");
////                        left_n_cyl.setText("");
////                        left_n_sph.setText("");
////                        left_d_va.setText("");
////                        left_d_axis.setText("");
////                        left_d_cyl.setText("");
////                        left_d_sph.setText("");
////                        right_cl_va.setText("");
////                        right_cl_axis.setText("");
////                        right_cl_cl.setText("");
////                        left_cl_va.setText("");
////                        right_cl_sph.setText("");
////                        right_add_sph.setText("");
////                        right_n_va.setText("");
////                        right_n_axis.setText("");
////                        right_d_cyl.setText("");
////                        right_n_sph.setText("");
////                        right_d_va.setText("");
////                        right_n_cyl.setText("");
////                        right_d_axis.setText("");
//
//
//                Intent intent = new Intent(getApplication(), ScannerActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        btnAddItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                String left_cl_axis_string = left_cl_axis.getText().toString();
//                String left_cl_cl_string = left_cl_cl.getText().toString();
//                String left_cl_sph_string = left_cl_sph.getText().toString();
//                String left_cl_va_string = left_cl_va.getText().toString();
//                String left_add_sph_string = left_add_sph.getText().toString();
//                String left_n_va_string = left_n_va.getText().toString();
//                String left_n_axis_string = left_n_axis.getText().toString();
//                String left_n_cyl_string = left_n_cyl.getText().toString();
//                String left_n_sph_string = left_n_sph.getText().toString();
//                String left_d_va_string = left_d_va.getText().toString();
//                String left_d_axis_string = left_d_axis.getText().toString();
//
//
//                String right_cl_sph_string = right_cl_sph.getText().toString();
//                String right_cl_cl_string = right_cl_cl.getText().toString();
//                String right_cl_axis_string = right_cl_axis.getText().toString();
//                String right_cl_va_string = right_cl_va.getText().toString();
//                String left_d_sph_string = left_d_sph.getText().toString();
//                String left_d_cyl_string = left_d_cyl.getText().toString();
//                String right_add_sph_string = right_add_sph.getText().toString();
//                String right_n_va_string = right_n_va.getText().toString();
//                String right_n_axis_string = right_n_axis.getText().toString();
//                String right_d_cyl_string = right_d_cyl.getText().toString();
//                String right_n_sph_string = right_n_sph.getText().toString();
//
//                String right_d_va_string = right_d_va.getText().toString();
//                String right_n_cyl_string = right_n_cyl.getText().toString();
//                String right_d_axis_string = right_d_axis.getText().toString();
//                String right_d_sph_string = right_d_sph.getText().toString();
//                String edtCode_string = edtCode.getText().toString();
//                String edtPrice_string = edtPrice.getText().toString();
//                String editDiscount_string = editDiscount.getText().toString();
//                String edtProductName_string = edtProductName.getText().toString();
//                String edtQuantity_string = edtQuantity.getText().toString();
//
//                if (validate(edtProductName_string, edtQuantity_string, editDiscount_string, edtPrice_string, edtCode_string)) {
//                    Long price = Long.valueOf(edtPrice_string);
//                    Long qty = Long.valueOf(edtQuantity_string);
//
//                    Long amount = price * qty;
//                    item_code_list.add("0");
//                    AddItem(edtProductName_string, edtQuantity_string, editDiscount_string, edtPrice_string, edtCode_string,
//
//                            right_d_sph_string, right_d_axis_string, right_n_cyl_string
//                            , right_d_va_string, right_n_sph_string, right_d_cyl_string,
//                            right_n_axis_string, right_n_va_string, right_add_sph_string,
//                            right_cl_sph_string, left_d_axis_string, left_d_va_string,
//                            left_n_sph_string, left_n_cyl_string, left_n_axis_string,
//                            left_n_va_string, left_add_sph_string, left_cl_sph_string,
//                            left_cl_cl_string, left_cl_axis_string, amount, left_cl_va_string,
//                            right_cl_cl_string, right_cl_axis_string, right_cl_va_string,
//                            left_d_sph_string, left_d_cyl_string);
//
//
//                    if (left_d_cyl_string.equals("")) {
//                        left_d_cyl_string = "0";
//                    }
//                    if (left_d_sph_string.equals("")) {
//                        left_d_sph_string = "0";
//                    }
//                    if (right_cl_va_string.equals("")) {
//                        right_cl_va_string = "0";
//                    }
//                    if (right_cl_axis_string.equals("")) {
//                        right_cl_axis_string = "0";
//                    }
//                    if (right_cl_cl_string.equals("")) {
//                        right_cl_cl_string = "0";
//                    }
//                    if (left_cl_va_string.equals("")) {
//                        left_cl_va_string = "0";
//                    }
//                    if (left_cl_axis_string.equals("")) {
//                        left_cl_axis_string = "0";
//                    }
//                    if (left_cl_cl_string.equals("")) {
//                        left_cl_cl_string = "0";
//                    }
//                    if (left_cl_sph_string.equals("")) {
//                        left_cl_sph_string = "0";
//                    }
//                    if (left_add_sph_string.equals("")) {
//                        left_add_sph_string = "0";
//                    }
//                    if (left_n_va_string.equals("")) {
//                        left_n_va_string = "0";
//                    }
//                    if (left_n_axis_string.equals("")) {
//                        left_n_axis_string = "0";
//                    }
//                    if (left_n_cyl_string.equals("")) {
//                        left_n_cyl_string = "0";
//                    }
//
//
//                    if (left_n_sph_string.equals("")) {
//                        left_n_sph_string = "0";
//                    }
//
//                    if (left_d_va_string.equals("")) {
//                        left_d_va_string = "0";
//                    }
//                    if (left_d_axis_string.equals("")) {
//                        left_d_axis_string = "0";
//                    }
//                    if (right_cl_sph_string.equals("")) {
//                        right_cl_sph_string = "0";
//                    }
//                    if (right_add_sph_string.equals("")) {
//                        right_add_sph_string = "0";
//                    }
//                    if (right_n_va_string.equals("")) {
//                        right_n_va_string = "0";
//                    }
//
//                    if (right_n_axis_string.equals("")) {
//                        right_n_axis_string = "0";
//                    }
//
//
//                    if (right_n_sph_string.equals("")) {
//                        right_n_sph_string = "0";
//                    }
//                    if (right_d_cyl_string.equals("")) {
//                        right_d_cyl_string = "0";
//                    }
//
//
//                    if (right_d_va_string.equals("")) {
//                        right_d_va_string = "0";
//                    }
//
//                    if (right_n_cyl_string.equals("")) {
//                        right_n_cyl_string = "0";
//                    }
//
//
//                    if (right_d_sph_string.equals("")) {
//                        right_d_sph_string = "0";
//                    }
//                    if (right_d_axis_string.equals("")) {
//                        right_d_axis_string = "0";
//                    }
//
//
//                    SalesOrderDetailsList salesOrderDetailsList = new SalesOrderDetailsList();
//                    salesOrderDetailsList.setSalesId(Long.valueOf(0));
//                    salesOrderDetailsList.setSalesOrderId(Long.valueOf(0));
//
//                    salesOrderDetailsList.setItemId(Long.valueOf(itmid_varialble));
//
//
//                    salesOrderDetailsList.setQty(Long.valueOf(edtQuantity_string));
//                    salesOrderDetailsList.setRate(Long.valueOf(edtPrice_string));
//                    salesOrderDetailsList.setAmnt(Long.valueOf(amount));
//
//                    salesOrderDetailsList.setCGST(Long.valueOf(0));
//                    salesOrderDetailsList.setSGST(Long.valueOf(0));
//                    salesOrderDetailsList.setIGST(Long.valueOf(0));
//                    salesOrderDetailsList.setCESS(Long.valueOf(0));
//
//
//                    salesOrderDetailsList.setDiscount(Long.valueOf(editDiscount_string));
//
//                    salesOrderDetailsList.setTotalTaxableValue(Long.valueOf("0"));
//                    salesOrderDetailsList.setTotalValue(Long.valueOf(1000));
//
//
//                    salesOrderDetailsList.setRightDSph(Long.valueOf(right_d_sph_string));
//
//
//                    salesOrderDetailsList.setRightDCyl(Long.valueOf(right_d_cyl_string));
//
//                    salesOrderDetailsList.setRightDAxis(Long.valueOf(right_d_axis_string));
//
//
//                    salesOrderDetailsList.setRightDVa(Long.valueOf(right_d_va_string));
//
//
//                    salesOrderDetailsList.setRightNSph(Long.valueOf(right_n_sph_string));
//
//
//                    salesOrderDetailsList.setRightNCyl(Long.valueOf(right_n_cyl_string));
//                    salesOrderDetailsList.setRightNAxis(Long.valueOf(right_n_axis_string));
//
//
//                    salesOrderDetailsList.setRightNVa(Long.valueOf(right_n_va_string));
//                    salesOrderDetailsList.setRightAddSph(Long.valueOf(right_add_sph_string));
//
//
//                    salesOrderDetailsList.setRightClSph(Long.valueOf(right_cl_sph_string));
//                    salesOrderDetailsList.setRightClCl(Long.valueOf(right_cl_cl_string));
//                    salesOrderDetailsList.setRightClAxis(Long.valueOf(right_cl_axis_string));
//                    salesOrderDetailsList.setRightClVa(Long.valueOf(right_cl_va_string));
//                    salesOrderDetailsList.setLeftDSph(Long.valueOf(left_d_sph_string));
//
//
//                    salesOrderDetailsList.setLeftDCyl(Long.valueOf(left_d_cyl_string));
//
//
//                    salesOrderDetailsList.setLeftDAxis(Long.valueOf(left_d_axis_string));
//                    salesOrderDetailsList.setLeftDVa(Long.valueOf(left_d_va_string));
//                    salesOrderDetailsList.setLeftNSph(Long.valueOf(left_n_sph_string));
//                    salesOrderDetailsList.setLeftNCyl(Long.valueOf(left_n_cyl_string));
//
//
//                    salesOrderDetailsList.setLeftNAxis(Long.valueOf(left_n_axis_string));
//                    salesOrderDetailsList.setLeftNVa(Long.valueOf(left_n_va_string));
//
//
//                    salesOrderDetailsList.setLeftAddSph(Long.valueOf(left_add_sph_string));
//                    salesOrderDetailsList.setLeftClSph(Long.valueOf(left_cl_sph_string));
//                    salesOrderDetailsList.setLeftClCl(Long.valueOf(left_cl_cl_string));
//
//
//                    salesOrderDetailsList.setLeftClAxis(Long.valueOf(left_cl_axis_string));
//                    salesOrderDetailsList.setLeftClVa(Long.valueOf(left_cl_va_string));
//
//
////                    listSalesOrderDetailsList_new.add(salesOrderDetailsList);
//
//                    dialog.dismiss();
//
//
//                }
//
//
//            }
//        });
//
//        dialog.show();
//        Window window = dialog.getWindow();
//        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//
//
//
//    }
    private void showDialogOforederLens() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.lensonly);
        dialog.setTitle("NEETHI");
        edtCode = (EditText) dialog.findViewById(R.id.edtCode);
        edtPrice = (EditText) dialog.findViewById(R.id.edtPrice);
        editDiscount = (EditText) dialog.findViewById(R.id.editDiscount);
        edtProductName = (EditText) dialog.findViewById(R.id.edtProductName);
        edtQuantity = (EditText) dialog.findViewById(R.id.edtQuantity);
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
        btnAddItem = (Button) dialog.findViewById(R.id.btnAddItem);
        lensfor = (Spinner) dialog.findViewById(R.id.lensfor);
        lenstype = (Spinner) dialog.findViewById(R.id.lenstype);
//        total_lens = (EditText) dialog.findViewById(R.id.total_lens);
        setSpinnerForLensTypeAndLensfor();
        left_cl_axis.setOnClickListener(this);

        editDiscount.setText("32");
        String lensfor_string = lensfor.getSelectedItem().toString();
        String lenstype_string = lenstype.getSelectedItem().toString();
        lensfor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String lensfor_string = lensfor.getSelectedItem().toString();
                edtProductName.setText(lensfor_string);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                edtProductName.setText("");

            }
        });

        lenstype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String lenstype_string = lenstype.getSelectedItem().toString();
                edtCode.setText(lenstype_string);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
                String edtProductName_string = edtProductName.getText().toString();
                String edtCode_string = edtCode.getText().toString();
                String edtPrice_string = edtPrice.getText().toString();
                String editDiscount_string = editDiscount.getText().toString();
                String edtQuantity_string = edtQuantity.getText().toString();
                if (validate(edtProductName_string, edtCode_string, edtPrice_string, editDiscount_string, edtQuantity_string, left_cl_axis_string
                        , left_cl_cl_string,
                        left_cl_sph_string,
                        left_cl_va_string, left_add_sph_string, left_n_va_string, left_n_axis_string, left_n_cyl_string, left_n_sph_string, left_d_va_string, left_d_axis_string, right_cl_sph_string, right_cl_cl_string, right_cl_axis_string, right_cl_va_string, left_d_sph_string, left_d_cyl_string, right_add_sph_string, right_n_va_string, right_n_axis_string, right_d_cyl_string, right_n_sph_string, right_d_va_string, right_n_cyl_string, right_d_axis_string, right_d_sph_string)) {
                    Long price = Long.valueOf(edtPrice_string);
                    Long qty = Long.valueOf(edtQuantity_string);

                    Long amount = price * qty;

                    double qunatity = Double.parseDouble(edtQuantity_string);
                    double discountedamount = price * 32 / 100;
                    double actualprice = price - discountedamount;
                    double totaldiscount = discountedamount * qunatity;



                    AddItem(edtProductName_string, edtQuantity_string, editDiscount_string, edtPrice_string, edtCode_string, right_d_sph_string, right_d_axis_string, right_n_cyl_string
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

                    salesOrderDetailsList.setItemId(Long.valueOf(-1));


                    salesOrderDetailsList.setQty(Long.valueOf(edtQuantity_string));
                    salesOrderDetailsList.setRate(Long.valueOf(edtPrice_string));
//                    salesOrderDetailsList.setAmnt(Long.valueOf(amount));

                    salesOrderDetailsList.setCGST(Long.valueOf(0));
                    salesOrderDetailsList.setSGST(Long.valueOf(0));
                    salesOrderDetailsList.setIGST(Long.valueOf(0));
                    salesOrderDetailsList.setCESS(Long.valueOf(0));


                    salesOrderDetailsList.setDiscount(Long.valueOf(editDiscount_string));

                    salesOrderDetailsList.setTotalTaxableValue(Long.valueOf("0"));
                    salesOrderDetailsList.setTotalValue(Long.valueOf(0));


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
                    salesOrderDetailsList.setLensFor(edtProductName_string);
                    salesOrderDetailsList.setLensType(edtCode_string);


                    listSalesOrderDetailsList_new.add(salesOrderDetailsList);

                    dialog.dismiss();

//
                }


            }
        });


        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


    }
    private void showDialogOforederLens(String s) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.lensandframeonly);
        dialog.setTitle("NEETHI");
        edtCode = (EditText) dialog.findViewById(R.id.edtCode);
        edtPrice = (EditText) dialog.findViewById(R.id.edtPrice);
        editDiscount = (EditText) dialog.findViewById(R.id.editDiscount);
        edtProductName = (EditText) dialog.findViewById(R.id.edtProductName);
        edtQuantity = (EditText) dialog.findViewById(R.id.edtQuantity);

        right_sph = (EditText) dialog.findViewById(R.id.right_sph);
        right_cyl = (EditText) dialog.findViewById(R.id.right_cyl);
        right_axis = (EditText) dialog.findViewById(R.id.right_axis);
        right_va = (EditText) dialog.findViewById(R.id.right_va);
        left_sph = (EditText) dialog.findViewById(R.id.left_sph);
        left__cyl = (EditText) dialog.findViewById(R.id.left__cyl);
        left_axis = (EditText) dialog.findViewById(R.id.left_axis);
        left_va = (EditText) dialog.findViewById(R.id.left_va);



        btnAddItem = (Button) dialog.findViewById(R.id.btnAddItem);

//        total_lens = (EditText) dialog.findViewById(R.id.total_lens);

//        left_cl_axis.setOnClickListener(this);

        editDiscount.setText("0");
//        String lensfor_string = lensfor.getSelectedItem().toString();
//        String lenstype_string = lenstype.getSelectedItem().toString();

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String right_sph_string = right_sph.getText().toString();
                String right_cyl_string = right_cyl.getText().toString();
                String right_axis_string = right_axis.getText().toString();
                String right_va_string = right_va.getText().toString();
                String left_sph_string = left_sph.getText().toString();
                String left__cyl_string = left__cyl.getText().toString();
                String left_axis_string = left_axis.getText().toString();
                String left_va_string = left_va.getText().toString();




                String edtCode_string = edtCode.getText().toString();
                String edtProductName_string = edtCode_string;
                String edtPrice_string = edtPrice.getText().toString();
                String editDiscount_string = editDiscount.getText().toString();
                String edtQuantity_string = edtQuantity.getText().toString();
                if (validate(edtProductName_string, edtCode_string, edtPrice_string, editDiscount_string, edtQuantity_string, right_sph_string
                        , right_cyl_string,
                        right_axis_string,
                        right_va_string, left_sph_string, left__cyl_string, left_axis_string,left_va_string)) {
                    Long price = Long.valueOf(edtPrice_string);
                    Long qty = Long.valueOf(edtQuantity_string);
                    Long dsk = Long.valueOf(editDiscount_string);

                    Long amount = price * qty;

                    double qunatity = Double.parseDouble(edtQuantity_string);
                    double discountedamount = price * dsk / 100;
                    double actualprice = price - discountedamount;
                    double totaldiscount = discountedamount * qunatity;




                    AddItem(edtProductName_string, edtQuantity_string, editDiscount_string, edtPrice_string, edtCode_string,
                            right_sph_string, right_axis_string, "0"
                            , right_va_string, "0", right_cyl_string,
                            "", "0", "0",
                            "0", "0", "0",
                            left_sph_string, left__cyl_string, left_axis_string,
                            left_va_string, "0", "0",
                            "0", "0", amount, "0",
                            "0", "0", "0",
                            "0", "0");


                    if (right_sph_string.equals("")) {
                        right_sph_string = "0";
                    }
                    if (right_axis_string.equals("")) {
                        right_axis_string = "0";
                    }
                    if (right_cyl_string.equals("")) {
                        right_cyl_string = "0";
                    }
                    if (right_va_string.equals("")) {
                        right_va_string = "0";
                    }
                    if (left_sph_string.equals("")) {
                        left_sph_string = "0";
                    }
                    if (left__cyl_string.equals("")) {
                        left__cyl_string = "0";
                    }
                    if (left_axis_string.equals("")) {
                        left_axis_string = "0";
                    }
                    if (left_va_string.equals("")) {
                        left_va_string = "0";
                    }
//                    if (left_cl_sph_string.equals("")) {
//                        left_cl_sph_string = "0";
//                    }
//                    if (left_add_sph_string.equals("")) {
//                        left_add_sph_string = "0";
//                    }
//                    if (left_n_va_string.equals("")) {
//                        left_n_va_string = "0";
//                    }
//                    if (left_n_axis_string.equals("")) {
//                        left_n_axis_string = "0";
//                    }
//                    if (left_n_cyl_string.equals("")) {
//                        left_n_cyl_string = "0";
//                    }
//
//
//                    if (left_n_sph_string.equals("")) {
//                        left_n_sph_string = "0";
//                    }
//
//                    if (left_d_va_string.equals("")) {
//                        left_d_va_string = "0";
//                    }
//                    if (left_d_axis_string.equals("")) {
//                        left_d_axis_string = "0";
//                    }
//                    if (right_cl_sph_string.equals("")) {
//                        right_cl_sph_string = "0";
//                    }
//                    if (right_add_sph_string.equals("")) {
//                        right_add_sph_string = "0";
//                    }
//                    if (right_n_va_string.equals("")) {
//                        right_n_va_string = "0";
//                    }
//
//                    if (right_n_axis_string.equals("")) {
//                        right_n_axis_string = "0";
//                    }
//
//
//                    if (right_n_sph_string.equals("")) {
//                        right_n_sph_string = "0";
//                    }
//                    if (right_d_cyl_string.equals("")) {
//                        right_d_cyl_string = "0";
//                    }
//
//
//                    if (right_d_va_string.equals("")) {
//                        right_d_va_string = "0";
//                    }
//
//                    if (right_n_cyl_string.equals("")) {
//                        right_n_cyl_string = "0";
//                    }
//
//
//                    if (right_d_sph_string.equals("")) {
//                        right_d_sph_string = "0";
//                    }
//                    if (right_d_axis_string.equals("")) {
//                        right_d_axis_string = "0";
//                    }


                    SalesOrderDetailsList salesOrderDetailsList = new SalesOrderDetailsList();
                    salesOrderDetailsList.setSalesId(Long.valueOf(0));
                    salesOrderDetailsList.setSalesOrderId(Long.valueOf(0));

                    salesOrderDetailsList.setItemId(Long.valueOf(-1));


                    salesOrderDetailsList.setQty(Long.valueOf(edtQuantity_string));
                    salesOrderDetailsList.setRate(Long.valueOf(edtPrice_string));
                    salesOrderDetailsList.setTotalValue(Long.valueOf(amount));

                    salesOrderDetailsList.setCGST(Long.valueOf(0));
                    salesOrderDetailsList.setSGST(Long.valueOf(0));
                    salesOrderDetailsList.setIGST(Long.valueOf(0));
                    salesOrderDetailsList.setCESS(Long.valueOf(0));


                    salesOrderDetailsList.setDiscount(Long.valueOf(editDiscount_string));

                    salesOrderDetailsList.setTotalTaxableValue(Long.valueOf("0"));
//                    salesOrderDetailsList.setTotalValue(Long.valueOf(0));


                    salesOrderDetailsList.setRightDSph(Long.valueOf(right_sph_string));

                    salesOrderDetailsList.setRightDCyl(Long.valueOf(right_cyl_string));

                    salesOrderDetailsList.setRightDAxis(Long.valueOf(right_axis_string));

                    salesOrderDetailsList.setRightDVa(Long.valueOf(right_va_string));
                    salesOrderDetailsList.setLeftDSph(Long.valueOf(left_sph_string));


                    salesOrderDetailsList.setLeftDCyl(Long.valueOf(left__cyl_string));


                    salesOrderDetailsList.setLeftDAxis(Long.valueOf(left_axis_string));
                    salesOrderDetailsList.setLeftDVa(Long.valueOf(left_va_string));



//
//
                    salesOrderDetailsList.setRightNSph(Long.valueOf("0"));


                    salesOrderDetailsList.setRightNCyl(Long.valueOf("0"));
                    salesOrderDetailsList.setRightNAxis(Long.valueOf("0"));


                    salesOrderDetailsList.setRightNVa(Long.valueOf("0"));
                    salesOrderDetailsList.setRightAddSph(Long.valueOf("0"));


                    salesOrderDetailsList.setRightClSph(Long.valueOf("0"));
                    salesOrderDetailsList.setRightClCl(Long.valueOf("0"));
                    salesOrderDetailsList.setRightClAxis(Long.valueOf("0"));
                    salesOrderDetailsList.setRightClVa(Long.valueOf("0"));
                    salesOrderDetailsList.setLeftDSph(Long.valueOf("0"));


                    salesOrderDetailsList.setLeftDCyl(Long.valueOf("0"));


                    salesOrderDetailsList.setLeftDAxis(Long.valueOf("0"));
                    salesOrderDetailsList.setLeftDVa(Long.valueOf("0"));
                    salesOrderDetailsList.setLeftNSph(Long.valueOf("0"));
                    salesOrderDetailsList.setLeftNCyl(Long.valueOf("0"));


                    salesOrderDetailsList.setLeftNAxis(Long.valueOf("0"));
                    salesOrderDetailsList.setLeftNVa(Long.valueOf("0"));


                    salesOrderDetailsList.setLeftAddSph(Long.valueOf("0"));
                    salesOrderDetailsList.setLeftClSph(Long.valueOf("0"));
                    salesOrderDetailsList.setLeftClCl(Long.valueOf("0"));


                    salesOrderDetailsList.setLeftClAxis(Long.valueOf("0"));
                    salesOrderDetailsList.setLeftClVa(Long.valueOf("0"));
//                    salesOrderDetailsList.setDiscount((long) discountedamount);



                    salesOrderDetailsList.setLensFor(edtProductName_string);
                    salesOrderDetailsList.setLensType(edtCode_string);


                    listSalesOrderDetailsList_new.add(salesOrderDetailsList);

                    dialog.dismiss();

                }


            }
        });


        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


    }

    private boolean validate(String edtProductName_string, String edtCode_string, String edtPrice_string, String editDiscount_string, String edtQuantity_string, String right_sph_string, String right_cyl_string, String right_axis_string, String right_va_string, String left_sph_string, String left__cyl_string, String left_axis_string, String left_va_string) {
   return true;
    }

    private void setSpinnerForLensTypeAndLensfor() {

        ArrayList<String> listLensfor = new ArrayList<>();

        listLensfor.add("Lens For");
        listLensfor.add("Distance");
        listLensfor.add("Near");
        listLensfor.add("Bifocal");
        listLensfor.add("Progressive");


        ArrayList<String> listlenstype = new ArrayList<>();
        listlenstype.add("Lens Type");
        listlenstype.add("Mineral Lens");
        listlenstype.add("Plastic Lens");
        listlenstype.add("Contact Lens");
        listlenstype.add("Ploycarbonate Lens");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listLensfor);
//set the spinners adapter to the previously created one.
//        lensfor.setAdapter(adapter);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listlenstype);
//set the spinners adapter to the previously created one.
//        lenstype.setAdapter(adapter1);


    }

    private boolean validate(String left_cl_axis_string,
                             String left_cl_cl_string,
                             String left_cl_sph_string,
                             String left_cl_va_string,
                             String left_add_sph_string,
                             String left_n_va_string,
                             String left_n_axis_string,
                             String left_n_cyl_string,
                             String left_n_sph_string,
                             String left_d_va_string,
                             String left_d_axis_string,
                             String right_cl_sph_string,
                             String right_cl_cl_string,
                             String right_cl_axis_string,
                             String right_cl_va_string,
                             String left_d_sph_string,
                             String left_d_cyl_string,
                             String right_add_sph_string,
                             String right_n_va_string,
                             String right_n_axis_string,
                             String right_d_cyl_string,
                             String right_n_sph_string,
                             String right_d_va_string,
                             String right_n_cyl_string,
                             String right_d_axis_string,
                             String right_d_sph_string) {
        boolean flag = true;


        if (left_cl_axis_string.equals("") &&
                left_cl_axis_string.equals("") &&
                left_cl_cl_string.equals("") &&
                left_cl_sph_string.equals("") &&
                left_cl_va_string.equals("") &&
                left_add_sph_string.equals("") &&
                left_n_va_string.equals("") &&
                left_n_axis_string.equals("") &&
                left_n_cyl_string.equals("") &&
                left_n_sph_string.equals("") &&
                left_d_va_string.equals("") &&
                left_d_axis_string.equals("") &&
                right_cl_sph_string.equals("") &&
                right_cl_cl_string.equals("") &&
                right_cl_axis_string.equals("") &&
                right_cl_va_string.equals("") &&
                left_d_sph_string.equals("") &&
                left_d_cyl_string.equals("") &&
                right_add_sph_string.equals("") &&
                right_n_va_string.equals("") &&
                right_n_axis_string.equals("") &&
                right_d_cyl_string.equals("") &&
                right_n_sph_string.equals("") &&
                right_d_va_string.equals("") &&
                right_n_cyl_string.equals("") &&
                right_d_axis_string.equals("") &&
                right_d_sph_string.equals("")
                ) {
            flag = false;

            Toast.makeText(this, "Select Anything", Toast.LENGTH_SHORT).show();
        }

        return flag;
    }

    private boolean validate(String lensfor_string, String lenstype_string, String total_lensString, String editDiscount_string, String edtQuantity_string, String left_cl_axis_string,
                             String left_cl_cl_string,
                             String left_cl_sph_string,
                             String left_cl_va_string,
                             String left_add_sph_string,
                             String left_n_va_string,
                             String left_n_axis_string,
                             String left_n_cyl_string,
                             String left_n_sph_string,
                             String left_d_va_string,
                             String left_d_axis_string,
                             String right_cl_sph_string,
                             String right_cl_cl_string,
                             String right_cl_axis_string,
                             String right_cl_va_string,
                             String left_d_sph_string,
                             String left_d_cyl_string,
                             String right_add_sph_string,
                             String right_n_va_string,
                             String right_n_axis_string,
                             String right_d_cyl_string,
                             String right_n_sph_string,
                             String right_d_va_string,
                             String right_n_cyl_string,
                             String right_d_axis_string,
                             String right_d_sph_string) {
        boolean flag = true;


        if (left_cl_axis_string.equals("") &&
                left_cl_axis_string.equals("") &&
                left_cl_cl_string.equals("") &&
                left_cl_sph_string.equals("") &&
                left_cl_va_string.equals("") &&
                left_add_sph_string.equals("") &&
                left_n_va_string.equals("") &&
                left_n_axis_string.equals("") &&
                left_n_cyl_string.equals("") &&
                left_n_sph_string.equals("") &&
                left_d_va_string.equals("") &&
                left_d_axis_string.equals("") &&
                right_cl_sph_string.equals("") &&
                right_cl_cl_string.equals("") &&
                right_cl_axis_string.equals("") &&
                right_cl_va_string.equals("") &&
                left_d_sph_string.equals("") &&
                left_d_cyl_string.equals("") &&
                right_add_sph_string.equals("") &&
                right_n_va_string.equals("") &&
                right_n_axis_string.equals("") &&
                right_d_cyl_string.equals("") &&
                right_n_sph_string.equals("") &&
                right_d_va_string.equals("") &&
                right_n_cyl_string.equals("") &&
                right_d_axis_string.equals("") &&
                right_d_sph_string.equals("")
                ) {

//           if(lensfor_string.equals(""))
//           {
//               flag=false;
//               Toast.makeText(this, "Select Lensfor", Toast.LENGTH_SHORT).show();
//           }
//            if(lenstype_string.equals(""))
//            {
//                flag=false;
//                Toast.makeText(this, "Select Lens type", Toast.LENGTH_SHORT).show();
//            }
//            f
            flag = false;


//            setSnakMessage("Select lens power");
            Toast.makeText(this, "Select lens power", Toast.LENGTH_SHORT).show();
        }

        if (lensfor_string.equals("Lens For")) {
            flag = false;
//                setSnakMessage("Select Lens for");
            Toast.makeText(this, "Select Lensfor", Toast.LENGTH_SHORT).show();
        }
        if (lenstype_string.equals("Lens Type")) {
            flag = false;
//                setSnakMessage("Select Lens Type");
            Toast.makeText(this, "Select Lens type", Toast.LENGTH_SHORT).show();
        }
        if (total_lensString.equals("")) {
            flag = false;
//                setSnakMessage("Enter Price");
            Toast.makeText(this, "Enter Price", Toast.LENGTH_SHORT).show();
        }
        if (editDiscount_string.equals("")) {
            flag = false;
//                setSnakMessage("Enter Price");
            Toast.makeText(this, "Enter discount", Toast.LENGTH_SHORT).show();
        }
        if (edtQuantity_string.equals("")) {
            flag = false;
//                setSnakMessage("Enter Price");
            Toast.makeText(this, "Enter quantity", Toast.LENGTH_SHORT).show();
        }


        return flag;
    }


    public void setSnakMessage(String message) {
//        SnackbarManager.show(
//                Snackbar.with(getApplicationContext()) // context
//                        .text("Message") // text to be displayed
//                        .textColor(Color.WHITE) // change the text color
//                        .color(Color.RED) // change the background color
//                         // action button's ActionClickListener
//                , this);
        Snackbar.make(getCurrentFocus(), message, Snackbar.LENGTH_LONG)
                .setAction("TryAgain", null).show();
    }

    private void showDialogOforederFrame() {

        setSpinner();
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.frameonly);
        dialog.setTitle("NEETHI");
        spinnerStockItems = dialog.findViewById(R.id.spinner_item);

        edtCode = (EditText) dialog.findViewById(R.id.edtCode);
        edtPrice = (EditText) dialog.findViewById(R.id.edtPrice);
        editDiscount = (EditText) dialog.findViewById(R.id.editDiscount);
        edtProductName = (EditText) dialog.findViewById(R.id.edtProductName);
        edtQuantity = (EditText) dialog.findViewById(R.id.edtQuantity);
        btnAddItem = (Button) dialog.findViewById(R.id.btnAddItem);


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
//                text.setText("Android custom dialog example!");
        btnScan = (Button) dialog.findViewById(R.id.btnScan);


        spinnerStockItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int position, long id) {

                String selected_val = spinnerStockItems.getSelectedItem().toString();

                String itemid = listItemCode.get(position).toString();

                getProdutcDetails(itemid);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), ScannerActivity.class);
                startActivity(intent);
            }
        });

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String edtCode_string = edtCode.getText().toString();
                String edtPrice_string = edtPrice.getText().toString();
                String editDiscount_string = editDiscount.getText().toString();
                String edtProductName_string = edtProductName.getText().toString();
                String edtQuantity_string = edtQuantity.getText().toString();


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
                    salesOrderDetailsList.setTotalValue(Long.valueOf(0));


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
                    salesOrderDetailsList.setLensFor("");
                    salesOrderDetailsList.setLensType("");


                    listSalesOrderDetailsList_new.add(salesOrderDetailsList);

                    dialog.dismiss();


                }


            }
        });

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (Validate()) {
            String name = edtName.getText().toString().trim();
            String phone = edtPhone.getText().toString().trim();

            String mail = edtEmail.getText().toString().trim();
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            bundle.putString("phone", name);
            bundle.putString("mail", name);

            if (bundle.isEmpty()) {
                Toast.makeText(this, "Bundle is empty", Toast.LENGTH_SHORT).show();
            } else {

                if (id == R.id.both) {

//                    Intent intent = new Intent(this, AddFrameActivity.class);
//                    intent.putExtra("CUSTOMER_DETAILS",bundle);
//                    startActivity(intent);
//                    finish();

//                    showDialogOforederForLensandFrame();


                }
                if (id == R.id.lens) {


//                    showDialogOforederLens();
                    showDialogOforederLens("new");
//                    Intent intent = new Intent(this, AddFrameActivity.class);
//                    intent.putExtra("CUSTOMER_DETAILS",bundle);
//                    startActivity(intent);
//                    finish();


                }
                if (id == R.id.frame) {
                    progressBar.setMessage("Loading..");
                    progressBar.show();
                    showDialogOforederFrame();
//                    Intent intent = new Intent(this, AddFrameActivity.class);
//                    intent.putExtra("CUSTOMER_DETAILS",bundle);
//                    startActivity(intent);
//                    finish();


                }
            }


        }

        if (id == R.id.left_cl_axis) {
            Log.e("onClick: ", "" + id);
        }


    }

    private boolean Validate() {
        String name = edtName.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();

        String mail = edtEmail.getText().toString().trim();


        if (name.length() == 0 || phone.length() == 0) {
            if (name.length() == 0) {
                edtName.setError("Field can't be blank");
                edtName.setFocusable(true);
            }
            if (phone.length() == 0) {
                edtPhone.setError("Field can't be blank");
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

    private boolean Validated() {
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

    private void AddItem(String edtProductName_string, String edtQuantity_string, String editDiscount_string, String edtPrice_string, String edtCode_string, String right_d_sph_string, String right_d_axis_string, String right_n_cyl_string, String right_d_va_string, String right_n_sph_string, String right_d_cyl_string, String right_n_axis_string, String right_n_va_string, String right_add_sph_string, String right_cl_sph_string, String left_d_axis_string, String left_d_va_string, String left_n_sph_string, String left_n_cyl_string, String left_n_axis_string, String left_n_va_string, String left_add_sph_string, String left_cl_sph_string, String left_cl_cl_string, String left_cl_axis_string, Long amount, String left_cl_va_string, String right_cl_cl_string, String right_cl_axis_string, String right_cl_va_string, String left_d_sph_string, String left_d_cyl_string) {


        double price = Double.parseDouble(edtPrice_string);
        double qunatity = Double.parseDouble(edtQuantity_string);
        double dsk = Double.parseDouble(editDiscount_string);
        double discountedamount = price * dsk / 100;
        double actualprice = price - discountedamount;
        double totaldiscount = discountedamount * qunatity;

        double totalprice = actualprice * qunatity;
        subtotalvaluearray.add(price * qunatity);

        grosslvaluearray.add(totalprice);

        totaldiscountamount.add(totaldiscount);

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
        categoruAdapter = new ItemListAdapter(catg, this);


        categoruAdapter.SetListnerMon(this);
        recyclerView.setAdapter(categoruAdapter);
        visible_button_andrate.setVisibility(View.VISIBLE);
        categoruAdapter.notifyDataSetChanged();

        setTotal();


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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        grosslvaluearray = new ArrayList<>();
        subtotalvaluearray = new ArrayList<>();
        totaldiscountamount = new ArrayList<>();

        catg = new ArrayList<>();
        full_cate = new ArrayList<>();


        salesOrderDetailsListForRecyclerViews = new ArrayList<>();
        listSalesOrderDetailsList_new = new ArrayList<>();
        discuntamount_total.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                if(!s.equals(null)) {


                    String discount=discuntamount_total.getText().toString();
                    String total=grandtotal_value.getText().toString();

                    if(discount.equals("")) {
                        discount="0";
                    }
                    if(total.equals("")) {
                        total="0";
                    }


                    double amt = Double.parseDouble(discount);

                    double tot = Double.parseDouble(total);

                    double balanceamout = tot - amt;
                    finalamount.setText("" + balanceamout);
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {





            }
        });



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
        for (int s = 0; s < grosslvaluearray.size(); s++) {
            sum = sum + grosslvaluearray.get(s);

        }
        String sums = String.valueOf(sum);
        Gradnd_Total.setText(sums);
        double sumtotaldiscountamount = 0;
        for (int s = 0; s < totaldiscountamount.size(); s++) {
            sumtotaldiscountamount = sumtotaldiscountamount + totaldiscountamount.get(s);

        }
        String sumtotaldis = String.valueOf(sumtotaldiscountamount);
        Gradnd_Total.setText(sumtotaldis);
    }


    @Override
    public void CloseItems(int position) {
        grosslvaluearray.remove(position);
        subtotalvaluearray.remove(position);
        totaldiscountamount.remove(position);
        listSalesOrderDetailsList_new.remove(position);
//
        if (position == 1) {
            visible_button_andrate.setVisibility(View.VISIBLE);
        }
        categoruAdapter.notifyDataSetChanged();
//
        setTotal();
    }


    private void ClearAll() {
        grosslvaluearray.clear();
        subtotalvaluearray.clear();
        totaldiscountamount.clear();
        listSalesOrderDetailsList_new.clear();
        edtName.setText("");
        edtPhone.setText("");
        edtEmail.setText("");
        catg.clear();
        full_cate.clear();

//            visible_button_andrate.setVisibility(View.VISIBLE);

        categoruAdapter.notifyDataSetChanged();

    }

    private void setTotal() {


        double sum = 0;
        for (int s = 0; s < grosslvaluearray.size(); s++) {
            sum = sum + grosslvaluearray.get(s);

        }
        String sums = String.valueOf(sum);
        grandtotal_value.setText(sums);


        double sumtotaldiscountamount = 0;
        for (int s = 0; s < totaldiscountamount.size(); s++) {
            sumtotaldiscountamount = sumtotaldiscountamount + totaldiscountamount.get(s);

        }

        String sumtotaldis = String.valueOf(sumtotaldiscountamount);
        total_discount_amount.setText(sumtotaldis);


        //gross value
        double sum_orginalvalue = 0;
        for (int s = 0; s < subtotalvaluearray.size(); s++) {
            sum_orginalvalue = sum_orginalvalue + subtotalvaluearray.get(s);

        }
        String sum_org = String.valueOf(sum_orginalvalue);
        subtotal_total.setText(sum_org);


    }

    @Override
    public void OnSuccessListItemByShopSingle(String message, com.example.work.cartapp.Mod.SingleItemModel.Data mData) {

//        showDialogOforeder();
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

//        Snackbar.make(getCurrentFocus(), "OnFailedListItemByShopSingle"+message, Snackbar.LENGTH_SHORT)
//                .setAction("TryAgain", null).show();

        setSnakMessage(message);


    }

    public void setSpinner() {

//        progressBar.setMessage("Loading..");
//        progressBar.show();
        Long shopid = sessionManager.getUserId();
        GetListItembyShopmodel getListItembyShopmodel = new GetListItembyShopmodel();
        getListItembyShopmodel.setItemDataId((long) 0);
        getListItembyShopmodel.setShopId(shopid);
        Log.e("SHOP ID: ", "" + shopid);
        HttpRequestForGetListItemByShop httpRequestForGetListItemByShop = new HttpRequestForGetListItemByShop(this);
        httpRequestForGetListItemByShop.getEvents(getListItembyShopmodel);

    }

    @Override
    public void OnSuccessListItemByShop(String message, List<Datum> mData) {
        progressBar.cancel();
        setSpinnerWithadata(mData);
    }

    @SuppressLint("LongLogTag")
    private void setSpinnerWithadata(List<Datum> mData) {
        int count = mData.size();
        Log.e("setSpinnerWithadata count: ", "" + count);
        if (count > 0) {

            listItemName = new ArrayList<>();
            listItemCode = new ArrayList<>();

            listItemName.add("Select Product");
            listItemCode.add("0");
            for (int i = 0; i < count; i++) {
                String itemname = mData.get(i).getItemsDetails().getItemName();
                String itemcode = mData.get(i).getItemsDetails().getItemId().toString();

                listItemName.add(itemname);
                listItemCode.add(itemcode);
            }
            setSpinnerToView(listItemName);
        } else {
            Toast.makeText(this, "1325 in take order", Toast.LENGTH_SHORT).show();
        }


    }

    private void setSpinnerToView(ArrayList<String> listItemName) {


        Log.e("setSpinnerToView: ", "" + listItemName);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listItemName);
        spinnerStockItems.setAdapter(adapter);
    }

    @Override
    public void OnFailedListItemByShop(String message) {
        progressBar.cancel();
        setSnakMessage(message);

    }

    @Override
    public void OnSuccessSaveOrder(boolean stautus, String message) {
        progressBar.cancel();
ClearAll();

Intent intent=new Intent(this,DetailsOfOrder.class);
intent.putExtra("ids",message);
startActivity(intent);
finish();
//        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void OnFailedSaveOrder(String message) {
        progressBar.cancel();
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    public void OnBackClick(View view) {
        onBackPressed();
    }
}
