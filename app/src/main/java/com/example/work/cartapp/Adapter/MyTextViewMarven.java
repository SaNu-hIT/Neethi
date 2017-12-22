package com.example.work.cartapp.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by work on 10/25/2017.
 */

public class MyTextViewMarven extends TextView {

    public MyTextViewMarven(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyTextViewMarven(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextViewMarven(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-SemiBold.ttf");
            setTypeface(tf);
        }
    }


}
