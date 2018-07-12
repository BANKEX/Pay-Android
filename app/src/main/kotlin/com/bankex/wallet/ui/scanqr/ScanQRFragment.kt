package com.bankex.wallet.ui.scanqr

import android.view.View
import com.bankex.wallet.R
import com.bankex.wallet.ui.base.fragment.BaseNoInternetFragment
import kotlinx.android.synthetic.main.w_wallet_content.*


/**
 * @author Denis Anisimov.
 */
class ScanQRFragment : BaseNoInternetFragment(), ScanQRView {
    override fun tryAgain() {

    }

    companion object {
        fun newInstance(): ScanQRFragment {
            return ScanQRFragment()
        }
    }

    override fun getViews(): Array<View> = arrayOf(content)

    override fun getLayout(): Int = R.layout.fr_scan_qr
}