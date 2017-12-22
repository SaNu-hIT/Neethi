package com.example.work.cartapp.ExternalLib;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by Intellyze105 on 14-07-2017.
 */

public class TextViewRobotoRegular extends android.support.v7.widget.AppCompatTextView {

    public TextViewRobotoRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TextViewRobotoRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewRobotoRegular(Context context) {
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