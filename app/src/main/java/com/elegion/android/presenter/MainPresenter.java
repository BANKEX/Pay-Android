package com.elegion.android.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;

import com.elegion.android.R;
import com.elegion.android.view.MainView;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import ru.elegion.rxloadermanager.loader.RxLoader;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * @author Rovkin Max
 */
public class MainPresenter {

    @NonNull
    private final Context mContext;

    @NonNull
    private final MainView mView;

    private RxLoader mRxLoader;

    public MainPresenter(@NonNull Context context, @NonNull MainView view, @NonNull LoaderManager loaderManager) {
        mContext = context;
        mView = view;
        mRxLoader = new RxLoader(context, new WeakReference<>(loaderManager));
    }

    public void loadContent() {

        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS);

        observable
                .compose(mRxLoader.initAsync(R.id.timer_loader))
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(value -> {
                    if (value != null) {
                        mView.showMainText("" + value);
                    }
                });
    }
}
