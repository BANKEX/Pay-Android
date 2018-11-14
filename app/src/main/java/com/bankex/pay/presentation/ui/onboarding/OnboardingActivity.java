package com.bankex.pay.presentation.ui.onboarding;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import com.bankex.pay.R;
import com.bankex.pay.di.onboarding.OnboardingInjector;
import com.bankex.pay.utils.preferences.SharedPreferencesUtils;
import com.github.paolorotolo.appintro.AppIntro;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * View for onboardinbg activity that holds
 * onboarding fragments - slides.
 */
public class OnboardingActivity extends AppIntro {

	public static Intent newIntent(Context context) {
		return new Intent(context, OnboardingActivity.class);
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		OnboardingInjector.getOnboardingComponent().inject(this);
		super.onCreate(savedInstanceState);
		setOnboardingParameters();
		addSlide(OnboardingCustomNetworkFragment.newInstance());
		addSlide(OnboardingFavoriteListFragment.newInstance());
		addSlide(OnboardingERC20StandardFragment.newInstance());
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

	private void setOnboardingParameters() {
		setBarColor(getColor(R.color.white));
		showSeparator(false);

		int color = getColor(R.color.placeholder_body_text);
		setColorDoneText(color);
		setColorSkipButton(color);
		setIndicatorColor(getColor(R.color.onboarding_selected_indicator), color);
		setImageNextButton(getDrawable(R.drawable.ic_chevron_right_grey_24dp));
		setNextArrowColor(color);

		setDoneText(getString(R.string.onboarding_done_text));
		setSkipText(getString(R.string.onboarding_skip_text));
	}

	private void saveResultAndFinish() {
		SharedPreferencesUtils.setOnboardingPreferenceStatus(true);
		setResult(RESULT_OK);
		finish();
	}
}
