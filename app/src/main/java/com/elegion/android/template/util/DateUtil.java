package com.elegion.android.template.util;

import android.support.annotation.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by marat.taychinov
 */
public final class DateUtil {

    private static final String DD_MM_YYYY = "dd.MM.yyyy";
    private static final String SERVER_ISO8601_DATE = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final String TIMEZONE_UTC = "UTC";

    private DateUtil() {
    }

    public static String getDateString(@Nullable Date date) {
        if (date == null) {
            return "";
        }

        DateFormat simpleDateFormat = new SimpleDateFormat(DD_MM_YYYY, Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    public static Calendar getCalendar() {
        return Calendar.getInstance(TimeZone.getTimeZone(TIMEZONE_UTC));
    }

    public static Calendar getCalendar(Date date) {
        final Calendar c = Calendar.getInstance(TimeZone.getTimeZone(TIMEZONE_UTC));
        c.setTime(date);
        return c;
    }

    public static Calendar getCalendar(long millis) {
        final Calendar c = Calendar.getInstance(TimeZone.getTimeZone(TIMEZONE_UTC));
        c.setTimeInMillis(millis);
        return c;
    }

    public static Date getTodayDate() {
        return new Date(Calendar.getInstance(TimeZone.getTimeZone(TIMEZONE_UTC)).getTimeInMillis());
    }
}
