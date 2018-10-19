package com.bankex.pay.utils.rx;


import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Implementation of {@link RxSchedulersUtils} for tests
 *
 * @author Gevork Safaryan on 11.09.2018.
 */

public class RxSchedulersTestUtils implements IRxSchedulersUtils {
    @Override
    public Scheduler getMainThreadScheduler() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler getIOScheduler() {
        return Schedulers.trampoline();
    }

    @Override
    public <T> ObservableTransformer<T, T> getIOToMainTransformer() {
        return objectObservable -> objectObservable
                .subscribeOn(getIOScheduler())
                .observeOn(getMainThreadScheduler());
    }
}
