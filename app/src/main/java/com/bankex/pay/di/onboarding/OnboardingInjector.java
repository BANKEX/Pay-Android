package com.bankex.pay.di.onboarding;

import com.bankex.pay.di.mainscreen.MainScreenInjector;

/**
 * @author Gevork Safaryan on 11.09.2018.
 */
public class OnboardingInjector {

    private static OnboardingComponent sOnboardingComponent;

    public static OnboardingComponent getOnboardingComponent() {
        if (sOnboardingComponent == null) {
            sOnboardingComponent = MainScreenInjector.getMainScreenComponent()
                    .plusOnboardingComponent()
                    .onboardingModule(new OnboardingModule())
                    .build();
        }
        return sOnboardingComponent;
    }

    public static void clearOnboardingComponent() {
        sOnboardingComponent = null;
    }
}
