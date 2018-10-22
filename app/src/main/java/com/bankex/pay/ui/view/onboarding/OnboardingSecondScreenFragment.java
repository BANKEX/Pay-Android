package com.bankex.pay.ui.view.onboarding;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bankex.pay.R;
import com.bankex.pay.di.onboarding.OnboardingInjector;
import com.bankex.pay.ui.view.base.BaseFragment;

/**
 * First Onboarding fragment (favourite list)
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
public class OnboardingSecondScreenFragment extends BaseFragment {

	/**
	 * @return OnboardingSecondScreenFragment instance
	 */
	public static OnboardingSecondScreenFragment newInstance() {
		return new OnboardingSecondScreenFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		OnboardingInjector.getOnboardingComponent().inject(this);
		super.onCreate(savedInstanceState);
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.onboarding_favorite_list, container, false);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		OnboardingInjector.clearOnboardingComponent();
	}
}
