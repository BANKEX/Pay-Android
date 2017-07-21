package com.elegion.android.ui.base.view;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.pchela.android.R;
import com.pchela.android.ui.dialog.MessageCustomDialog;

import static com.pchela.android.ui.main.MainActivity.TAG_DIALOG_EXCLUDE;

/**
 * @author Max Kuznetsov on 15-Jun-17.
 */

public class DialogErrorView implements ErrorView {

    private AppCompatActivity mActivity;
    private Fragment mFragment;

    public DialogErrorView(@NonNull AppCompatActivity activity) {
        mActivity = activity;
    }

    public DialogErrorView(Fragment fragment) {
        mFragment = fragment;
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
        showActivityDialog(message);
        showFragmentDialog(message);
    }

    private void showActivityDialog(String message) {
        if (mActivity != null) {
            MessageCustomDialog.show(mActivity.getSupportFragmentManager(), message,
                    mActivity.getString(R.string.close_button_text), TAG_DIALOG_EXCLUDE);
        }
    }

    private void showFragmentDialog(String message) {
        if (mFragment != null && mFragment.getContext() != null) {
            MessageCustomDialog.show(mFragment.getChildFragmentManager(), message,
                    mFragment.getString(R.string.close_button_text), TAG_DIALOG_EXCLUDE);
        }
    }

    private void showDialog(@StringRes int message) {
        showActivityDialog(message);
        showFragmentDialog(message);
    }

    private void showActivityDialog(@StringRes int message) {
        if (mActivity != null) {
            MessageCustomDialog.show(mActivity.getSupportFragmentManager(), mActivity.getString(message),
                    mActivity.getString(R.string.close_button_text), TAG_DIALOG_EXCLUDE);
        }
    }

    private void showFragmentDialog(@StringRes int message) {
        if (mFragment != null && mFragment.getContext() != null) {
            MessageCustomDialog.show(mFragment.getChildFragmentManager(),
                    mFragment.getString(message),
                    mFragment.getString(R.string.close_button_text), TAG_DIALOG_EXCLUDE);
        }
    }
}
