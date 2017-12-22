package com.example.work.cartapp.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.work.cartapp.Extras.ExternLib.BusFactory;
import com.example.work.cartapp.Extras.ExternLib.PassEvent;
import com.example.work.cartapp.Extras.SessionManager.SessionManager;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * Created by work on 10/4/2017.
 */

public class SimpleScannerFragment extends Fragment implements ZBarScannerView.ResultHandler {
    private ZBarScannerView mScannerView;
    SessionManager sessionManager;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BusFactory.getBus().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        sessionManager=new SessionManager(getActivity());



//        if (ActivityCompat.checkSelfPermission(getContext(),
//                android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ){
//            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.CAMERA}, 1);
//        } else {
//            Log.e("DB", "PERMISSION GRANTED");
//            mScannerView = new ZBarScannerView(getActivity());
//        }
        mScannerView = new ZBarScannerView(getActivity());
        return mScannerView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        Toast.makeText(getActivity(), "Contents = " + rawResult.getContents() +
                ", Format = " + rawResult.getBarcodeFormat().getName(), Toast.LENGTH_SHORT).show();
        BusFactory.getBus().post(new PassEvent( rawResult.getContents()));
String data=rawResult.getContents();

String id=data.substring(8);
        Log.e( "handleResult: "," "+id );


        sessionManager.setPId(id);
        getActivity().finish();
        // Note:
        // * Wait 2 seconds to resume the preview.
        // * On older devices continuously stopping and resuming camera preview can result in freezing the app.
        // * I don't know why this is the case but I don't have the time to figure out.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.resumeCameraPreview(SimpleScannerFragment.this);
            }
        }, 2000);
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }
}
