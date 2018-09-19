package com.bankex.pay.di.mainscreen;

import com.bankex.pay.di.contacts.ContactsComponent;
import com.bankex.pay.di.onboarding.OnboardingComponent;
import com.bankex.pay.presentation.ui.mainscreen.MainScreenActivity;

import dagger.Subcomponent;

/**
 * Временный сабкомпонент для варианта "чтобы быстро и работало"
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
@Subcomponent(modules = {MainScreenModule.class})
@MainScreenScope
public interface MainScreenComponent {

    @Subcomponent.Builder
    interface Builder {
        MainScreenComponent.Builder makeMainScreenModule(MainScreenModule module);

        MainScreenComponent build();
    }

    void inject(MainScreenActivity activity);

    OnboardingComponent.Builder plusOnboardingComponent();

    ContactsComponent.Builder plusContactsComponent();
}
