package com.mmadapps.firebaseexample.customviews;


import android.content.Context;
import android.util.AttributeSet;

import com.mmadapps.firebaseexample.customs.CrickFreaksApplication;


public class AllerRegularTextview extends android.support.v7.widget.AppCompatTextView {

    public AllerRegularTextview(Context context) {
        super(context);
        init(context);
    }

    public AllerRegularTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public AllerRegularTextview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }


    private void init(Context context) {
        CrickFreaksApplication crickFreaksApplication = (CrickFreaksApplication) context.getApplicationContext();
        setTypeface(crickFreaksApplication.ALLER_REGULAR);
    }

}
