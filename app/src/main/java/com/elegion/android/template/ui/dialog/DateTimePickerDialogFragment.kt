package com.elegion.android.template.ui.dialog

import android.app.Activity
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.PagerAdapter
import android.support.v7.app.AlertDialog
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import com.elegion.android.template.R
import kotlinx.android.synthetic.main.w_date_time_picker.*
import java.util.*

class DateTimePickerDialogFragment : DialogFragment(), View.OnClickListener {

    private var mInitialDate: Long = 0
    private var mMinDate: Long = 0
    private var mMaxDate: Long = 0

    private var mCallback: Callback? = null

    //TODO : need to save last date and time if this widgets will be destroy
    private var mDatePicker: DatePicker? = null
    private var mTimePicker: TimePicker? = null

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        resolveCallback(activity)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        initArguments()

        val alertDialog = AlertDialog.Builder(activity!!).create()
        val dialogView = createDialogView()
        dateTimeOk.setOnClickListener(this)
        alertDialog.setView(dialogView)

        return alertDialog
    }

    private fun initArguments() = arguments?.apply {
        mMinDate = getLong(ARG_MIN_DATE)
        mMaxDate = getLong(ARG_MAX_DATE)
        mInitialDate = getLong(ARG_INITIAL_DATE)
    }

    override fun onClick(v: View) {
        mCallback?.apply { onDateTimeSet(constructCalendar(), tag) }
        dismiss()
    }

    private fun constructCalendar(): Calendar {
        val calendar = Calendar.getInstance()
        val year = if (mDatePicker == null) calendar.get(Calendar.YEAR) else mDatePicker!!.year
        val month = if (mDatePicker == null) calendar.get(Calendar.MONTH) else mDatePicker!!.month
        val day = if (mDatePicker == null) calendar.get(Calendar.DAY_OF_MONTH) else mDatePicker!!.dayOfMonth

        val hour: Int
        val minute: Int
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            hour = if (mTimePicker == null) calendar.get(Calendar.HOUR_OF_DAY) else mTimePicker!!.currentHour
            minute = if (mTimePicker == null) calendar.get(Calendar.MINUTE) else mTimePicker!!.currentMinute
        } else {
            hour = if (mTimePicker == null) calendar.get(Calendar.HOUR_OF_DAY) else mTimePicker!!.hour
            minute = if (mTimePicker == null) calendar.get(Calendar.MINUTE) else mTimePicker!!.minute
        }

        calendar.clear()
        calendar.set(year, month, day, hour, minute)
        return calendar
    }

    private fun resolveCallback(activity: Activity?) {
        if (parentFragment is Callback) {
            mCallback = parentFragment as Callback?
        } else if (activity is Callback) {
            mCallback = activity
        }
    }

    private fun createDialogView(): View {
        // set initial date to current time if not set
        if (mInitialDate == 0L) {
            mInitialDate = Calendar.getInstance().timeInMillis
        }

        val dialogView = View.inflate(activity, R.layout.w_date_time_picker, null)
        with(Calendar.getInstance()) {
            timeInMillis = mInitialDate
            dateTimeViewPager.adapter = DateTimeAdapter(get(Calendar.YEAR),
                    get(Calendar.MONTH), get(Calendar.DAY_OF_MONTH),
                    get(Calendar.HOUR_OF_DAY), get(Calendar.MINUTE))
        }
        dateTimeTabLayout.setupWithViewPager(dateTimeViewPager)

        return dialogView
    }

    private inner class DateTimeAdapter(private val mYear: Int, private val mMonth: Int, private val mDay: Int,
                                        private val mHourOfDay: Int, private val mMinute: Int) : PagerAdapter() {

        override fun getPageTitle(position: Int): CharSequence? = when (position) {
            0 -> getString(R.string.date)
            1 -> getString(R.string.time)
            else -> super.getPageTitle(position)
        }

        override fun getItemPosition(obj: Any): Int = when (obj) {
            is DatePicker -> 0
            is TimePicker -> 1
            else -> super.getItemPosition(obj)
        }

        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) = container.removeView(obj as View)

        override fun instantiateItem(container: ViewGroup, position: Int): Any = when (position) {
            0 -> {
                val dp = DatePicker(activity)
                dp.init(mYear, mMonth, mDay, null)
                if (mMinDate > 0) {
                    dp.minDate = mMinDate
                }
                if (mMaxDate > 0) {
                    dp.maxDate = mMaxDate
                }

                dp.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                mDatePicker = dp
                mDatePicker as Any
            }
            1 -> {
                val tp = TimePicker(activity)
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    tp.currentHour = mHourOfDay
                    tp.currentMinute = mMinute
                } else {
                    tp.hour = mHourOfDay
                    tp.minute = mMinute
                }
                tp.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                mTimePicker = tp
                mTimePicker as Any
            }
            else -> throw IllegalStateException("DateTimePickerDialogFragment adapter may have only 2 tabs trying to create the third one")
        }

        override fun getCount(): Int {
            return 2
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean = obj === view
    }

    interface Callback {
        fun onDateTimeSet(dateTime: Calendar, tag: String?)
    }

    companion object {

        private const val ARG_MIN_DATE = "ARG_MIN_DATE"
        private const val ARG_MAX_DATE = "ARG_MAX_DATE"
        private const val ARG_INITIAL_DATE = "ARG_INITIAL_DATE"

        @JvmOverloads
        fun show(fm: FragmentManager, initialDate: Long, minDate: Long,
                 maxDate: Long, tag: String = DateTimePickerDialogFragment::class.java.name) {
            val dialog = DateTimePickerDialogFragment()
            dialog.arguments = Bundle().apply {
                putLong(ARG_MIN_DATE, if (minDate < 0) 0 else minDate)
                putLong(ARG_MAX_DATE, if (maxDate < 0) 0 else maxDate)
                putLong(ARG_INITIAL_DATE, if (initialDate < 0) 0 else initialDate)
            }
            dialog.show(fm, tag)
        }
    }
}
