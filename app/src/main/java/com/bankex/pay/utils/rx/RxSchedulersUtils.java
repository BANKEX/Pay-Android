package com.bankex.pay.utils.rx;

import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Class that provides schedulers for working with Rx
 *
 * @author Gevork Safaryan on 11.09.2018.
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
	public <T> ObservableTransformer<T, T> getIOToMainTransformer() {
		return objectObservable -> objectObservable
				.subscribeOn(getIOScheduler())
				.observeOn(getMainThreadScheduler());
	}
}
