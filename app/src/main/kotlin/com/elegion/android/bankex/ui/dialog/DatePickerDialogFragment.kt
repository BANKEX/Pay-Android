package com.elegion.android.bankex.ui.dialog

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.widget.DatePicker
import java.util.Calendar

class DatePickerDialogFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {
    private var minDate: Long = 0
    private var maxDate: Long = 0
    private var initialDate: Long = 0
    private var callback: Callback? = null

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        resolveCallback(activity)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        initArguments()

        // set initial date to current time if not set
        if (initialDate == 0L) {
            initialDate = Calendar.getInstance().timeInMillis
        }

        val dialog = Calendar.getInstance().run {
            timeInMillis = initialDate
            DatePickerDialog(
                activity, this@DatePickerDialogFragment, get(Calendar.YEAR),
                get(Calendar.MONTH), get(Calendar.DAY_OF_MONTH)
            )
        }
        if (minDate > 0) {
            dialog.datePicker.minDate = minDate
        }
        if (maxDate > 0) {
            dialog.datePicker.maxDate = maxDate
        }
        // remove title from the dialog
        dialog.setTitle(null)

        return dialog
    }

    private fun initArguments() = arguments?.apply {
        minDate = getLong(ARG_MIN_DATE)
        maxDate = getLong(ARG_MAX_DATE)
        initialDate = getLong(ARG_INITIAL_DATE)
    }

    private fun resolveCallback(activity: Activity?) {
        if (parentFragment is Callback) {
            callback = parentFragment as Callback?
        } else if (activity is Callback) {
            callback = activity
        }
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        callback?.apply {
            onDateSet(year, month, day)
        }
    }

    interface Callback {
        fun onDateSet(year: Int, month: Int, day: Int)
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
            tag: String = DatePickerDialogFragment::class.java.name
        ) {
            val dialog = DatePickerDialogFragment()
            dialog.arguments = Bundle().apply {
                putLong(ARG_MIN_DATE, if (minDate < 0) 0 else minDate)
                putLong(ARG_MAX_DATE, if (maxDate < 0) 0 else maxDate)
                putLong(ARG_INITIAL_DATE, if (initialDate < 0) 0 else initialDate)
            }
            dialog.show(fm, tag)
        }
    }
}