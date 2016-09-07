package com.elegion.android.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.elegion.android.R;
import com.elegion.android.view.MainView;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * @author Rovkin Max
 */
public class MainPresenter {

    @NonNull
    private final Context mContext;

    @NonNull
    private final MainView mView;

    public MainPresenter(@NonNull Context context, @NonNull MainView view) {
        mContext = context;
        mView = view;
    }

    public void loadContent() {

        Observable observable = Observable.timer(1000, TimeUnit.MILLISECONDS);

        mView.showMainText(mContext.getString(R.string.hello_world));
    }
}
