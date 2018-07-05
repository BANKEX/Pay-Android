package com.elegion.android.bankex.ui.createwallet

import android.Manifest
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.text.TextWatcher
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.elegion.android.bankex.R
import com.elegion.android.bankex.data.Repository
import com.elegion.android.bankex.extension.android.widget.addTextChangedListener
import com.elegion.android.bankex.ui.attention.attentionwarning.AttentionActivity
import com.elegion.android.bankex.ui.base.fragment.BaseNoInternetFragment
import kotlinx.android.synthetic.main.w_top_navigation.*
import kotlinx.android.synthetic.main.w_wallet_content.*
import timber.log.Timber

class CreateWalletFragment : BaseNoInternetFragment(), CreateWalletView {

    @InjectPresenter
    internal lateinit var presenterCreate: CreateWalletPresenter

    private lateinit var passwordTexTWatcher: TextWatcher
    private lateinit var confirmpasswordTexTWatcher: TextWatcher

    @ProvidePresenter
    internal fun providePresenter(): CreateWalletPresenter = CreateWalletPresenter(Repository.get(activity!!))

    override fun getViews(): Array<View> = arrayOf(content)

    fun tabList(): Array<View> = arrayOf(passphraseLayuot)

    override fun getLayout(): Int = R.layout.fr_create_wallet

    override fun onResume() {
        super.onResume()
        generateWalletButton.setOnClickListener {
            val permissionCheck = ContextCompat.checkSelfPermission(activity!!,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        activity!!,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        REQUEST_PERMISSION_WRITE_STORAGE)
            } else presenterCreate.generateWallet()
        }

        passwordTexTWatcher = password.addTextChangedListener {
            onTextChanged { text, _, _, _ -> presenterCreate.password = text.toString() }
        }
        confirmpasswordTexTWatcher = confirmPassword.addTextChangedListener {
            onTextChanged { text, _, _, _ -> presenterCreate.confPassword = text.toString() }
        }
        privateKey.setOnClickListener({ onTabSelected(privateKey) })
        passphrase.setOnClickListener({ onTabSelected(passphrase) })
    }

    override fun onPause() {
        super.onPause()
        generateWalletButton!!.setOnClickListener(null)
        password.removeTextChangedListener(passwordTexTWatcher)
        confirmPassword.removeTextChangedListener(confirmpasswordTexTWatcher)
    }


    override fun tryAgain() = presenterCreate.generateWallet()

    override fun showButtonEnabled(enabled: Boolean) {
        generateWalletButton.isEnabled = enabled
    }

    override fun showGeneratedWallet(walletAddress: String) {
        startActivity(AttentionActivity.makeIntent(activity!!))
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_PERMISSION_WRITE_STORAGE -> {
                if (grantResults.size == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    activity!!.finish()
                } else {
                    presenterCreate!!.generateWallet()
                }
            }
        }
    }

    companion object {
        fun newInstance() = CreateWalletFragment()
    }

    private val REQUEST_PERMISSION_WRITE_STORAGE = 0

    fun onTabSelected(v: View) {
        var tabList = tabList()
        val isSelected = v.isSelected
        //supportInvalidateOptionsMenu()
        when (v.id) {
            R.id.privateKey -> {
                Timber.i("click best_stocks tab")
                if (!isSelected) {

                }
            }
            R.id.passphrase -> if (!isSelected) {

            }
        }
        if (!isSelected) {
            for (tab in tabList) {
                tab.setSelected(tab === v)
            }
        }
    }
}