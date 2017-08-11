package com.elegion.android.ui.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;

/**
 * Created by azret.magometov
 */
public class SingleChoiceDialog extends DialogFragment {

    private static final String ARG_OPTIONS = "ARG_OPTIONS";
    private static final String ARG_TITLE = "ARG_TITLE";

    @ArrayRes
    int mOptions;
    @Nullable
    private String mTitle;

    private Callback mCallback;

    public static void show(@NonNull FragmentManager fm, @ArrayRes int options, @Nullable String title) {
        show(fm, options, title, SingleChoiceDialog.class.getName());
    }

    public static void show(@NonNull FragmentManager fm, @ArrayRes int options,
                            @Nullable String title, @NonNull String tag) {
        Bundle args = new Bundle();
        args.putInt(ARG_OPTIONS, options);
        args.putString(ARG_TITLE, title);
        SingleChoiceDialog dialog = new SingleChoiceDialog();
        dialog.setArguments(args);
        dialog.show(fm, tag);
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
        if (!TextUtils.isEmpty(mTitle)) {
            builder.setTitle(mTitle);
        }
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
            mTitle = args.getString(ARG_TITLE);
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