package com.elegion.android.template.ui.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import com.elegion.android.template.R
import kotlinx.android.synthetic.main.fr_message_dialog.*

class MessageDialog : DialogFragment() {

    private var mCallback: Callback? = null
    private var mTitleText: String? = null
    private var mMessageText: String? = null
    private var mConfirmText: String? = null
    private var mCancelText: String? = null

    private val mPrimaryButtonListener = View.OnClickListener {
        mCallback?.apply {
            onConfirmButtonClick()
        }
        dialog.dismiss()
    }

    private val mSecondaryButtonListener = View.OnClickListener {
        mCallback?.apply {
            onCancelButtonClick()
        }
        dialog.dismiss()
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        resolveCallback(activity)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        resolveCallback(context)
    }

    private fun resolveCallback(context: Context?) {
        if (parentFragment is Callback) {
            mCallback = parentFragment as Callback?
        } else if (context is Callback) {
            mCallback = context
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fr_message_dialog, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArguments()

        // set texts
        bindTextOrHide(title, mTitleText)
        bindTextOrHide(message, mMessageText)
        bindTextOrHide(primaryAction, mConfirmText)
        bindTextOrHide(secondaryAction, mCancelText)
    }

    private fun bindTextOrHide(textView: TextView?, text: String?) {
        if (!TextUtils.isEmpty(text)) {
            textView?.let {
                it.visibility = View.VISIBLE
                it.text = text
            }
        } else {
            textView?.visibility = View.GONE
        }
    }

    private fun initArguments() = arguments?.apply {
        mTitleText = getString(ARG_TITLE)
        mMessageText = getString(ARG_MESSAGE)
        mConfirmText = getString(ARG_CONFIRM_BUTTON)
        mCancelText = getString(ARG_CANCEL_BUTTON)
    }

    override fun onResume() {
        super.onResume()
        val width = resources.getDimensionPixelSize(R.dimen.dialog_message_width)
        changeWindowSizes(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        primaryAction.setOnClickListener(mPrimaryButtonListener)
        secondaryAction.setOnClickListener(mSecondaryButtonListener)
    }

    override fun onPause() {
        super.onPause()
        primaryAction.setOnClickListener(null)
        secondaryAction.setOnClickListener(null)
    }

    private fun changeWindowSizes(width: Int, height: Int) = dialog.window?.setLayout(width, height)

    override fun getTheme(): Int = R.style.AppTheme_Dialog

    interface Callback {
        fun onConfirmButtonClick()

        fun onCancelButtonClick()
    }

    companion object {
        private val ARG_TITLE = "ARG_TITLE"
        private val ARG_MESSAGE = "ARG_MESSAGE"
        private val ARG_CONFIRM_BUTTON = "ARG_CONFIRM_BUTTON"
        private val ARG_CANCEL_BUTTON = "ARG_CANCEL_BUTTON"

        @JvmOverloads
        fun show(fm: FragmentManager,
                 title: String?,
                 message: String?,
                 confirm: String?,
                 cancel: String?,
                 tag: String = MessageDialog::class.java.name) {
            val dialog = MessageDialog()
            dialog.arguments = Bundle().apply {
                putString(ARG_TITLE, title)
                putString(ARG_MESSAGE, message)
                putString(ARG_CONFIRM_BUTTON, confirm)
                putString(ARG_CANCEL_BUTTON, cancel)
            }
            dialog.show(fm, tag)
        }
    }
}