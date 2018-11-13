package com.bankex.pay.di.deletecontact;

import com.bankex.pay.presentation.ui.deletecontact.DeleteContactDialog;
import dagger.Subcomponent;

/**
 * Sub component for custom delete contact dialog.
 */
@Subcomponent(modules = { DeleteContactModule.class })
@DeleteContactScope
public interface DeleteContactComponent {
	@Subcomponent.Builder
	interface Builder {
		DeleteContactComponent.Builder makeDeleteContactModule(DeleteContactModule module);
		DeleteContactComponent build();
	}

	void inject(DeleteContactDialog fragmentDialog);
}
