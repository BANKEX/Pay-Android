package com.elegion.android.bankex.ui.dialog

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
import com.elegion.android.bankex.R
import kotlinx.android.synthetic.main.fr_message_dialog.*

class MessageDialog : DialogFragment() {

    private var callback: Callback? = null
    private var titleText: String? = null
    private var messageText: String? = null
    private var confirmText: String? = null
    private var cancelText: String? = null

    private val primaryButtonListener = View.OnClickListener {
        callback?.apply {
            onConfirmButtonClick()
        }
        dialog.dismiss()
    }

    private val secondaryButtonListener = View.OnClickListener {
        callback?.apply {
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
            callback = parentFragment as Callback?
        } else if (context is Callback) {
            callback = context
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fr_message_dialog, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArguments()

        // set texts
        bindTextOrHide(title, titleText)
        bindTextOrHide(message, messageText)
        bindTextOrHide(primaryAction, confirmText)
        bindTextOrHide(secondaryAction, cancelText)
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
        titleText = getString(ARG_TITLE)
        messageText = getString(ARG_MESSAGE)
        confirmText = getString(ARG_CONFIRM_BUTTON)
        cancelText = getString(ARG_CANCEL_BUTTON)
    }

    override fun onResume() {
        super.onResume()
        val width = resources.getDimensionPixelSize(R.dimen.dialog_message_width)
        changeWindowSizes(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        primaryAction.setOnClickListener(primaryButtonListener)
        secondaryAction.setOnClickListener(secondaryButtonListener)
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

    class Params(
        val title: String?,
        val message: String?,
        val confirm: String?,
        val cancel: String?
    )

    companion object {
        private val ARG_TITLE = "ARG_TITLE"
        private val ARG_MESSAGE = "ARG_MESSAGE"
        private val ARG_CONFIRM_BUTTON = "ARG_CONFIRM_BUTTON"
        private val ARG_CANCEL_BUTTON = "ARG_CANCEL_BUTTON"

        @JvmOverloads
        fun show(fm: FragmentManager, params: Params, tag: String = MessageDialog::class.java.name) {
            val dialog = MessageDialog()
            dialog.arguments = Bundle().apply {
                putString(ARG_TITLE, params.title)
                putString(ARG_MESSAGE, params.message)
                putString(ARG_CONFIRM_BUTTON, params.confirm)
                putString(ARG_CANCEL_BUTTON, params.cancel)
            }
            dialog.show(fm, tag)
        }
    }
}