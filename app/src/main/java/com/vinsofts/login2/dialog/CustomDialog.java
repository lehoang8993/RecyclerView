package com.vinsofts.login2.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.widget.ImageView;

import com.vinsofts.login2.R;

import butterknife.BindView;

public class CustomDialog {
    private Context context;
    private Drawable drawable;

    ImageView ivFlagCountry;


    public CustomDialog(Context context,Drawable drawable) {
        this.context = context;
        this.drawable = drawable;
    }

    public void DialogFlag (){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.layout_dialog);
        ivFlagCountry = (ImageView) dialog.findViewById(R.id.iv_FlagCountry);
        ivFlagCountry.setImageDrawable(drawable);
        dialog.show();
    }


}
