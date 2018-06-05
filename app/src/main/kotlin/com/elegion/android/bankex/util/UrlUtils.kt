package com.elegion.android.bankex.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import com.elegion.android.bankex.R
import timber.log.Timber

object UrlUtils {

    @JvmStatic
    private val APP_MARKET_URI = "market://details?id="
    @JvmStatic
    private val WEB_MARKET_URI = "https://play.google.com/store/apps/details?id="

    @JvmStatic
    fun openUrl(context: Context, url: String) {
        if (!TextUtils.isEmpty(url)) {
            try {
                val builder = CustomTabsIntent.Builder()
                builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(context, Uri.parse(url))
            } catch (e: ActivityNotFoundException) {
                // ignore
            }
        }
    }

    @JvmStatic
    fun openPlayMarket(context: Context) {
        val appPackageName = context.packageName
        try {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(APP_MARKET_URI + appPackageName)))
        } catch (e: ActivityNotFoundException) {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(WEB_MARKET_URI + appPackageName)))
            Timber.e(e, "Failed to open market")
        }
    }

    @JvmStatic
    fun sendEmail(context: Context, subject: String?, message: String?, vararg recipients: String): Boolean {
        val email = Intent(Intent.ACTION_SENDTO)
        if (!TextUtils.isEmpty(subject)) {
            email.putExtra(Intent.EXTRA_SUBJECT, subject)
        }
        if (!TextUtils.isEmpty(message)) {
            email.putExtra(Intent.EXTRA_TEXT, message)
        }
        if (recipients.isNotEmpty()) {
            email.putExtra(Intent.EXTRA_EMAIL, recipients)
        }
        //need this to prompts email client only
        email.type = "message/rfc822"
        email.data = Uri.parse("mailto:")
        val infos = context.packageManager.queryIntentActivities(email, 0)
        if (infos.size > 0) {
            context.startActivity(email)
            return true
        } else {
            return false
        }
    }
}
