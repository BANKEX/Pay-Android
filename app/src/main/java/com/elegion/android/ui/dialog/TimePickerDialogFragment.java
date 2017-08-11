package com.elegion.android.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * @author Mike
 */
public class TimePickerDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private static final String ARG_INITIAL_TIME = "ARG_INITIAL_TIME";

    private long mInitialDate;
    private Callback mCallback;

    public static void show(@NonNull FragmentManager fm, long initialDate) {
        show(fm, initialDate, TimePickerDialogFragment.class.getName());
    }

    public static void show(@NonNull FragmentManager fm, long initialDate, @NonNull String tag) {
        Bundle args = new Bundle();
        args.putLong(ARG_INITIAL_TIME, initialDate < 0 ? 0 : initialDate);
        TimePickerDialogFragment dialog = new TimePickerDialogFragment();
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
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        final Context context = getActivity();
        return new TimePickerDialog(context, this, hourOfDay, minute, DateFormat.is24HourFormat(context));
    }

    private void initArguments() {
        final Bundle args = getArguments();
        if (args != null) {
            mInitialDate = args.getLong(ARG_INITIAL_TIME);
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
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (mCallback != null) {
            mCallback.onTimeSet(hourOfDay, minute);
        }
    }

    public interface Callback {
        void onTimeSet(int hourOfDay, int minute);
    }

}