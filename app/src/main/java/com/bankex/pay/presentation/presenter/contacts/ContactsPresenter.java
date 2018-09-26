package com.bankex.pay.presentation.presenter.contacts;

import com.arellomobile.mvp.InjectViewState;
import com.bankex.pay.data.reporitories.ContactRepository;
import com.bankex.pay.presentation.presenter.base.BasePresenter;
import com.bankex.pay.presentation.ui.home.IContactView;
import com.bankex.pay.presentation.ui.lockscreen.LockScreenActivity;

/**
 * Презентер фрагмента отображающего конгакты пользователя {@link LockScreenActivity}
 *
 * @author Denis Anisimov.
 */
@InjectViewState
public class ContactsPresenter extends BasePresenter<IContactView> {

    private ContactRepository datasource;

    public ContactsPresenter(ContactRepository datasource) {
        this.datasource = datasource;
    }

    @Override
    public void onStart() {
        super.onStart();
        loadContacts();
        onContactClicked();
    }

    public void onContactClicked() {

    }

    private void loadContacts() {
        addDisposable(datasource.getContacts().doOnSuccess(contactModels -> getViewState().loadContacts(contactModels)).subscribe());
    }
}
