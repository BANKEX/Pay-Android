package com.bankex.pay.di.user;

import com.bankex.pay.di.importorcreate.ImportOrCreateComponent;
import com.bankex.pay.di.lockscreen.LockScreenComponent;
import com.bankex.pay.di.mainscreen.MainScreenComponent;
import com.bankex.pay.di.receiveshareaddress.ReceiveShareAddressComponent;
import com.bankex.pay.di.setpin.SetPinComponent;
import dagger.Subcomponent;

/**
 * Component for after-login part of application
 *
 * @author Gevork Safaryan on 11.09.2018.
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

	ImportOrCreateComponent.Builder plusImportOrCreateComponentBuilder();

	SetPinComponent.Builder plusSetPinComponentBuilder();

	LockScreenComponent.Builder plusLockScreenComponentBuilder();

	// TODO: 30/09/2018 возможно - унести в компонент инфо о токене
	ReceiveShareAddressComponent.Builder plusReceiveComponent();

}
