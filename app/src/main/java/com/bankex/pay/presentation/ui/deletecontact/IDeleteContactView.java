package com.bankex.pay.presentation.ui.deletecontact;

import android.support.annotation.StringRes;
import com.bankex.pay.presentation.ui.base.BaseView;

/**
 * View interface for custom deleting dialog.
 */
public interface IDeleteContactView extends BaseView {
	void openContactsListFragment();

	void showMessage(@StringRes int messageId);
}
