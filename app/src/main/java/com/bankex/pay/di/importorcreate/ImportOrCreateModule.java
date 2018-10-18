package com.bankex.pay.di.importorcreate;

import com.bankex.pay.presentation.navigation.importorcreate.IImportWalletRouter;
import com.bankex.pay.presentation.navigation.importorcreate.ImportWalletRouter;
import dagger.Module;
import dagger.Provides;

/**
 * Module for screen Create/import wallet
 *
 * @author Gevork Safaryan on 19.09.2018.
 */
@Module
public class ImportOrCreateModule {

	@Provides
	@ImportOrCreateScope
	IImportWalletRouter provideImportWalletRouter() {
		return new ImportWalletRouter();
	}
}
