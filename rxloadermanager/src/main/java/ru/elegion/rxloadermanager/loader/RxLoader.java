package ru.elegion.rxloadermanager.loader;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;

import java.lang.ref.Reference;

import ru.elegion.rxloadermanager.RxSchedulers;
import rx.Observable;
import rx.functions.Action0;

/**
 * @author Nikita Bumakov
 */
public class RxLoader {

    private final Context mContext;

    private final Reference<LoaderManager> mLmRef;

    public RxLoader(Context context, Reference<LoaderManager> lmRef) {
        mContext = context;
        mLmRef = lmRef;
    }

    public <T> Observable.Transformer<T, T> initAsync(@IdRes final int loaderId) {
        return async(loaderId, false);
    }

    public <T> Observable.Transformer<T, T> restartAsync(@IdRes final int loaderId) {
        return async(loaderId, true);
    }

    @NonNull
    private <T> Observable.Transformer<T, T> async(@IdRes final int loaderId, final boolean restart) {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(final Observable<T> observable) {
                final LoaderManager lm = mLmRef.get();
                if (lm != null) {
                    RxLcImpl<T> rxLc = new RxLcImpl<>(mContext, lm.getLoader(loaderId));
                    return observable
                            .doOnSubscribe(restart
                                    ? restart(loaderId, rxLc)
                                    : init(loaderId, rxLc))
                            .lift(rxLc.getLoader().lifecycle())
                            .compose(rxLc.getLoader().transform(restart))
                            .subscribeOn(RxSchedulers.io())
                            .observeOn(RxSchedulers.main());
                }else {
                    return Observable.empty();
                }
            }
        };
    }

    @NonNull
    private Action0 init(@IdRes final int loaderId, final RxLcImpl rxLc) {
        return new Action0() {
            @Override
            public void call() {
                final LoaderManager lm = mLmRef.get();
                if (lm != null) {
                    synchronized (LoaderManager.class) {
                        lm.initLoader(loaderId, Bundle.EMPTY, rxLc);
                    }
                }
            }
        };
    }

    @NonNull
    private Action0 restart(@IdRes final int loaderId, final RxLcImpl rxLc) {
        return new Action0() {
            @Override
            public void call() {
                final LoaderManager lm = mLmRef.get();
                if (lm != null) {
                    synchronized (LoaderManager.class) {
                        lm.restartLoader(loaderId, Bundle.EMPTY, rxLc);
                    }
                }
            }
        };
    }
}
