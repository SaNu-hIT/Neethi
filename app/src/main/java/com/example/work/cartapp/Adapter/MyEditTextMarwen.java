package com.example.work.cartapp.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by work on 10/25/2017.
 */

public class MyEditTextMarwen extends EditText {

    public MyEditTextMarwen(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyEditTextMarwen(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEditTextMarwen(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/MavenPro-Regular.ttf");
            setTypeface(tf);
        }
    }


}