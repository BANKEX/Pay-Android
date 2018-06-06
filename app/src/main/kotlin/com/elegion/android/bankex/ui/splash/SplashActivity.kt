package com.elegion.android.bankex.ui.splash

import android.os.CountDownTimer

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.elegion.android.bankex.R
import com.elegion.android.bankex.data.Repository
import com.elegion.android.bankex.ui.base.activity.BaseActivity
import com.elegion.android.bankex.ui.generation.GenerationActivity
import com.elegion.android.bankex.ui.onboarding.OnBoardingActivity

class SplashActivity : BaseActivity(), SplashView {

    @InjectPresenter
    internal lateinit var presenter: SplashPresenter

    private var timer: CountDownTimer? = null
    private var timerFinished: Boolean = false

    @ProvidePresenter
    internal fun providePresenter(): SplashPresenter = SplashPresenter(Repository.get(this))

    override fun getLayout(): Int = R.layout.ac_splash

    override fun onResume() {
        super.onResume()
        cancelTimer()
        timer = createSplashTimer().start()
    }

    override fun onPause() {
        cancelTimer()
        super.onPause()
    }

    private fun cancelTimer() {
        timerFinished = false
        if (timer != null) {
            timer!!.cancel()
            timer = null
        }
    }

    private fun createSplashTimer(): CountDownTimer {
        return object : CountDownTimer(SPLASH_DURATION.toLong(), SPLASH_DURATION.toLong()) {
            override fun onTick(millisUntilFinished: Long) {
                // nothing
            }

            override fun onFinish() {
                timerFinished = true
                openNextActivity()
            }
        }
    }

    private fun openNextActivity() {
        if (timerFinished) {
            presenter.timerFinish()
        }
    }

    override fun openOnBoarding() {
        startActivity(OnBoardingActivity.makeIntent(this))
        supportFinishAfterTransition()
    }

    override fun openWallet() {
        startActivity(GenerationActivity.makeIntent(this))
        supportFinishAfterTransition()
    }

    companion object {
        private const val SPLASH_DURATION = 500
    }
}