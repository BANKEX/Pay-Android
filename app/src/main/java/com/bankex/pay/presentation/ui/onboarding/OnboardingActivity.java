package com.bankex.pay.presentation.ui.onboarding;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.bankex.pay.R;
import com.bankex.pay.di.onboarding.OnboardingInjector;
import com.bankex.pay.utils.SharedPreferencesUtils;
import com.github.paolorotolo.appintro.AppIntro;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Активити онбординга
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
public class OnboardingActivity extends AppIntro {


    /**
     * Возвращает интент OnboardingActivity
     *
     * @param context Context
     * @return intent
     */
    public static Intent newIntent(Context context) {
        return new Intent(context, OnboardingActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        OnboardingInjector.getOnboardingComponent().inject(this);
        super.onCreate(savedInstanceState);

        setOnboardingParameters();

        addSlide(OnboardingWhatIsItFragment.newInstance());
        addSlide(OnboardingFastAndEasyFragment.newInstance());
        addSlide(OnboardingTalkToUsFragment.newInstance());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OnboardingInjector.clearOnboardingComponent();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        saveResultAndFinish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        saveResultAndFinish();
        JSONObject object = new JSONObject();
        try {
            object.put("Screen", currentFragment.getClass().getSimpleName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        String oldScreenName = null;
        String newScreenName = null;
        if (oldFragment != null) {
            oldScreenName = oldFragment.getClass().getSimpleName();
        }
        if (newFragment != null) {
            newScreenName = newFragment.getClass().getSimpleName();
        }
    }

    private void setOnboardingParameters() {
        setBarColor(getColor(R.color.bankex_white));
        showSeparator(false);

        int color = getColor(R.color.placeholder_body_text);
        setColorDoneText(color);
        setColorSkipButton(color);
        setIndicatorColor(
                getColor(R.color.onboarding_selected_indicator),
                color);
        setImageNextButton(getDrawable(R.drawable.ic_chevron_right_grey_24dp));
        setNextArrowColor(color);

        setDoneText(getString(R.string.onboarding_done_text));
        setSkipText(getString(R.string.onboarding_skip_text));
    }

    private void saveResultAndFinish() {
        SharedPreferencesUtils.setOnboardingPreferenceStatus(this, true);
        finish();
    }
}
