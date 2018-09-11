package com.bankex.pay.ui.startscreen

import android.content.Context
import android.content.Intent
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bankex.pay.R
import com.bankex.pay.ui.base.activity.BaseActivity
import com.bankex.pay.ui.importwallet.ImportWalletActivity
import com.bankex.pay.ui.main.MainActivity
import kotlinx.android.synthetic.main.ac_start.*

class StartActivity : BaseActivity(), StartView {

    @InjectPresenter
    internal lateinit var presenter: StartPresenter

    override fun getLayout(): Int = R.layout.ac_start

    override fun createWallet() {
        startActivity(MainActivity.makeIntent(this))
    }

    override fun importWallet() {
        startActivity(ImportWalletActivity.makeIntent(this))
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
