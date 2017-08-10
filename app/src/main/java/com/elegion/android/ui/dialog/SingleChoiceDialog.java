package com.elegion.android.ui.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by azret.magometov
 */
public class SingleChoiceDialog extends DialogFragment {

    private static final String ARG_OPTIONS = "ARG_OPTIONS";

    @ArrayRes
    int mOptions;

    private Callback mCallback;

    public static void show(@NonNull FragmentManager fm,
                            @ArrayRes int options) {
        final SingleChoiceDialog dialog = new SingleChoiceDialog();
        final Bundle args = new Bundle();
        args.putInt(ARG_OPTIONS, options);
        dialog.setArguments(args);
        dialog.show(fm, SingleChoiceDialog.class.getName());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        resolveCallback(activity);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        initArguments();
        builder.setItems(mOptions, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int option) {
                if (mCallback != null) {
                    mCallback.onOptionSelected(option);
                }
            }
        });
        return builder.create();
    }

    private void initArguments() {
        final Bundle args = getArguments();
        if (args != null) {
            mOptions = args.getInt(ARG_OPTIONS);
        }
    }

    private void resolveCallback(Activity activity) {
        if (getParentFragment() instanceof Callback) {
            mCallback = (Callback) getParentFragment();
        } else if (activity instanceof Callback) {
            mCallback = (Callback) activity;
        }
    }

    public interface Callback {
        void onOptionSelected(int option);
    }

}