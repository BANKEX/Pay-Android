package com.bankex.pay.di.importorcreatewallet;

import com.bankex.pay.presentation.ui.importorcreatewallet.ImportOrCreateFragment;
import com.bankex.pay.presentation.ui.importwallet.passphrase.ImportPassPhraseFragment;
import com.bankex.pay.presentation.ui.importwallet.privatekey.ImportPrivateKeyFragment;
import dagger.Subcomponent;

/**
 * Sub component to import or create wallet screen.
 */
@Subcomponent(modules = { ImportOrCreateWalletModule.class })
@ImportOrCreateWalletScope
public interface ImportOrCreateWalletComponent {
	@Subcomponent.Builder
	interface Builder {
		ImportOrCreateWalletComponent.Builder makeImportOrCreateModule(ImportOrCreateWalletModule module);

		ImportOrCreateWalletComponent build();
	}

	void inject(ImportOrCreateFragment fragment);

	void inject(ImportPassPhraseFragment fragment);

	void inject(ImportPrivateKeyFragment fragment);
}
