package com.bankex.pay.di.importorcreate;

import com.bankex.pay.di.user.UserComponentInjector;

/**
 * Injector for component {@link ImportOrCreateComponent}
 *
 * @author Gevork Safaryan on 19.09.2018.
 */
public class ImportOrCreateInjector {

	private static ImportOrCreateComponent sImportOrCreateComponent;

	public static ImportOrCreateComponent getImportOrCreateComponent() {
		if (sImportOrCreateComponent == null) {
			sImportOrCreateComponent = UserComponentInjector.getUserComponent()
				.plusImportOrCreateComponentBuilder()
				.makeImportOrCreateModule(new ImportOrCreateModule())
				.build();
		}
		return sImportOrCreateComponent;
	}

	public static void clearImportOrCreateComponent() {
		sImportOrCreateComponent = null;
	}
}
