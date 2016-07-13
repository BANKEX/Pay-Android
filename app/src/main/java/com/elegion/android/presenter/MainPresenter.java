package com.elegion.android.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.elegion.android.R;
import com.elegion.android.view.MainView;

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
        mView.showMainText(mContext.getString(R.string.hello_world));
    }
}
