package com.elegion.android.template.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.elegion.android.template.R;

import java.util.List;

import timber.log.Timber;

/**
 * @author Mike
 */
public final class UrlUtils {

    private static final String APP_MARKET_URI = "market://details?id=";
    private static final String WEB_MARKET_URI = "https://play.google.com/store/apps/details?id=";

    private UrlUtils() {}

    public static void openUrl(Context context, String url) {
        final CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary));
        final CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(context, Uri.parse(url));
    }

    public static void openPlayMarket(Context context) {
        final String appPackageName = context.getPackageName();
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(APP_MARKET_URI + appPackageName)));
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(WEB_MARKET_URI + appPackageName)));
            Timber.e(e, "Failed to open market");
        }
    }

    public static boolean sendEmail(@NonNull Context context, @Nullable String subject,
                                    @Nullable String message, @Nullable String... recipients) {
        final Intent email = new Intent(Intent.ACTION_SENDTO);
        if (!TextUtils.isEmpty(subject)) {
            email.putExtra(Intent.EXTRA_SUBJECT, subject);
        }
        if (!TextUtils.isEmpty(message)) {
            email.putExtra(Intent.EXTRA_TEXT, message);
        }
        if (recipients != null && recipients.length != 0) {
            email.putExtra(Intent.EXTRA_EMAIL, recipients);
        }
        //need this to prompts email client only
        email.setType("message/rfc822");
        email.setData(Uri.parse("mailto:"));
        final List<ResolveInfo> infos = context.getPackageManager().queryIntentActivities(email, 0);
        if (infos.size() > 0) {
            context.startActivity(email);
            return true;
        } else {
            return false;
        }
    }
}
