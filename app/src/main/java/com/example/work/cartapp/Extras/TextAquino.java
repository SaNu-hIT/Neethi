package com.example.work.cartapp.Extras;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;


public class TextAquino extends TextView {

    public TextAquino(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TextAquino(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextAquino(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Aquino-Demo.ttf");
            setTypeface(tf);
        }
    }

}