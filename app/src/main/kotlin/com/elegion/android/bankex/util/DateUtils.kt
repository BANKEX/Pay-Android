package com.elegion.android.bankex.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateUtils {

    private const val DD_MM_YYYY = "dd.MM.yyyy"
    private const val SERVER_ISO8601_DATE = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    private const val TIMEZONE_UTC = "UTC"

    @JvmStatic
    val calendar: Calendar
        get() = Calendar.getInstance(TimeZone.getTimeZone(TIMEZONE_UTC))

    @JvmStatic
    val todayDate: Date
        get() = Date(Calendar.getInstance(TimeZone.getTimeZone(TIMEZONE_UTC)).timeInMillis)

    @JvmStatic
    fun getDateString(date: Date?): String {
        if (date == null) {
            return ""
        }

        return SimpleDateFormat(DD_MM_YYYY, Locale.getDefault()).format(date)
    }

    @JvmStatic
    fun getCalendar(date: Date): Calendar = Calendar.getInstance(TimeZone.getTimeZone(TIMEZONE_UTC)).apply {
        time = date
    }

    @JvmStatic
    fun getCalendar(millis: Long): Calendar = Calendar.getInstance(TimeZone.getTimeZone(TIMEZONE_UTC)).apply {
        timeInMillis = millis
    }
}
