package com.bankex.pay.di.onboarding;

import com.bankex.pay.presentation.view.onboarding.OnboardingActivity;
import com.bankex.pay.presentation.view.onboarding.OnboardingFastAndEasyFragment;
import com.bankex.pay.presentation.view.onboarding.OnboardingTalkToUsFragment;
import com.bankex.pay.presentation.view.onboarding.OnboardingWhatIsItFragment;
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

	void inject(OnboardingWhatIsItFragment fragment);

	void inject(OnboardingFastAndEasyFragment fragment);

	void inject(OnboardingTalkToUsFragment fragment);
}
