package com.bankex.pay.presentation.view.onboarding;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bankex.pay.R;
import com.bankex.pay.di.onboarding.OnboardingInjector;
import com.bankex.pay.presentation.view.base.BaseFragment;

/**
 * First Onboarding fragment (ERC-20 standard)
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
public class OnboardingThirdScreenFragment extends BaseFragment {

	/**
	 * @return new OnboardingThirdScreenFragment intent
	 */
	public static OnboardingThirdScreenFragment newInstance() {
		return new OnboardingThirdScreenFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		OnboardingInjector.getOnboardingComponent().inject(this);
		super.onCreate(savedInstanceState);
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.onboarding_erc20_standard, container, false);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		OnboardingInjector.clearOnboardingComponent();
	}
}
