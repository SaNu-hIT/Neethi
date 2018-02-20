package com.example.work.cartapp.ExternalLib;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import me.grantland.widget.AutofitTextView;

/**
 * Created by work on 11/8/2017.
 */

public class AutofitTextViewCustomFont extends AutofitTextView
{

    public AutofitTextViewCustomFont(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public AutofitTextViewCustomFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AutofitTextViewCustomFont(Context context) {
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
