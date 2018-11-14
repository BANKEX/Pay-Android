package com.bankex.pay.di.lockscreen;

import com.bankex.pay.di.user.UserComponentInjector;

/**
 * Injector for {@link LockScreenComponent}.
 */
public class LockScreenInjector {
	private static LockScreenComponent sLockScreenComponent;

	public static LockScreenComponent getLockScreenComponent() {
		if (sLockScreenComponent == null) {
			sLockScreenComponent = UserComponentInjector.getUserComponent()
					.plusLockScreenComponentBuilder()
					.makeLockScreenModule(new LockScreenModule())
					.build();
		}
		return sLockScreenComponent;
	}

	public static void clearLockScreenComponent() {
		sLockScreenComponent = null;
	}
}
