package com.bankex.pay.domain.navigation.settings;

import android.content.Context;

/**
 * Роутер экрана настроек
 *
 * @author Pavel Apanovskiy on 08.09.2018.
 */
public interface ISettingsRouter {
    /**
     * Пытаемся открыть почтовый клиент с заданным e-mail
     *
     * @param context context
     */
    void goToEmail(Context context);

    /**
     * Переходим в twitter по ссылке
     *
     * @param context Context
     */
    void goToTwitter(Context context);

    /**
     * Переходим в facebook по ссылке
     *
     * @param context Context
     */
    void goToFacebook(Context context);
}
