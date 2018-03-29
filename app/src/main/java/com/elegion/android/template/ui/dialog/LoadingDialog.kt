package com.elegion.android.template.ui.dialog

import android.app.Dialog
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.elegion.android.template.R
import com.elegion.android.template.ui.base.view.LoadingView
import java.lang.ref.Reference
import java.lang.ref.WeakReference
import java.util.concurrent.atomic.AtomicBoolean

class LoadingDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = ProgressDialog(activity, R.style.AppTheme_Dialog)
        val window = dialog.window
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP && window != null) {
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        dialog.setMessage(activity!!.getString(R.string.loading))
        return dialog
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isCancelable = false
    }

    private class HideTask internal constructor(fm: FragmentManager) : Runnable {

        private val mFmRef: Reference<FragmentManager>

        private var mAttempts = MAX_ATTEMPTS

        init {
            mFmRef = WeakReference(fm)
        }

        override fun run() {
            HANDLER.removeCallbacks(this)
            val fm = mFmRef.get()
            if (fm != null) {
                val dialog = fm.findFragmentByTag(LoadingDialog::class.java.name) as? LoadingDialog
                if (dialog != null) {
                    dialog.dismissAllowingStateLoss()
                } else if (--mAttempts >= 0) {
                    HANDLER.postDelayed(this, POST_DELAY.toLong())
                }
            }
        }

        companion object {
            private val MAX_ATTEMPTS = 10
            private val POST_DELAY = 300
        }
    }

    companion object {

        private val HANDLER = Handler(Looper.getMainLooper())

        fun view(fm: FragmentManager): LoadingView {
            return object : LoadingView {
                private val mWaitForHide = AtomicBoolean()

                override fun showLoadingIndicator() {
                    if (mWaitForHide.compareAndSet(false, true)) {
                        HANDLER.removeCallbacks(null)
                        if (fm.findFragmentByTag(LoadingDialog::class.java.name) == null) {
                            val loadingDialog = LoadingDialog()
                            loadingDialog.show(fm, LoadingDialog::class.java.name)
                        }
                    }
                }

                override fun hideLoadingIndicator() {
                    if (mWaitForHide.compareAndSet(true, false)) {
                        HANDLER.post(HideTask(fm))
                    }
                }
            }
        }

        fun view(fragment: Fragment): LoadingView = view(fragment.fragmentManager!!)
    }
}