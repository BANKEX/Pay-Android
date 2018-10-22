package com.bankex.pay.di.onboarding;

import com.bankex.pay.ui.view.onboarding.OnboardingActivity;
import com.bankex.pay.ui.view.onboarding.OnboardingSecondScreenFragment;
import com.bankex.pay.ui.view.onboarding.OnboardingFirstScreenFragment;
import com.bankex.pay.ui.view.onboarding.OnboardingThirdScreenFragment;
import dagger.Subcomponent;

/**
 * Subcomponent for Onboarding screen
 *
 * @author Gevork Safaryan on 11.09.2018.
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

	void inject(OnboardingFirstScreenFragment fragment);

	void inject(OnboardingSecondScreenFragment fragment);

	void inject(OnboardingThirdScreenFragment fragment);
}
