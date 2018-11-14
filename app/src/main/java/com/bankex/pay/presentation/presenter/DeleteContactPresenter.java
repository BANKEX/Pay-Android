package com.bankex.pay.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.bankex.pay.R;
import com.bankex.pay.domain.interactor.IContactsInteractor;
import com.bankex.pay.presentation.presenter.base.BasePresenter;
import com.bankex.pay.presentation.ui.deletecontact.DeleteContactDialog;
import com.bankex.pay.presentation.ui.deletecontact.IDeleteContactView;

/**
 * Presenter for {@link DeleteContactDialog}.
 */
@InjectViewState
public class DeleteContactPresenter extends BasePresenter<IDeleteContactView> {
	private IContactsInteractor mContactsInteractor;

	public DeleteContactPresenter(IContactsInteractor contactsInteractor) {
		this.mContactsInteractor = contactsInteractor;
	}

	public void onDeleteClicked(String contactId) {
		mContactsInteractor.deleteContact(contactId);
		getViewState().showMessage(R.string.delete_contact_success_message);
		getViewState().openContactsListFragment();
	}
}
