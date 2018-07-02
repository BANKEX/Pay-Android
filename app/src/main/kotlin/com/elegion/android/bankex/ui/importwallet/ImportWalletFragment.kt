package com.elegion.android.bankex.ui.importwallet

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.elegion.android.bankex.R
import com.elegion.android.bankex.ui.base.fragment.BaseNoInternetFragment
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.w_wallet_content.*

/**
 * @author Denis Anisimov.
 */
class ImportWalletFragment : BaseNoInternetFragment(), ImportWalletView {

    companion object {
        fun newInstance(): ImportWalletFragment {
            return ImportWalletFragment()
        }
    }

    @InjectPresenter
    internal lateinit var presenterImport: ImportWalletPresenter

    @ProvidePresenter
    internal fun providePresenter(): ImportWalletPresenter = ImportWalletPresenter()

    override fun getViews(): Array<View> = arrayOf(content)

    override fun getLayout(): Int = R.layout.fr_import_wallet

    override fun tryAgain() {

    }

    override fun showButtonEnabled(enabled: Boolean) {

    }

    override fun showScanQR() {
        IntentIntegrator(activity).initiateScan()
        //startActivity(ScanQRActivity.makeIntent(activity!!))
    }

    val CUSTOMIZED_REQUEST_CODE = 0x0000ffff

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode != CUSTOMIZED_REQUEST_CODE && requestCode != IntentIntegrator.REQUEST_CODE) {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data)
            return
        }
        when (requestCode) {
            CUSTOMIZED_REQUEST_CODE -> {
                Toast.makeText(activity, "REQUEST_CODE = $requestCode", Toast.LENGTH_LONG).show()
            }
            else -> {
            }
        }

        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if (result.contents == null) {
            Log.d("MainActivity", "Cancelled scan")
            Toast.makeText(activity, "Cancelled", Toast.LENGTH_LONG).show()
        } else {
            Log.d("MainActivity", "Scanned")
            Toast.makeText(activity, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
        }
    }
}