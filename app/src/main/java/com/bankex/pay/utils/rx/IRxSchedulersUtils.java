package com.bankex.pay.utils.rx;

import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;

/**
 * Интерфейс для класса {@link RxSchedulersUtils}
 *
 * @author Gevork Safaryan on 11.09.2018.
 */

public interface IRxSchedulersUtils {

    Scheduler getMainThreadScheduler();

    Scheduler getIOScheduler();

    <T> ObservableTransformer<T, T> getIOToMainTransformer();
}
