package com.elegion.android.ui.splash;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;

import com.pchela.android.R;
import com.pchela.android.data.Repository;
import com.pchela.android.ui.main.MainActivity;
import com.pchela.android.ui.tutorial.activity.TutorialActivity;

/**
 * @author mikhail barannikov
 */
public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_DURATION = 500;

    private CountDownTimer mTimer;
    private boolean mTimerFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cancelTimer();
        mTimer = getSplashTimer();
        mTimer.start();
    }

    @Override
    protected void onPause() {
        cancelTimer();
        super.onPause();
    }

    private void cancelTimer() {
        mTimerFinished = false;
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    private CountDownTimer getSplashTimer() {
        return new CountDownTimer(SPLASH_DURATION, SPLASH_DURATION) {
            @Override
            public void onTick(long millisUntilFinished) {
                // nothing
            }

            @Override
            public void onFinish() {
                mTimerFinished = true;
                boolean isTutorialShown = Repository.get().isTutorialShown();
                if (isTutorialShown) {
                    openNextActivity();
                } else {
                    showTutorial();
                }
            }
        };
    }

    private void openNextActivity() {
        if (mTimerFinished) {
            // TODO: add getLoginCode check logic here
            openMainActivity();
        }
    }

    private void openMainActivity() {
        startActivity(MainActivity.makeIntent(this));
    }

    private void showTutorial() {
        startActivity(TutorialActivity.makeIntent(this));
        finish();
    }
}
