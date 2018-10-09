package com.bankex.pay.utils.share;

import android.content.Context;
import android.support.annotation.StringRes;

/**
 * Интерфейс утилитного класса для работы с шарингом данных
 *
 * @author Pavel Apanovskiy on 17.06.2018.
 */
public interface IShareDataUtils {

    /**
     * Генерим интент в формате строки для шаринга
     *
     * @param context context
     * @param text    текст для шаринга
     */
    void shareDataLikeText(Context context, String text);

    /**
     * Запихиваем текст в буфер обмена
     *
     * @param context       context
     * @param data          текст для копирования
     * @param stringResInfo StringRes текста для toast
     */
    void copyDataToClipboard(Context context, String data, @StringRes int stringResInfo);

    /**
     * Извлекаем CharSequence из буфера обмена
     *
     * @param context context
     * @return CharSequence
     */
    CharSequence getCharSequenceFromClipboard(Context context);
}
