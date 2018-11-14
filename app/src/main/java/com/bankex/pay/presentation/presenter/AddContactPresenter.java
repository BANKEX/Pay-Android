package com.bankex.pay.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.bankex.pay.R;
import com.bankex.pay.domain.interactor.IContactsInteractor;
import com.bankex.pay.domain.model.ContactModel;
import com.bankex.pay.presentation.presenter.base.BasePresenter;
import com.bankex.pay.presentation.ui.addcontact.AddContactFragment;
import com.bankex.pay.presentation.ui.addcontact.IAddContactView;

/**
 * Presenter for {@link AddContactFragment}.
 */
@InjectViewState
public class AddContactPresenter extends BasePresenter<IAddContactView> {
	private final IContactsInteractor mContactsInteractor;

	public AddContactPresenter(IContactsInteractor сontactsInteractor) {
		this.mContactsInteractor = сontactsInteractor;
	}

	public void onSaveContactClicked(String name, String surname, String address) {
		if (name.isEmpty()) {
			getViewState().showTextInputError(R.id.til_first_name, R.string.add_contact_error_first_name);
		}
		if (surname.isEmpty()) {
			getViewState().showTextInputError(R.id.til_surname, R.string.add_contact_error_surname);
		}
		if (address.isEmpty()) {
			getViewState().showTextInputError(R.id.til_address, R.string.add_contact_error_address);
		}
		if (!name.isEmpty() && !surname.isEmpty() && !address.isEmpty()) {
			ContactModel mContactModel = new ContactModel(name, surname, address, "");
			mContactsInteractor.addContact(mContactModel);
			getViewState().showMessage(R.string.add_contact_added_success);
			getViewState().popBackStack();
		}
	}
}
