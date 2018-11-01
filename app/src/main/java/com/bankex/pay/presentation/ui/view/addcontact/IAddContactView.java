package com.bankex.pay.presentation.ui.view.addcontact;

import com.bankex.pay.presentation.ui.base.BaseView;

public interface IAddContactView extends BaseView {
	void showTextInputError(int fieldId, int errorMessageId);

	void hideTextInputError(int fieldId);
}
