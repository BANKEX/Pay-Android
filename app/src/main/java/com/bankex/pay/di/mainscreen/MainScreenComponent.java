package com.bankex.pay.di.mainscreen;

import com.bankex.pay.di.onboarding.OnboardingComponent;
import com.bankex.pay.di.settings.SettingsComponent;
import com.bankex.pay.di.transactionhistory.TransactionHistoryComponent;
import com.bankex.pay.di.wallet.WalletComponent;
import com.bankex.pay.presentation.view.mainscreen.MainScreenActivity;
import dagger.Subcomponent;

/**
 * Temporary subcomponent for MainScreen
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
@Subcomponent(modules = { MainScreenModule.class })
@MainScreenScope
public interface MainScreenComponent {

	@Subcomponent.Builder
	interface Builder {
		MainScreenComponent.Builder makeMainScreenModule(MainScreenModule module);

		MainScreenComponent build();
	}

	void inject(MainScreenActivity activity);

	OnboardingComponent.Builder plusOnboardingComponent();

	WalletComponent.Builder plusWalletComponent();

	SettingsComponent.Builder plusSettingsComponent();

	TransactionHistoryComponent.Builder plusTransactionHistoryComponent();
}
