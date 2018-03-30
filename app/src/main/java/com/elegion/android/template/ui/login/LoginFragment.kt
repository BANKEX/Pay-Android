package com.elegion.android.template.ui.login

import android.support.v4.app.Fragment
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.elegion.android.template.R
import com.elegion.android.template.data.Repository
import com.elegion.android.template.ui.base.fragment.BaseNoInternetFragment
import com.elegion.android.template.ui.features.FeaturesActivity
import com.elegion.android.template.util.SimpleTextWatcher
import kotlinx.android.synthetic.main.fr_login.*

class LoginFragment : BaseNoInternetFragment(), LoginView {
    @InjectPresenter
    internal
    lateinit var mPresenter: LoginPresenter
    val loginEmailTexTWatcher = object : SimpleTextWatcher() {
        override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
            mPresenter.setEmail(text.toString())
        }
    }
    val loginPasswordTexTWatcher = object : SimpleTextWatcher() {
        override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
            mPresenter.setPassword(text.toString())
        }
    }

    @ProvidePresenter
    internal fun providePresenter(): LoginPresenter = LoginPresenter(Repository.get(activity!!))

    override fun getLayout(): Int = R.layout.fr_login

    override fun onResume() {
        super.onResume()
        loginBtn.setOnClickListener { mPresenter.login() }
        loginLetMeInBtn.setOnClickListener { mPresenter.letMeIn() }
        loginEmail.addTextChangedListener(loginEmailTexTWatcher)
        loginPassword.addTextChangedListener(loginPasswordTexTWatcher)
    }

    override fun onPause() {
        super.onPause()
        loginBtn.setOnClickListener(null)
        loginEmail.removeTextChangedListener(loginEmailTexTWatcher)
        loginPassword.removeTextChangedListener(loginPasswordTexTWatcher)
    }

    override fun getViews(): Array<View> = arrayOf(loginTopLayout)

    override fun tryAgain() = mPresenter.login()

    override fun loginSuccessful() {
        startActivity(FeaturesActivity.makeIntent(activity!!))
        activity?.supportFinishAfterTransition()
    }

    companion object {
        fun newInstance(): Fragment {
            return LoginFragment()
        }
    }
}
