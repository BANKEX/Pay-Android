package com.elegion.android.bankex.ui.startscreen

import android.content.Context
import android.content.Intent
import com.arellomobile.mvp.presenter.InjectPresenter
import com.elegion.android.bankex.R
import com.elegion.android.bankex.ui.base.activity.BaseActivity
import com.elegion.android.bankex.ui.createwallet.CreateWalletActivity
import com.elegion.android.bankex.ui.importwallet.ImportWalletActivity
import com.elegion.android.bankex.ui.scanqr.ScanQRActivity
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.ac_start.*

class StartActivity : BaseActivity(), StartView {

    @InjectPresenter
    internal lateinit var presenter: StartPresenter

    override fun getLayout(): Int = R.layout.ac_start

    override fun createWallet() {
        startActivity(CreateWalletActivity.makeIntent(this))
        supportFinishAfterTransition()
    }

    override fun importWallet() {
        startActivity(ImportWalletActivity.makeIntent(this))
        supportFinishAfterTransition()
    }

    companion object {
        fun makeIntent(context: Context) = Intent(context, StartActivity::class.java)
    }

    override fun onResume() {
        super.onResume()
        createWallet.setOnClickListener { presenter.createWallet() }
        importWallet.setOnClickListener { presenter.importWallet() }
    }

    override fun onPause() {
        createWallet.setOnClickListener { null }
        importWallet.setOnClickListener { null }
        super.onPause()
    }
}
