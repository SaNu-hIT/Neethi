package com.example.work.cartapp.Activitys;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.example.work.cartapp.Extras.ExternLib.BusFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by work on 7/21/2017.
 */

public class BaseActivity extends Activity {


    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BusFactory.getBus().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BusFactory.getBus().unregister(this);
    }
    public boolean isConnectedToNet(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;

    }

    public String ConvertDateFormat(String dateToConvert) {

        String myFormat = "EEE, MMM d, yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        DateFormat originalFormat = new SimpleDateFormat("EEE, MMM d, yyyy", Locale.US);
        DateFormat targetFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = originalFormat.parse(dateToConvert);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = targetFormat.format(date);
        return formattedDate;
    }
    public void setProgressBar() {

        progressBar = new ProgressDialog(this,
                ProgressDialog.STYLE_SPINNER);
        progressBar.setIndeterminate(false);
        progressBar.setCancelable(false);

        progressBar.cancel();

    }


    public void showProgress(String message) {
        progressBar.setMessage(message);
        progressBar.show();
    }

    public void cancelProgress() {
        progressBar.cancel();
    }

}
