package com.bankex.pay.utils.email;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

/**
 * Утилитный класс для работы с e-mail
 *
 * @author Pavel Apanovskiy on 15.09.2018.
 */
public class EmailUtils {

    /**
     * Пытаемся открыть почтовый клиент с заданным e-mail
     *
     * @param context context
     * @param email   e-mail на который необходимо отправить кляузу
     */
    public void createEmail(Context context, String email) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/plain");
        intent.setData(Uri.parse("mailto:" + email));

        try {
            context.startActivity(Intent.createChooser(intent, "Send Email"));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "No apps can perform this action", Toast.LENGTH_SHORT).show();
        }
    }
}
