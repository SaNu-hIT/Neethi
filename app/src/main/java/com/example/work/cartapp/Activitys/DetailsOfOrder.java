package com.example.work.cartapp.Activitys;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.StrictMode;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.work.cartapp.Adapter.BillListAdapetr;
import com.example.work.cartapp.Connection.HttpRequestForDownLoadtem;
import com.example.work.cartapp.Connection.HttpRequestForOrder;
import com.example.work.cartapp.Extras.TextAquino;
import com.example.work.cartapp.Interface.OnHttpResponseDownloadItem;
import com.example.work.cartapp.Interface.OnHttpResponseOrderDetails;
import com.example.work.cartapp.Mod.DownModel;
import com.example.work.cartapp.Model.ListModel.DetailsList.SalesOrderDetailsList;
import com.example.work.cartapp.Model.ListModel.DetailsList.Data;
import com.example.work.cartapp.Model.ListModel.ParameterListOrder;
import com.example.work.cartapp.R;
import com.example.work.cartapp.Extras.SessionManager.SessionManager;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

import static com.example.work.cartapp.Retrofit.ApiClient.BASE_URL;

public class DetailsOfOrder extends AppCompatActivity implements OnHttpResponseOrderDetails, OnHttpResponseDownloadItem {
TextView customername,address,phonenumber,shopemail,invoice_id,subtotal,CGST_Total,SGST_Total,IGST_Total,GST_Total,subtotal_full,discount;
RecyclerView recyclerView;
BillListAdapetr billListAdapetr;
NestedScrollView lnrh;
TextAquino  shopaddress;
Button print;
LinearLayout jkn;
    private List<SalesOrderDetailsList> salesOrderDetail;
    private ProgressDialog progressBar;
//    List<SalesOrderDetailsListForRecyclerView> salesOrderDetailsListList
String id_new;
ImageView backclick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_of_order);
        customername=findViewById(R.id.customername);
        this.setFinishOnTouchOutside(false);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }
        progressBar = new ProgressDialog(this,
                ProgressDialog.STYLE_SPINNER);
        progressBar.setIndeterminate(false);
        progressBar.setMessage("Loading...");
        progressBar.setCancelable(false);
        print=findViewById(R.id.print);
        jkn=findViewById(R.id.jkn);
        lnrh=findViewById(R.id.lnrh);
        address=findViewById(R.id.address);
        backclick=findViewById(R.id.backclick);
        invoice_id=findViewById(R.id.invoice_id);
        phonenumber=findViewById(R.id.phonenumber);
        subtotal=findViewById(R.id.subtotal);
        discount=findViewById(R.id.discount);
        shopaddress=findViewById(R.id.shopaddress);

        subtotal_full=findViewById(R.id.subtotal_full);
        CGST_Total=findViewById(R.id.CGST_Total);
        SGST_Total=findViewById(R.id.SGST_Total);
        IGST_Total=findViewById(R.id.IGST_Total);
        GST_Total=findViewById(R.id.GST_Total);
        shopemail=findViewById(R.id.shopemail);

        backclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownModel downModel=new DownModel();
                downModel.setPageOrientation("landscape");
                downModel.setPageSize("a4");
                downModel.setType("htmlToPdf");
                downModel.setUrl(BASE_URL+"/Order/SInvoice?Invoice_Id="+id_new);
                DownLoadDataAndPrint("https://api.sejda.com/v1/tasks",downModel);

            }
        });

        recyclerView=findViewById(R.id.recycerview);
        salesOrderDetail=new ArrayList<>();

        Intent intent=getIntent();
        id_new = intent.getStringExtra("ids");

        billListAdapetr=new BillListAdapetr(salesOrderDetail,this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SessionManager sessionManager=new SessionManager(this);
        Long shopid=sessionManager.getUserId();

        ParameterListOrder parameterListOrder=new ParameterListOrder();
        parameterListOrder.setSalesOrderId(Long.valueOf(id_new));
        parameterListOrder.setShopId(shopid);
        progressBar.show();
        HttpRequestForOrder httpRequestForOrder=new HttpRequestForOrder(this);
        httpRequestForOrder.getItemDetis(parameterListOrder);
//        print.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DownModel downModel=new DownModel();
//                downModel.setPageOrientation("landscape");
//                downModel.setPageSize("a4");
//                downModel.setType("htmlToPdf");
//                downModel.setUrl("http://saneeshvs-001-site1.itempurl.com/Order/SInvoice?Invoice_Id="+id_new);
//                DownLoadDataAndPrint("https://api.sejda.com/v1/tasks",downModel);
//            }
//        });
//
//        print.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
//                {
//                    downloadData();
//                    Log.e("callPhone: ", "permission" );
//                } else {
//                    ActivityCompat.requestPermissions(DetailsOfOrder.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
//                    Toast.makeText(getApplicationContext(), "need permission", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
    }

    private void DownLoadDataAndPrint(String url, DownModel downModel) {
progressBar.setMessage("Preppairing invoice");
progressBar.show();
        HttpRequestForDownLoadtem httpRequestForDownLoadtem=new HttpRequestForDownLoadtem(this);
        httpRequestForDownLoadtem.download(url,downModel);


    }

    private void downloadData() {


        Bitmap bitmap = Bitmap.createBitmap(
                lnrh.getChildAt(0).getWidth()*2,
                lnrh.getChildAt(0).getHeight()*2,
                Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bitmap);
        c.scale(2.0f, 2.0f);
        c.drawColor(getResources().getColor(R.color.colorPrimary));
        lnrh.getChildAt(0).draw(c);
        // Do whatever you want with your bitmap
        saveBitmap(bitmap);

    }
    public void saveBitmap(Bitmap bitmap) {
        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + "SidduInvoices");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        if (success) {
            // Do something on success
        } else {
            // Do something else on failure
        }

        File imagePath = new File(Environment.getExternalStorageDirectory() + "/SidduInvoices/Siddus.png");

        if(imagePath.exists())
        {
            imagePath=new File(Environment.getExternalStorageDirectory() + "/SidduInvoices/Siddus.png");

        }
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
//            progressBar.cancel();


            final File finalImagePath = imagePath;





        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }



    }
    @Override
    public void OnSuccessListOrderDetails(String message, Data mData) {
progressBar.cancel();
        Gson gson = new Gson();
        String json = gson.toJson(mData);

        Log.e( "mData RESPO: ", ""+json);
        customername.setText(mData.getCustomerName());
        phonenumber.setText(mData.getCustomerPhno());
        address.setText(mData.getCustomerAddress());
        subtotal.setText(mData.getGrandTotalOrder().toString());
        subtotal_full
                .setText(mData.getTotalOrder().toString());





//        CGST_Total.setText("CGST Total :"+mData.getCGSTTotal().toString());
        discount.setText(mData.getDiscountAmnt().toString());
        shopaddress.setText(mData.getShopDetails().getAddress());
        shopemail.setText(mData.getShopDetails().getEmail());

//        SGST_Total.setText(mData.getSGSTTotalAmnt().toString());
//        IGST_Total.setText(mData.getIGSTTotalAmnt().toString());
//        GST_Total.setText(mData.getGSTTotalAmnt().toString());



        List<SalesOrderDetailsList> salesOrderDetailsListList=mData.getSalesOrderDetailsList();

        Log.e( "Sales order details  ",""+salesOrderDetail.size() );
        billListAdapetr=new BillListAdapetr(salesOrderDetailsListList,this);
        recyclerView.setAdapter(billListAdapetr);
        billListAdapetr.notifyDataSetChanged();


    }

    @Override
    public void OnFailedListOrderDetails(String message) {
progressBar.cancel();
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnSuccessItem(String message, ResponseBody body) {
        Log.e( "OnSuccessItem: ",""+message );


        runThread(body);



    }

    private void runThread(final ResponseBody body) {



            new Thread() {
                public void run() {

                        try {
                            runOnUiThread(new Runnable() {

                                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                                @Override
                                public void run() {

                                    boolean done=  writeResponseBodyToDisk(body);
                                    if(done)
                                    {
                                        Log.e( "run: ", ""+done);
                                    }

                                }
                            });
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                }
            }.start();

    }

    @Override
    public void OnFailedItem(String message) {
        Log.e( "fails: ",""+message );
    }










    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            // todo change the file location/name according to your needs
            File futureStudioIconFile = new File(getExternalFilesDir(null) + File.separator + "Future Studio Icon.pdf");

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.e( "dsbjd: ",futureStudioIconFile.getAbsolutePath() );

                    Log.d("File download", "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {



                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }

                showPrintAlert(futureStudioIconFile);
            }
        } catch (IOException e) {
            return false;
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void showPrintAlert(final File urlsc) {


        PrintDocumentAdapter pda = new PrintDocumentAdapter(){

            @Override
            public void onWrite(PageRange[] pages, ParcelFileDescriptor destination, CancellationSignal cancellationSignal, WriteResultCallback callback){
                InputStream input = null;
                OutputStream output = null;

                try {

                    input = new FileInputStream(urlsc);
                    output = new FileOutputStream(destination.getFileDescriptor());

                    byte[] buf = new byte[1024];
                    int bytesRead;

                    while ((bytesRead = input.read(buf)) > 0) {
                        output.write(buf, 0, bytesRead);
                    }

                    callback.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});

                } catch (FileNotFoundException ee){
                    //Catch exception
                } catch (Exception e) {
                    //Catch exception
                } finally {
                    try {
                        input.close();
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onLayout(PrintAttributes oldAttributes, PrintAttributes newAttributes, CancellationSignal cancellationSignal, LayoutResultCallback callback, Bundle extras){

                if (cancellationSignal.isCanceled()) {
                    callback.onLayoutCancelled();
                    return;
                }


                PrintDocumentInfo pdi = new PrintDocumentInfo.Builder("Name of file").setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT).build();

                callback.onLayoutFinished(pdi, true);
            }
        };
        progressBar.cancel();
        PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
        String jobName = this.getString(R.string.app_name) + " Document";

        PrintAttributes attrib = new PrintAttributes.Builder()
                .setMediaSize(PrintAttributes.MediaSize.ISO_A4.asLandscape())
                . build();

        printManager.print(jobName, pda, attrib);





    }



}




