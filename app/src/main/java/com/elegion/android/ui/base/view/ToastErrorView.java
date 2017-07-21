package com.elegion.android.ui.base.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.pchela.android.R;

/**
 * @author mikhail barannikov
 */
public class ToastErrorView implements ErrorView {

    private Context mContext;

    public ToastErrorView(@NonNull Context context) {
        mContext = context;
    }

    @Override
    public void showNetworkError() {
        showToast(R.string.error_network);
    }

    @Override
    public void showUnexpectedError() {
        showToast(R.string.error_unexpected);
    }

    @Override
    public void showErrorMessage(@NonNull String message) {
        showToast(message);
    }

    @Override
    public void showErrorMessage(@StringRes int message) {
        showToast(message);
    }

    private void showToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    private void showToast(@StringRes int message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideErrorMessage() {
        //Not implemented yet
    }
}
