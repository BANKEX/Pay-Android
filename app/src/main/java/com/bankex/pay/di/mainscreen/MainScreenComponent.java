package com.bankex.pay.di.mainscreen;

import com.bankex.pay.di.addcontact.AddContactComponent;
import com.bankex.pay.di.contactinfo.ContactInfoComponent;
import com.bankex.pay.di.contacts.ContactsComponent;
import com.bankex.pay.di.deletecontact.DeleteContactComponent;
import com.bankex.pay.di.onboarding.OnboardingComponent;
import com.bankex.pay.di.settings.SettingsComponent;
import com.bankex.pay.di.transactionhistory.TransactionHistoryComponent;
import com.bankex.pay.di.wallet.WalletComponent;
import com.bankex.pay.presentation.ui.mainscreen.MainScreenActivity;
import dagger.Subcomponent;

/**
 * TODO fix this mess
 * Main screen sub component.
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

	ContactsComponent.Builder plusContactsComponentBuilder();

	AddContactComponent.Builder plusAddContactBuilder();

	ContactInfoComponent.Builder plusContactInfoBuilder();

	DeleteContactComponent.Builder plusDeleteContactBuilder();
}
