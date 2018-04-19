package com.elegion.android.template.ui.login

import android.text.TextWatcher
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.elegion.android.template.R
import com.elegion.android.template.data.Repository
import com.elegion.android.template.extension.android.widget.addTextChangedListener
import com.elegion.android.template.ui.base.fragment.BaseNoInternetFragment
import com.elegion.android.template.ui.features.FeaturesActivity
import kotlinx.android.synthetic.main.fr_login.*

class LoginFragment : BaseNoInternetFragment(), LoginView {
    @InjectPresenter
    internal lateinit var presenter: LoginPresenter

    private lateinit var loginEmailTexTWatcher: TextWatcher
    private lateinit var loginPasswordTexTWatcher: TextWatcher

    @ProvidePresenter
    internal fun providePresenter(): LoginPresenter = LoginPresenter(Repository.get(activity!!))

    override fun getLayout(): Int = R.layout.fr_login

    override fun onResume() {
        super.onResume()
        loginBtn.setOnClickListener { presenter.login() }
        loginLetMeInBtn.setOnClickListener { presenter.letMeIn() }
        loginEmailTexTWatcher = loginEmail.addTextChangedListener {
            onTextChanged { text, _, _, _ -> presenter.setEmail(text.toString()) }
        }
        loginPasswordTexTWatcher = loginPassword.addTextChangedListener {
            onTextChanged { text, _, _, _ -> presenter.setPassword(text.toString()) }
        }
    }

    override fun onPause() {
        super.onPause()
        loginBtn.setOnClickListener(null)
        loginEmail.removeTextChangedListener(loginEmailTexTWatcher)
        loginPassword.removeTextChangedListener(loginPasswordTexTWatcher)
    }

    override fun getViews(): Array<View> = arrayOf(loginTopLayout)

    override fun tryAgain() = presenter.login()

    override fun loginSuccessful() {
        startActivity(FeaturesActivity.makeIntent(activity!!))
        activity?.supportFinishAfterTransition()
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}
