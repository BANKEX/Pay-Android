package com.bankex.pay.utils.rx;

import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Util class for RxSchedulers
 */
public class RxSchedulersUtils implements IRxSchedulersUtils {
	@Override
	public Scheduler getMainThreadScheduler() {
		return AndroidSchedulers.mainThread();
	}

	@Override
	public Scheduler getIOScheduler() {
		return Schedulers.io();
	}

	@Override
	public <T> ObservableTransformer<T, T> getItToMainTransformer() {
		return objectObservable -> objectObservable
				.subscribeOn(getIOScheduler())
				.observeOn(getMainThreadScheduler());
	}
}
