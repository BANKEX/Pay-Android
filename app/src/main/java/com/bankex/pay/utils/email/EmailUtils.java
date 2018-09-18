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

    private static final String EMAIL = "e-mail";

    /**
     * Пытаемся открыть почтовый клиент с заданным e-mail
     *
     * @param context context
     */
    public void createEmail(Context context) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/plain");
        intent.setData(Uri.parse("mailto:" + EMAIL));

        try {
            context.startActivity(Intent.createChooser(intent, "Send Email"));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "No apps can perform this action", Toast.LENGTH_SHORT).show();
        }
    }
}
