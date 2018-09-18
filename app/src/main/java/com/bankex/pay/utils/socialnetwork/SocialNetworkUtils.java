package com.bankex.pay.utils.socialnetwork;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Утилитный класс для перехода в соц. сети
 *
 * @author Pavel Apanovskiy on 18.09.2018.
 */
public class SocialNetworkUtils {

    private static final String TWITTER_USER_NAME = "BANKEX";
    private static final String FACEBOOK_USER_NAME = "BankExchange";
    private static final String FACEBOOK_PAGE_ID = "428217707302739";

    /**
     * Переходим в twitter по ссылке
     *
     * @param context Context
     */
    public void goToTwitter(Context context) {
        Uri uri = Uri.parse("https://twitter.com/" + TWITTER_USER_NAME);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

    /**
     * Переходим в facebook по ссылке
     *
     * @param context Context
     */
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
