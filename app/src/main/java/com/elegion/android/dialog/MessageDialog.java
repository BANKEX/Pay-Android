package com.elegion.android.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

/**
 * @author Maksim Radko
 */
public class MessageDialog extends DialogFragment {

    private static final String KEY_MESSAGE = "MESSAGE";

    public static void showWithText(@NonNull FragmentManager fm, @NonNull String string) {
        final MessageDialog dialog = new MessageDialog();
        final Bundle args = new Bundle();
        args.putString(KEY_MESSAGE, string);
        dialog.setArguments(args);
        dialog.show(fm, MessageDialog.class.getName());
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Bundle args = getArguments();
        final String message = args.getString(KEY_MESSAGE, "");
        return new AlertDialog.Builder(getActivity())
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }
}
