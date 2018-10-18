package com.bankex.pay.di.importorcreate;

import com.bankex.pay.presentation.view.importorcreate.ImportOrCreateFragment;
import dagger.Subcomponent;

/**
 * Subcomponent for impoet or create wallet screen
 *
 * @author Gevork Safaryan on 19.09.2018.
 */
@Subcomponent(modules = { ImportOrCreateModule.class })
@ImportOrCreateScope
public interface ImportOrCreateComponent {

	@Subcomponent.Builder
	interface Builder {
		ImportOrCreateComponent.Builder makeImportOrCreateModule(ImportOrCreateModule module);

		ImportOrCreateComponent build();
	}

	void inject(ImportOrCreateFragment fragment);
}
