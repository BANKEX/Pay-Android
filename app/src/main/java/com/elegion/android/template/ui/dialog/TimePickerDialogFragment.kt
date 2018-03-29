package com.elegion.android.template.ui.dialog

import android.app.Activity
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.text.format.DateFormat
import android.widget.TimePicker
import java.util.*

class TimePickerDialogFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    private var mInitialDate: Long = 0
    private var mCallback: Callback? = null

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        resolveCallback(activity)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        initArguments()

        // set initial date to current time if not set
        if (mInitialDate == 0L) {
            val calendar = Calendar.getInstance()
            mInitialDate = calendar.timeInMillis
        }

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = mInitialDate
        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        return TimePickerDialog(activity, this, hourOfDay, minute, DateFormat.is24HourFormat(context))
    }

    private fun initArguments() = arguments?.apply {
        mInitialDate = getLong(ARG_INITIAL_TIME)
    }

    private fun resolveCallback(activity: Activity?) {
        if (parentFragment is Callback) {
            mCallback = parentFragment as Callback?
        } else if (activity is Callback) {
            mCallback = activity
        }
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        mCallback?.apply {
            onTimeSet(hourOfDay, minute)
        }
    }

    interface Callback {
        fun onTimeSet(hourOfDay: Int, minute: Int)
    }

    companion object {
        private val ARG_INITIAL_TIME = "ARG_INITIAL_TIME"

        @JvmOverloads
        fun show(fm: FragmentManager, initialDate: Long, tag: String = TimePickerDialogFragment::class.java.name) {
            val args = Bundle()
            args.putLong(ARG_INITIAL_TIME, if (initialDate < 0) 0 else initialDate)
            val dialog = TimePickerDialogFragment()
            dialog.arguments = args
            dialog.show(fm, tag)
        }
    }

}