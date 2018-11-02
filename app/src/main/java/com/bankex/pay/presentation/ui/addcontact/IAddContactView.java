package com.bankex.pay.presentation.ui.addcontact;

import android.support.annotation.StringRes;
import com.bankex.pay.presentation.ui.base.BaseView;

public interface IAddContactView extends BaseView {
	void showTextInputError(int fieldId, int errorMessageId);
	void hideTextInputError(int fieldId);
	void showMessage(@StringRes int messageId);
	void popBackStack();
}
