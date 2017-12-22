package com.example.work.cartapp.ExternalLib;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by work on 11/27/2017.
 */

public class EditTextRegular extends android.support.v7.widget.AppCompatEditText {

    public EditTextRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public EditTextRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextRegular(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-Regular.ttf");
            setTypeface(tf);
        }
    }

}