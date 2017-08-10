package com.elegion.android.ui.dialog;


import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
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
//TODO : need to take "current date and time" argument
public class DateTimePickerDialogFragment extends DialogFragment implements View.OnClickListener {

    private static final String KEY_MIN_DATE = "key_min_date";
    private static final String KEY_MAX_DATE = "key_max_date";

    private long mMinDate;
    private long mMaxDate;

    private Callback mCallback;

    //TODO : need to save last date and time if this widgets will be destroy
    private DatePicker mDatePicker;
    private TimePicker mTimePicker;

    public static DateTimePickerDialogFragment newInstance(long minDate, long maxDate) {
        Bundle args = new Bundle();
        args.putLong(KEY_MIN_DATE, minDate < 0 ? 0 : minDate);
        args.putLong(KEY_MAX_DATE, maxDate < 0 ? 0 : maxDate);

        DateTimePickerDialogFragment fragment = new DateTimePickerDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static DateTimePickerDialogFragment newInstance() {
        return newInstance(0, 0);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMinDate = getArguments().getLong(KEY_MIN_DATE);
        mMaxDate = getArguments().getLong(KEY_MAX_DATE);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        resolveCallback(activity);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        View dialogView = createDialogView();
        dialogView.findViewById(R.id.date_time_ok).setOnClickListener(this);
        alertDialog.setView(dialogView);

        return alertDialog;
    }

    @Override
    public void onClick(View v) {
        if (mCallback != null) {
            mCallback.onDateTimeSet(constructCalendar(), getTag());
        }
        dismiss();
    }

    private Calendar constructCalendar() {
        Calendar calendar = Calendar.getInstance();
        int year = mDatePicker == null ? calendar.get(Calendar.YEAR) : mDatePicker.getYear();
        int month = mDatePicker == null ? calendar.get(Calendar.MONTH) : mDatePicker.getMonth();
        int day = mDatePicker == null ? calendar.get(Calendar.DAY_OF_MONTH) : mDatePicker.getDayOfMonth();

        int hour = mTimePicker == null ? calendar.get(Calendar.HOUR_OF_DAY) : mTimePicker.getCurrentHour();
        int minute = mTimePicker == null ? calendar.get(Calendar.MINUTE) : mTimePicker.getCurrentMinute();

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
        final View dialogView = View.inflate(getActivity(), R.layout.w_date_time_picker, null);
        ViewPager pager = (ViewPager) dialogView.findViewById(R.id.date_time_view_pager);
        pager.setAdapter(new DateTimeAdapter());
        TabLayout tabLayout = (TabLayout) dialogView.findViewById(R.id.date_time_tab_layout);
        tabLayout.setupWithViewPager(pager);

        return dialogView;
    }

    private class DateTimeAdapter extends PagerAdapter {

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

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View item = null;
            if (position == 0) {
                DatePicker dp = new DatePicker(getActivity());
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
