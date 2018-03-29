package com.elegion.android.template.ui.dialog

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
    internal var mOptions: Int = 0
    private var mTitle: String? = null

    private var mCallback: Callback? = null

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        resolveCallback(activity)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        initArguments()
        if (!TextUtils.isEmpty(mTitle)) {
            builder.setTitle(mTitle)
        }
        builder.setItems(mOptions) { _, option ->
            mCallback?.onOptionSelected(option)
        }
        return builder.create()
    }

    private fun initArguments() = arguments?.apply {
        mOptions = getInt(ARG_OPTIONS)
        mTitle = getString(ARG_TITLE)
    }

    private fun resolveCallback(activity: Activity?) {
        if (parentFragment is Callback) {
            mCallback = parentFragment as Callback?
        } else if (activity is Callback) {
            mCallback = activity
        }
    }

    interface Callback {
        fun onOptionSelected(option: Int)
    }

    companion object {
        private val ARG_OPTIONS = "ARG_OPTIONS"
        private val ARG_TITLE = "ARG_TITLE"

        @JvmOverloads
        fun show(fm: FragmentManager, @ArrayRes options: Int,
                 title: String?, tag: String = SingleChoiceDialog::class.java.name) {
            val dialog = SingleChoiceDialog()
            dialog.arguments = Bundle().apply {
                putInt(ARG_OPTIONS, options)
                putString(ARG_TITLE, title)
            }
            dialog.show(fm, tag)
        }
    }

}