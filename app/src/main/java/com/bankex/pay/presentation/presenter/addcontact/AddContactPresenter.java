package com.bankex.pay.presentation.presenter.addcontact;

import com.arellomobile.mvp.InjectViewState;
import com.bankex.pay.R;
import com.bankex.pay.domain.interactor.IContactsInteractor;
import com.bankex.pay.domain.model.ContactModel;
import com.bankex.pay.presentation.presenter.base.BasePresenter;
import com.bankex.pay.presentation.ui.addcontact.IAddContactView;

/**
 * Presenter for screen to add new contact.
 */
@InjectViewState
public class AddContactPresenter extends BasePresenter<IAddContactView> {
	private final IContactsInteractor mContactsInteractor;

	public AddContactPresenter(IContactsInteractor mContactsInteractor) {
		this.mContactsInteractor = mContactsInteractor;
	}

	public void onSaveContactClicked(String name, String surname, String address) {
		if (name == null || name.isEmpty()) {
			getViewState().showTextInputError(R.id.til_first_name, R.string.add_contact_error_first_name);
		} else if (surname == null || surname.isEmpty()) {
			getViewState().showTextInputError(R.id.til_surname, R.string.add_contact_error_surname);
		} else if (address == null || address.isEmpty()) {
			getViewState().showTextInputError(R.id.til_address, R.string.add_contact_error_address);
		} else {
			ContactModel mContactModel = new ContactModel();
			mContactModel.setName(name);
			mContactModel.setSurname(surname);
			mContactModel.setAddress(address);

			mContactsInteractor.addContact(mContactModel);
			getViewState().popBackStack();
		}
	}
}
