package com.elegion.android.bankex.ui.dialog

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
import com.elegion.android.bankex.R
import kotlinx.android.synthetic.main.w_date_time_picker.*
import java.util.Calendar

class DateTimePickerDialogFragment : DialogFragment(), View.OnClickListener {

    private var initialDate: Long = 0
    private var minDate: Long = 0
    private var maxDate: Long = 0

    private var callback: Callback? = null

    //TODO : need to save last date and time if this widgets will be destroy
    private var datePicker: DatePicker? = null
    private var timePicker: TimePicker? = null

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
        minDate = getLong(ARG_MIN_DATE)
        maxDate = getLong(ARG_MAX_DATE)
        initialDate = getLong(ARG_INITIAL_DATE)
    }

    override fun onClick(v: View) {
        callback?.apply { onDateTimeSet(constructCalendar(), tag) }
        dismiss()
    }

    private fun constructCalendar(): Calendar {
        val calendar = Calendar.getInstance()
        removeSecondsAndMillis(calendar)
        updateDate(calendar)
        updateTime(calendar)
        return calendar
    }

    private fun removeSecondsAndMillis(calendar: Calendar) {
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
    }

    private fun updateDate(calendar: Calendar) {
        datePicker?.let {
            calendar.set(Calendar.YEAR, it.year)
            calendar.set(Calendar.MONTH, it.month)
            calendar.set(Calendar.DAY_OF_MONTH, it.dayOfMonth)
        }
    }

    private fun updateTime(calendar: Calendar) {
        timePicker?.let {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                calendar.set(Calendar.HOUR_OF_DAY, it.currentHour)
                calendar.set(Calendar.MINUTE, it.currentMinute)
            } else {
                calendar.set(Calendar.HOUR_OF_DAY, it.hour)
                calendar.set(Calendar.MINUTE, it.minute)
            }
        }
    }

    private fun resolveCallback(activity: Activity?) {
        if (parentFragment is Callback) {
            callback = parentFragment as Callback?
        } else if (activity is Callback) {
            callback = activity
        }
    }

    private fun createDialogView(): View {
        // set initial date to current time if not set
        if (initialDate == 0L) {
            initialDate = Calendar.getInstance().timeInMillis
        }

        val dialogView = View.inflate(activity, R.layout.w_date_time_picker, null)
        with(Calendar.getInstance()) {
            timeInMillis = initialDate
            dateTimeViewPager.adapter = DateTimeAdapter(
                get(Calendar.YEAR),
                get(Calendar.MONTH), get(Calendar.DAY_OF_MONTH),
                get(Calendar.HOUR_OF_DAY), get(Calendar.MINUTE)
            )
        }
        dateTimeTabLayout.setupWithViewPager(dateTimeViewPager)

        return dialogView
    }

    private inner class DateTimeAdapter(
        private val year: Int,
        private val month: Int,
        private val day: Int,
        private val hourOfDay: Int,
        private val minute: Int
    ) : PagerAdapter() {

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
                dp.init(year, month, day, null)
                if (minDate > 0) {
                    dp.minDate = minDate
                }
                if (maxDate > 0) {
                    dp.maxDate = maxDate
                }

                dp.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                datePicker = dp
                datePicker as Any
            }
            1 -> {
                val tp = TimePicker(activity)
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    tp.currentHour = hourOfDay
                    tp.currentMinute = minute
                } else {
                    tp.hour = hourOfDay
                    tp.minute = minute
                }
                tp.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                timePicker = tp
                timePicker as Any
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
        fun show(
            fm: FragmentManager,
            initialDate: Long,
            minDate: Long,
            maxDate: Long,
            tag: String = DateTimePickerDialogFragment::class.java.name
        ) {
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
