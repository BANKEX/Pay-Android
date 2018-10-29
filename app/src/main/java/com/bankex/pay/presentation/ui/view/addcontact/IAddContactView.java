package com.bankex.pay.presentation.ui.view.addcontact;

import com.bankex.pay.presentation.ui.view.base.BaseView;

public interface IAddContactView extends BaseView {
	void showError(int fieldId, int errorMessageId);

	void hideError(int fieldId);
}
