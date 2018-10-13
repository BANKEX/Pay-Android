package com.bankex.pay.utils;

import android.content.Context;
import android.content.res.Resources;

import com.bankex.pay.R;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Утилитный класс для преобразования даты
 *
 * @author Pavel Apanovskiy on 02.06.2018.
 */
public class IsoTimeUtils {

    private static final String ISO_TIME_DATE_POSTFIX = "T";
    private static final String ISO_TIME_TIME_POSTFIX = "Z";

    /**
     * Получаем стрингу с датой из isoTime
     *
     * @param isoTime isoTime
     * @return String с датой
     */
    public static String getDateFromIsoTime(String isoTime) {
        return isoTime.substring(0, isoTime.indexOf(ISO_TIME_DATE_POSTFIX));
    }

    /**
     * Получаем стрингу с временем из isoTime
     *
     * @param isoTime isoTime
     * @return String с временем
     */
    public static String getTimeFromIsoTime(String isoTime) {
        return isoTime.substring(isoTime.indexOf(ISO_TIME_DATE_POSTFIX) + 1, isoTime.indexOf(ISO_TIME_TIME_POSTFIX));
    }

    /**
     * Сравниваем текущую дату с датой isoTime и возвращем текст с разницей во времени
     *
     * @param isoTime isoTime
     * @return String
     */
    public static String getDifferenceBetweenCurrentAndAnotherDates(Context context, String isoTime) {
        LocalDateTime localDateTime = LocalDateTime.now();

        String updatedIsoTime = isoTime.substring(0, isoTime.length() - 1);
        LocalDateTime testDateTime = LocalDateTime.parse(updatedIsoTime);

        Period period = new Period(localDateTime, testDateTime);

        int minutes = Math.abs(period.getMinutes());
        int hours = Math.abs(period.getHours());
        int days = Math.abs(period.getDays());
        int months = Math.abs(period.getMonths());
        int weaks = Math.abs(period.getWeeks());
        int years = Math.abs(period.getYears());

        String quantityText = null;
        String dateNumber = null;
        Resources resources = context.getResources();

        if (minutes != 0) {
            quantityText = resources.getQuantityString(R.plurals.minutes, minutes);
            dateNumber = String.valueOf(minutes);
        }
        if (hours != 0) {
            quantityText = resources.getQuantityString(R.plurals.hours, hours);
            dateNumber = String.valueOf(hours);
        }
        if (days != 0) {
            quantityText = resources.getQuantityString(R.plurals.days, days);
            dateNumber = String.valueOf(days);
        }
        if (weaks != 0) {
            quantityText = resources.getQuantityString(R.plurals.weaks, weaks);
            dateNumber = String.valueOf(weaks);
        }
        if (months != 0) {
            quantityText = resources.getQuantityString(R.plurals.months, months);
            dateNumber = String.valueOf(months);
        }
        if (years != 0) {
            quantityText = resources.getQuantityString(R.plurals.years, years);
            dateNumber = String.valueOf(years);
        }

        String differenceText;
        if (quantityText != null) {
            differenceText = resources.getString(R.string.date_with_plurals,
                    String.valueOf(dateNumber),
                    quantityText,
                    getTimeFromIsoTime(isoTime));
        } else {
            differenceText = isoTime;
        }
        return differenceText;
    }

    /**
     * Получаем стрингу с датой из isoTime в красивом формате d mmm, yyyy
     *
     * @param isoTime isoTime
     * @return String с датой
     */
    public static String getDateLikeNormal(String isoTime) {
        DateTime dateTime = getDateTime(isoTime);

        DateTimeFormatter formatter = DateTimeFormat.forPattern("d MMM, yyyy");

        return dateTime.toString(formatter);
    }

    /**
     * Получаем DateTime с датой из isoTime
     *
     * @param isoTime isoTime
     * @return DateTime с датой
     */
    public static DateTime getDateTime(String isoTime) {
        String dateFromIsoTime = getDateFromIsoTime(isoTime);
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        return dateTimeFormatter.parseDateTime(dateFromIsoTime);
    }

    /**
     * Получаем Date с датой из isoTime
     *
     * @param isoTime isoTime
     * @return Date с датой
     */
    public static Date getDate(String isoTime) {
        String dateFromIsoTime = getDateFromIsoTime(isoTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = Calendar.getInstance().getTime();
        try {
            date = dateFormat.parse(dateFromIsoTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
