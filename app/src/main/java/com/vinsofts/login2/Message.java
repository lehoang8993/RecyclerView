package com.vinsofts.login2;

import android.content.Context;
import android.widget.Toast;

public class Message {
    public static void sMessage(Context context,String message){
        Toast.makeText(context, message, android.widget.Toast.LENGTH_SHORT).show();
    }
}
