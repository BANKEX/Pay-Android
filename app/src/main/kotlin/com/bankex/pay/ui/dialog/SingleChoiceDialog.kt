package com.bankex.pay.ui.dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.annotation.ArrayRes
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.text.TextUtils

class SingleChoiceDialog : DialogFragment() {

    @ArrayRes
    internal var options: Int = 0
    private var title: String? = null
    private var callback: Callback? = null

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        resolveCallback(activity)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        initArguments()
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title)
        }
        builder.setItems(options) { _, option ->
            callback?.onOptionSelected(option)
        }
        return builder.create()
    }

    private fun initArguments() = arguments?.apply {
        options = getInt(ARG_OPTIONS)
        title = getString(ARG_TITLE)
    }

    private fun resolveCallback(activity: Activity?) {
        if (parentFragment is Callback) {
            callback = parentFragment as Callback?
        } else if (activity is Callback) {
            callback = activity
        }
    }

    interface Callback {
        fun onOptionSelected(option: Int)
    }

    companion object {
        private val ARG_OPTIONS = "ARG_OPTIONS"
        private val ARG_TITLE = "ARG_TITLE"

        @JvmOverloads
        fun show(
            fm: FragmentManager,
            @ArrayRes options: Int,
            title: String?,
            tag: String = SingleChoiceDialog::class.java.name
        ) {
            val dialog = SingleChoiceDialog()
            dialog.arguments = Bundle().apply {
                putInt(ARG_OPTIONS, options)
                putString(ARG_TITLE, title)
            }
            dialog.show(fm, tag)
        }
    }
}