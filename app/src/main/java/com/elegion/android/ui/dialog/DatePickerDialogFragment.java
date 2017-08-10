package com.elegion.android.ui.dialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * @author Mike
 */
public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private static final String ARG_MIN_DATE = "ARG_MIN_DATE";
    private static final String ARG_MAX_DATE = "ARG_MAX_DATE";

    private long mMinDate;
    private long mMaxDate;
    private Callback mCallback;

    public static void show(@NonNull FragmentManager fm,
                            long minDate,
                            long maxDate) {
        final DatePickerDialogFragment dialog = new DatePickerDialogFragment();
        final Bundle args = new Bundle();
        args.putLong(ARG_MIN_DATE, minDate);
        args.putLong(ARG_MAX_DATE, maxDate);
        dialog.setArguments(args);
        dialog.show(fm, DatePickerDialogFragment.class.getName());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        resolveCallback(activity);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initArguments();

        final Calendar calendar = Calendar.getInstance();
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = 1970;
        int month = 0;
        int day = 5;

        final DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
        if (mMinDate != -1 && mMinDate != 0) {
            dialog.getDatePicker().setMinDate(mMinDate);
        }
        if (mMaxDate != -1 && mMaxDate != 0) {
            dialog.getDatePicker().setMaxDate(mMaxDate);
        }

        return dialog;
    }

    private void initArguments() {
        final Bundle args = getArguments();
        if (args != null) {
            mMinDate = args.getLong(ARG_MIN_DATE);
            mMaxDate = args.getLong(ARG_MAX_DATE);
        }
    }

    private void resolveCallback(Activity activity) {
        if (getParentFragment() instanceof Callback) {
            mCallback = (Callback) getParentFragment();
        } else if (activity instanceof Callback) {
            mCallback = (Callback) activity;
        }
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        if (mCallback != null) {
            mCallback.onDateSet(year, month, day);
        }
    }

    public interface Callback {
        void onDateSet(int year, int month, int day);
    }

}