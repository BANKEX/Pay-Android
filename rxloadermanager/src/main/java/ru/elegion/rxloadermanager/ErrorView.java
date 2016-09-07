package ru.elegion.rxloadermanager;

import android.support.annotation.NonNull;

/**
 * @author Daniel Serdyukov
 */
public interface ErrorView {

    void showNetworkError();

    void showUnexpectedError();

    void showErrorMessage(@NonNull String message);

    void hideErrorMessage();
}
