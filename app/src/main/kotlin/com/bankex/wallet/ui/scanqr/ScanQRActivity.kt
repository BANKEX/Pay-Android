package com.bankex.wallet.ui.scanqr

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import com.bankex.wallet.R
import com.bankex.wallet.ui.base.activity.BaseActivity
import com.journeyapps.barcodescanner.CaptureManager
import kotlinx.android.synthetic.main.fr_scan_qr.*


class ScanQRActivity : BaseActivity() {
    override fun getLayout(): Int = R.layout.fr_scan_qr

    private lateinit var capture: CaptureManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        capture = CaptureManager(this, barcodeScannerView)
        capture.initializeFromIntent(intent, savedInstanceState)
        capture.decode()
    }

    companion object {
        fun makeIntent(context: Context) = Intent(context, ScanQRActivity::class.java)
    }

    override fun onResume() {
        super.onResume()
        capture.onResume()
    }

    override fun onPause() {
        super.onPause()
        capture.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        capture.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        capture.onSaveInstanceState(outState)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return barcodeScannerView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event)
    }
}
