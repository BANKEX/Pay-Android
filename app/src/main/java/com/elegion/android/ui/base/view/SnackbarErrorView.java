package com.elegion.android.ui.base.view;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.elegion.android.R;

/**
 * @author mikhail barannikov
 */
public class SnackbarErrorView implements ErrorView {

    private Activity mActivity;
    private View mView;

    public SnackbarErrorView(@NonNull Activity activity) {
        mActivity = activity;
    }

    public SnackbarErrorView(View view) {
        mView = view;
    }

    @Override
    public void showNetworkError() {
        showSnackBar(R.string.error_network);
    }

    @Override
    public void showUnexpectedError() {
        showSnackBar(R.string.error_unexpected);
    }

    @Override
    public void showErrorMessage(@NonNull String message) {
        showSnackBar(message);
    }

    @Override
    public void showErrorMessage(@StringRes int message) {
        showSnackBar(message);
    }

    private void showSnackBar(String message) {
        showActivitySnackbar(message);
        showFragmentSnackbar(message);
    }

    private void showActivitySnackbar(String message) {
        if (mActivity != null) {
            final View focusView = mActivity.getWindow().getDecorView().findFocus();
            if (focusView != null && focusView.getContext() != null) {
                Snackbar.make(focusView, message, Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    private void showFragmentSnackbar(String message) {
        if (mView != null && mView.getContext() != null) {
            Snackbar.make(mView, message, Snackbar.LENGTH_SHORT).show();
        }
    }

    private void showSnackBar(@StringRes int message) {
        showActivitySnackbar(message);
        showFragmentSnackbar(message);
    }

    private void showActivitySnackbar(@StringRes int message) {
        if (mActivity != null) {
            final View focusView = mActivity.getWindow().getDecorView().findFocus();
            if (focusView != null && focusView.getContext() != null) {
                Snackbar.make(focusView, message, Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    private void showFragmentSnackbar(@StringRes int message) {
        if (mView != null && mView.getContext() != null) {
            Snackbar.make(mView, message, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void hideErrorMessage() {
        //Not implemented yet
    }
}
