package com.mmadapps.firebaseexample.news;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Window;
import android.view.WindowManager;

import com.mmadapps.firebaseexample.R;
import com.mmadapps.firebaseexample.customviews.AllerBoldTextview;
import com.mmadapps.firebaseexample.customviews.AllerRegularTextview;

public class ApplySchemeDialogueFragment extends DialogFragment {

    Dialog dialog;
    String title,desc;
    AllerBoldTextview vT_newstitle_nf;
    AllerRegularTextview vT_newsdesc_nf;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        title = getArguments().getString("Title");
        desc=getArguments().getString("Desc");
        dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.setContentView(R.layout.apply_scheme_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        vT_newstitle_nf = (AllerBoldTextview)dialog.findViewById(R.id.vT_newstitle_nf);
        vT_newsdesc_nf = (AllerRegularTextview) dialog.findViewById(R.id.vT_newsdesc_nf);
        vT_newstitle_nf.setText(title);
        vT_newsdesc_nf.setText(desc);
        dialog.show();

        return dialog;
    }


}
