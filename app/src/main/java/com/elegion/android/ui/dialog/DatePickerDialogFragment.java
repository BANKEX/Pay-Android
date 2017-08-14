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
    private static final String ARG_INITIAL_DATE = "ARG_INITIAL_DATE";

    private long mMinDate;
    private long mMaxDate;
    private long mInitialDate;
    private Callback mCallback;

    public static void show(@NonNull FragmentManager fm, long initialDate, long minDate, long maxDate) {
        show(fm, initialDate, minDate, maxDate, DatePickerDialogFragment.class.getName());
    }

    public static void show(@NonNull FragmentManager fm, long initialDate, long minDate,
                            long maxDate, @NonNull String tag) {
        Bundle args = new Bundle();
        args.putLong(ARG_MIN_DATE, minDate < 0 ? 0 : minDate);
        args.putLong(ARG_MAX_DATE, maxDate < 0 ? 0 : maxDate);
        args.putLong(ARG_INITIAL_DATE, initialDate < 0 ? 0 : initialDate);
        DatePickerDialogFragment dialog = new DatePickerDialogFragment();
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
        initArguments();

        // set initial date to current time if not set
        if (mInitialDate == 0) {
            final Calendar calendar = Calendar.getInstance();
            mInitialDate = calendar.getTimeInMillis();
        }

        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mInitialDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
        if (mMinDate > 0) {
            dialog.getDatePicker().setMinDate(mMinDate);
        }
        if (mMaxDate > 0) {
            dialog.getDatePicker().setMaxDate(mMaxDate);
        }

        return dialog;
    }

    private void initArguments() {
        final Bundle args = getArguments();
        if (args != null) {
            mMinDate = args.getLong(ARG_MIN_DATE);
            mMaxDate = args.getLong(ARG_MAX_DATE);
            mInitialDate = args.getLong(ARG_INITIAL_DATE);
        }
    }

    private void resolveCallback(Activity activity) {
        if (getParentFragment() instanceof Callback) {
            mCallback = (Callback) getParentFragment();
        } else if (activity instanceof Callback) {
            mCallback = (Callback) activity;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        if (mCallback != null) {
            mCallback.onDateSet(year, month, day);
        }
    }

    public interface Callback {
        void onDateSet(int year, int month, int day);
    }

}