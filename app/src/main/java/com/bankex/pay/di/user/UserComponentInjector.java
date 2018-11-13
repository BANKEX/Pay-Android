package com.bankex.pay.di.user;

import com.bankex.pay.BankexPayApplication;

/**
 * Injector for user part of application (after login).
 */
public class UserComponentInjector {

	private static UserComponent sUserComponent;

	public static UserComponent getUserComponent() {
		if (sUserComponent == null) {
			sUserComponent = BankexPayApplication
					.getApplicationComponent()
					.plusUserComponent()
					.userModule(new UserModule())
					.build();
		}
		return sUserComponent;
	}

	public static void clearUserComponent() {
		sUserComponent = null;
	}
}
