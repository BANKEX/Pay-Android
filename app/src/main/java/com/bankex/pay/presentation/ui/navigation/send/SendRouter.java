package com.bankex.pay.presentation.ui.navigation.send;

import android.content.Context;
import android.content.Intent;

import com.bankex.pay.presentation.ui.send.SendActivity;

public class SendRouter implements ISendRouter{

    public void open(Context context) {
        Intent intent = new Intent(context, SendActivity.class);
        context.startActivity(intent);
    }
}
