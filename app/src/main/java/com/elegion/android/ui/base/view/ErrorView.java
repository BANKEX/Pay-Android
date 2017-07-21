package com.elegion.android.ui.base.view;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

/**
 * @author Mikhail Barannikov
 */
public interface ErrorView {

    void showNetworkError();

    void showUnexpectedError();

    void showErrorMessage(@NonNull String message);

    void showErrorMessage(@StringRes int message);

    void hideErrorMessage();
}
