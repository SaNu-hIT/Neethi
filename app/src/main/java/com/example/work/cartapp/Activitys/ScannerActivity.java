package com.example.work.cartapp.Activitys;

import android.os.Bundle;

import com.example.work.cartapp.R;

/**
 * Created by work on 10/4/2017.
 */

public class ScannerActivity extends BaseScannerActivity {
    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_simple_scanner_fragment);
        setupToolbar();
    }
}