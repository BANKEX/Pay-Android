package com.elegion.android.rx;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.elegion.android.dialog.NetworkErrorDialog;
import com.elegion.android.dialog.UnexpectedErrorDialog;
import com.elegion.android.view.ErrorView;

import rx.functions.Action1;
import timber.log.Timber;

/**
 * @author Daniel Serdyukov
 */
public final class RxError {

    private RxError() {
        //
    }

    @NonNull
    public static ErrorView view(@NonNull Activity activity) {
        return view(activity, activity.getFragmentManager());
    }

    @NonNull
    public static ErrorView view(@NonNull Fragment fragment) {
        return view(fragment.getActivity(), fragment.getFragmentManager());
    }

    @NonNull
    public static ErrorView view(@NonNull Activity activity, @NonNull FragmentManager fm) {
        return new ErrorView() {
            @Override
            public void showNetworkError() {
                new NetworkErrorDialog().show(fm, NetworkErrorDialog.class.getName());
            }

            @Override
            public void showUnexpectedError() {
                new UnexpectedErrorDialog().show(fm, UnexpectedErrorDialog.class.getName());
            }

            @Override
            public void showErrorMessage(@NonNull String message) {
                final View focusView = activity.getWindow().getDecorView().findFocus();
                Snackbar.make(focusView, message, Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void hideErrorMessage() {
                //Not implemented yet
            }
        };
    }

    @NonNull
    public static Action1<Throwable> doNothing() {
        return throwable -> Timber.tag(RxError.class.getSimpleName()).d(throwable, "Something wrong");
    }
}
