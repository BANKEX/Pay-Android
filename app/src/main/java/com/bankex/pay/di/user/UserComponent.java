package com.bankex.pay.di.user;

import com.bankex.pay.di.importorcreatewallet.ImportOrCreateWalletComponent;
import com.bankex.pay.di.lockscreen.LockScreenComponent;
import com.bankex.pay.di.mainscreen.MainScreenComponent;
import com.bankex.pay.di.receiveaddress.ReceiveAddressComponent;
import com.bankex.pay.di.setpin.SetPinComponent;
import dagger.Subcomponent;

/**
 * Sub component for user part of application (after login).
 */
@Subcomponent(modules = UserModule.class)
@UserScope
public interface UserComponent {
	@Subcomponent.Builder
	interface Builder {
		UserComponent.Builder userModule(UserModule userModule);

		UserComponent build();
	}

	MainScreenComponent.Builder plusMainScreenComponentBuilder();

	ImportOrCreateWalletComponent.Builder plusImportOrCreateComponentBuilder();

	SetPinComponent.Builder plusSetPinComponentBuilder();

	LockScreenComponent.Builder plusLockScreenComponentBuilder();

	// TODO: 30/09/2018 возможно - унести в компонент инфо о токене
	ReceiveAddressComponent.Builder plusReceiveComponent();
}
