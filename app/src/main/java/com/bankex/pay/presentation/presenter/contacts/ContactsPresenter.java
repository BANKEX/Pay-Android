package com.bankex.pay.presentation.presenter.contacts;

import com.arellomobile.mvp.InjectViewState;
import com.bankex.pay.data.entity.ContactModel;
import com.bankex.pay.domain.interactor.IContactsInteractor;
import com.bankex.pay.presentation.presenter.base.BasePresenter;
import com.bankex.pay.presentation.ui.contacts.IContactsView;
import com.bankex.pay.utils.rx.IRxSchedulersUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Presenter for Contacts screen.
 */
@InjectViewState
public class ContactsPresenter extends BasePresenter<IContactsView> {
	private IContactsInteractor mContactsInteractor;
	private IRxSchedulersUtils mRxSchedulersUtils;

	private List<ContactModel> contacts;

	public ContactsPresenter(IContactsInteractor mContactsInteractor, IRxSchedulersUtils mRxSchedulersUtils) {
		this.mContactsInteractor = mContactsInteractor;
		this.mRxSchedulersUtils = mRxSchedulersUtils;
		contacts = new ArrayList<>();
	}

	@Override public void attachView(IContactsView view) {
		super.attachView(view);
		loadContacts();
	}

	// TODO add asynchronous
	private void loadContacts() {
		contacts = mContactsInteractor.getSavedContacts();
		getViewState().setContacts(contacts);

		if (contacts.size() == 0) {
			getViewState().showContactsList(false);
			getViewState().showEmptyView(true);
		} else {
			getViewState().showContactsList(true);
			getViewState().showEmptyView(false);
		}
	}
}
