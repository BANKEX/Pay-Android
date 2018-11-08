package com.bankex.pay.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.bankex.pay.domain.interactor.IContactsInteractor;
import com.bankex.pay.domain.model.ContactModel;
import com.bankex.pay.presentation.presenter.base.BasePresenter;
import com.bankex.pay.presentation.ui.contactinfo.IContactInfoView;

@InjectViewState
public class ContactInfoPresenter extends BasePresenter<IContactInfoView> {
	private IContactsInteractor mContactsInteractor;

	public ContactInfoPresenter(IContactsInteractor contactsInteractor) {
		mContactsInteractor = contactsInteractor;
	}

	public ContactModel setContactInfo(String contactId) {
		return mContactsInteractor.getSavedContacts(contactId);
	}
}
