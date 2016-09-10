package com.elegion.android.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.elegion.android.view.MainView;

import java.util.concurrent.TimeUnit;

import ru.elegion.rxloadermanager.RxLoaderManager;
import ru.elegion.rxloadermanager.RxLoaderObserver;
import rx.Observable;

/**
 * @author Rovkin Max
 */
public class MainPresenter {

    @NonNull
    private final Context mContext;

    @NonNull
    private final MainView mView;

    private final MyLoaderObserver mMyLoaderObserver = new MyLoaderObserver();

    @NonNull
    private final RxLoaderManager mRxLoaderManager;

    public MainPresenter(@NonNull Context context, @NonNull MainView view, @NonNull RxLoaderManager loaderManager) {
        mContext = context;
        mView = view;
        mRxLoaderManager = loaderManager;
    }

    public void loadContent() {

        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS);


    }

    public void dispatchStart() {
    }

    public void dispatchStop() {
    }

    private class MyLoaderObserver extends RxLoaderObserver<Long> {
        @Override
        public void onStarted() {
            Log.d("***", "onStarted");
        }

        @Override
        public void onNext(Long result) {
            Log.d("***", "onNext " + result);
            mView.showMainText("" + result);
        }

        @Override
        public void onError(Throwable error) {
            Log.d("***", "onError " + error);
        }
    }
}
