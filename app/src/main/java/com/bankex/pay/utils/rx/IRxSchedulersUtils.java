package com.bankex.pay.utils.rx;

import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;

/**
 * Interface for RxSchedulers.
 */
public interface IRxSchedulersUtils {

	Scheduler getMainThreadScheduler();

	Scheduler getIOScheduler();

	<T> ObservableTransformer<T, T> getItToMainTransformer();
}
