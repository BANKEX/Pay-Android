package com.elegion.android.ui.dialog;


import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.elegion.android.R;

import java.util.Calendar;

/**
 * @author mikhail.funikov@e-legion.com on 08/06/2017.
 */
public class DateTimePickerDialogFragment extends DialogFragment implements View.OnClickListener {

    private static final String ARG_MIN_DATE = "ARG_MIN_DATE";
    private static final String ARG_MAX_DATE = "ARG_MAX_DATE";
    private static final String ARG_INITIAL_DATE = "ARG_INITIAL_DATE";

    private long mInitialDate;
    private long mMinDate;
    private long mMaxDate;

    private Callback mCallback;

    //TODO : need to save last date and time if this widgets will be destroy
    private DatePicker mDatePicker;
    private TimePicker mTimePicker;

    public static void show(@NonNull FragmentManager fm, long initialDate, long minDate, long maxDate) {
        show(fm, initialDate, minDate, maxDate, DateTimePickerDialogFragment.class.getName());
    }

    public static void show(@NonNull FragmentManager fm, long initialDate, long minDate,
                            long maxDate, @NonNull String tag) {
        Bundle args = new Bundle();
        args.putLong(ARG_MIN_DATE, minDate < 0 ? 0 : minDate);
        args.putLong(ARG_MAX_DATE, maxDate < 0 ? 0 : maxDate);
        args.putLong(ARG_INITIAL_DATE, initialDate < 0 ? 0 : initialDate);
        DateTimePickerDialogFragment dialog = new DateTimePickerDialogFragment();
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

        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        View dialogView = createDialogView();
        dialogView.findViewById(R.id.date_time_ok).setOnClickListener(this);
        alertDialog.setView(dialogView);

        return alertDialog;
    }

    private void initArguments() {
        final Bundle args = getArguments();
        if (args != null) {
            mMinDate = args.getLong(ARG_MIN_DATE);
            mMaxDate = args.getLong(ARG_MAX_DATE);
            mInitialDate = args.getLong(ARG_INITIAL_DATE);
        }
    }

    @Override
    public void onClick(View v) {
        if (mCallback != null) {
            mCallback.onDateTimeSet(constructCalendar(), getTag());
        }
        dismiss();
    }

    @SuppressWarnings("deprecation")
    private Calendar constructCalendar() {
        Calendar calendar = Calendar.getInstance();
        int year = mDatePicker == null ? calendar.get(Calendar.YEAR) : mDatePicker.getYear();
        int month = mDatePicker == null ? calendar.get(Calendar.MONTH) : mDatePicker.getMonth();
        int day = mDatePicker == null ? calendar.get(Calendar.DAY_OF_MONTH) : mDatePicker.getDayOfMonth();

        int hour;
        int minute;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            hour = mTimePicker == null ? calendar.get(Calendar.HOUR_OF_DAY) : mTimePicker.getCurrentHour();
            minute = mTimePicker == null ? calendar.get(Calendar.MINUTE) : mTimePicker.getCurrentMinute();
        } else {
            hour = mTimePicker == null ? calendar.get(Calendar.HOUR_OF_DAY) : mTimePicker.getHour();
            minute = mTimePicker == null ? calendar.get(Calendar.MINUTE) : mTimePicker.getMinute();
        }

        calendar.clear();
        calendar.set(year, month, day, hour, minute);
        return calendar;
    }

    private void resolveCallback(Activity activity) {
        if (getParentFragment() instanceof Callback) {
            mCallback = (Callback) getParentFragment();
        } else if (activity instanceof Callback) {
            mCallback = (Callback) activity;
        }
    }

    private View createDialogView() {
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
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        final View dialogView = View.inflate(getActivity(), R.layout.w_date_time_picker, null);
        ViewPager pager = (ViewPager) dialogView.findViewById(R.id.date_time_view_pager);
        pager.setAdapter(new DateTimeAdapter(year, month, day, hourOfDay, minute));
        TabLayout tabLayout = (TabLayout) dialogView.findViewById(R.id.date_time_tab_layout);
        tabLayout.setupWithViewPager(pager);

        return dialogView;
    }

    private class DateTimeAdapter extends PagerAdapter {
        private final int mYear;
        private final int mMonth;
        private final int mDay;
        private final int mHourOfDay;
        private final int mMinute;

        public DateTimeAdapter(int year, int month, int day, int hourOfDay, int minute) {
            mYear = year;
            mMonth = month;
            mDay = day;
            mHourOfDay = hourOfDay;
            mMinute = minute;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return getString(R.string.date);
            } else if (position == 1) {
                return getString(R.string.time);
            } else {
                return super.getPageTitle(position);
            }
        }

        @Override
        public int getItemPosition(Object object) {
            if (object instanceof DatePicker) {
                return 0;
            } else if (object instanceof TimePicker) {
                return 1;
            } else {
                return super.getItemPosition(object);
            }
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @SuppressWarnings("deprecation")
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View item = null;
            if (position == 0) {
                DatePicker dp = new DatePicker(getActivity());
                dp.init(mYear, mMonth, mDay, null);
                if (mMinDate > 0) {
                    dp.setMinDate(mMinDate);
                }
                if (mMaxDate > 0) {
                    dp.setMaxDate(mMaxDate);
                }

                dp.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                item = mDatePicker = dp;
            } else if (position == 1) {
                TimePicker tp = new TimePicker(getActivity());
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    tp.setCurrentHour(mHourOfDay);
                    tp.setCurrentMinute(mMinute);
                } else {
                    tp.setHour(mHourOfDay);
                    tp.setMinute(mMinute);
                }
                tp.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                item = mTimePicker = tp;
            }

            if (item != null) {
                container.addView(item);
            }

            return item;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return object == view;
        }
    }

    public interface Callback {
        void onDateTimeSet(Calendar dateTime, String tag);
    }
}
