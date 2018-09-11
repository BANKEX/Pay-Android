package com.bankex.pay.data.realm;

/**
 * Интерфейс работы с базой данных
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
public interface IRealmService {

    /**
     * Закрыть соединение
     */
    void closeRealm();
}
