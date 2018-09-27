package com.bankex.pay.presentation.ui.navigation.settings;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.bankex.pay.presentation.ui.navigation.BaseRouter;

/**
 * Реализация роутера экрана настроек {@link ISettingsRouter}
 *
 * @author Pavel Apanovskiy on 08.09.2018.
 */
public class SettingsRouter extends BaseRouter implements ISettingsRouter {

    private static final String EMAIL = "e-mail";
    private static final String TWITTER_USER_NAME = "BANKEX";
    private static final String FACEBOOK_USER_NAME = "BankExchange";
    private static final String FACEBOOK_PAGE_ID = "428217707302739";

    /**
     * {@inheritDoc }
     */
    @Override
    public void goToEmail(Context context) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/plain");
        intent.setData(Uri.parse("mailto:" + EMAIL));

        try {
            context.startActivity(Intent.createChooser(intent, "Send Email"));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "No apps can perform this action", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void goToTwitter(Context context) {
        Uri uri = Uri.parse("https://twitter.com/" + TWITTER_USER_NAME);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void goToFacebook(Context context) {
        Uri uri = Uri.parse("https://facebook.com/" + FACEBOOK_USER_NAME);
        Intent intent;
        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/" + FACEBOOK_PAGE_ID));
        } catch (Exception e) {
            intent = new Intent(Intent.ACTION_VIEW, uri);
        }
        context.startActivity(intent);
    }
}
