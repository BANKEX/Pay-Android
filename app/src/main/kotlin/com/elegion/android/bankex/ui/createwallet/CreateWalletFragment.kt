package com.elegion.android.bankex.ui.createwallet

import android.view.View
import android.widget.Button
import android.widget.EditText
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.elegion.android.bankex.R
import com.elegion.android.bankex.data.Repository
import com.elegion.android.bankex.ui.base.fragment.BaseNoInternetFragment

class CreateWalletFragment : BaseNoInternetFragment(), CreateWalletView {

    @InjectPresenter
    internal lateinit var mPresenterCreate: CreateWalletPresenter

    @ProvidePresenter
    internal fun providePresenter(): CreateWalletPresenter = CreateWalletPresenter(Repository.get(activity!!),this,"erferf")

    private var mGenerateWalletButton: Button? = null

    private var mPassword: EditText? = null

    private var mPasswordConfirmation: EditText? = null

    override fun getViews(): Array<View> = arrayOf()

    override fun getLayout(): Int = R.layout.fr_wallet


    override fun onResume() {
        super.onResume()
        mGenerateWalletButton!!.setOnClickListener { mPresenterCreate.generateWallet("") }

     /*   mPasswordConfirmation = mPassword.addTextChangedListener {
            onTextChanged { text, _, _, _ -> mPresenterCreate.setEmail(text.toString()) }
        }
        mPassword = mPassword.addTextChangedListener {
            onTextChanged { text, _, _, _ -> mPresenterCreate.setPassword(text.toString()) }
        }*/
    }

    override fun onPause() {
        super.onPause()
        mGenerateWalletButton!!.setOnClickListener(null)
/*        mPassword.removeTextChangedListener(loginEmailTexTWatcher)
        mPasswordConfirmation.removeTextChangedListener(loginPasswordTexTWatcher)*/
    }


    override fun tryAgain() {

    }

    override fun showGeneratedWallet(walletAddress: String) {

    }

    companion object {
        fun newInstance() = CreateWalletFragment()
    }
}