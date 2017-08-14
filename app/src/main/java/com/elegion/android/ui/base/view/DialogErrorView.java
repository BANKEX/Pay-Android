package com.elegion.android.ui.base.view;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentManager;

import com.elegion.android.R;
import com.elegion.android.ui.dialog.MessageDialog;

/**
 * @author Mike
 */
public class DialogErrorView implements ErrorView {
    private FragmentManager mFragmentManager;
    private Resources mResources;

    public DialogErrorView(@NonNull FragmentManager fragmentManager, @NonNull Resources resources) {
        mFragmentManager = fragmentManager;
        mResources = resources;
    }

    @Override
    public void showNetworkError() {
        showDialog(R.string.error_network);
    }

    @Override
    public void showUnexpectedError() {
        showDialog(R.string.error_unexpected);
    }

    @Override
    public void showErrorMessage(@NonNull String message) {
        showDialog(message);
    }

    @Override
    public void showErrorMessage(@StringRes int message) {
        showDialog(message);
    }

    @Override
    public void hideErrorMessage() {
        //Not implemented yet
    }

    private void showDialog(String message) {
        if (mFragmentManager != null) {
            MessageDialog.show(mFragmentManager, null, message,
                    mResources.getString(R.string.btn_ok), null);
        }
    }

    private void showDialog(@StringRes int message) {
        showDialog(mResources.getString(message));
    }
}
