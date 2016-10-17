package com.elegion.android.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.elegion.android.R;

/**
 * @author Daniel Serdyukov
 */
public class UnexpectedErrorDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity(), R.style.AppTheme_Dialog)
                .setMessage(R.string.error_unexpected)
                .setPositiveButton(R.string.button_ok, null)
                .create();
    }

}
