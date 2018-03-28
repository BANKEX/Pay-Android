package com.elegion.android.template.ui.splash

import android.os.CountDownTimer

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.elegion.android.template.R
import com.elegion.android.template.data.Repository
import com.elegion.android.template.ui.base.activity.BaseActivity
import com.elegion.android.template.ui.features.FeaturesActivity
import com.elegion.android.template.ui.login.LoginActivity

class SplashActivity : BaseActivity(), SplashView {

    @InjectPresenter
    private lateinit var mPresenter: SplashPresenter

    private var mTimer: CountDownTimer? = null
    private var mTimerFinished: Boolean = false

    @ProvidePresenter
    internal fun providePresenter(): SplashPresenter {
        return SplashPresenter(Repository.get(this))
    }

    override fun getLayout(): Int {
        return R.layout.ac_splash
    }

    override fun onResume() {
        super.onResume()
        cancelTimer()
        mTimer = createSplashTimer().start()
    }

    override fun onPause() {
        cancelTimer()
        super.onPause()
    }

    private fun cancelTimer() {
        mTimerFinished = false
        mTimer = mTimer?.run {
            cancel()
            null
        }
    }

    private fun createSplashTimer(): CountDownTimer {
        return object : CountDownTimer(SPLASH_DURATION.toLong(), SPLASH_DURATION.toLong()) {
            override fun onTick(millisUntilFinished: Long) {
                // nothing
            }

            override fun onFinish() {
                mTimerFinished = true
                openNextActivity()
            }
        }
    }

    private fun openNextActivity() {
        if (mTimerFinished) {
            mPresenter.timerFinish()
        }
    }

    override fun openLogin() {
        startActivity(LoginActivity.makeIntent(this))
        supportFinishAfterTransition()
    }

    override fun openFeatures() {
        startActivity(FeaturesActivity.makeIntent(this))
        supportFinishAfterTransition()
    }

    companion object {
        private const val SPLASH_DURATION = 500
    }
}
