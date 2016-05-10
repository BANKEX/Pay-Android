package com.elegion.android.presenter;

import android.support.annotation.NonNull;

import com.elegion.android.view.MainView;

/**
 * @author Rovkin Max
 */
public class MainPresenter {
    @NonNull
    private final MainView mView;

    public MainPresenter(@NonNull MainView view) {
        mView = view;
    }

    public void loadContent() {
        mView.showMainText("Hello world");
    }
}
