package com.bankex.pay.di.onboarding;

import com.bankex.pay.presentation.ui.onboarding.OnboardingActivity;
import com.bankex.pay.presentation.ui.onboarding.OnboardingCustomNetworkFragment;
import com.bankex.pay.presentation.ui.onboarding.OnboardingERC20StandardFragment;
import com.bankex.pay.presentation.ui.onboarding.OnboardingFavoriteListFragment;
import dagger.Subcomponent;

/**
 * Sub component for onboarding screen.
 */
@OnboardingScope
@Subcomponent(modules = { OnboardingModule.class })
public interface OnboardingComponent {
	@Subcomponent.Builder
	interface Builder {
		OnboardingComponent.Builder onboardingModule(OnboardingModule module);

		OnboardingComponent build();
	}

	void inject(OnboardingActivity activity);

	void inject(OnboardingCustomNetworkFragment fragment);

	void inject(OnboardingFavoriteListFragment fragment);

	void inject(OnboardingERC20StandardFragment fragment);
}
