package com.bankex.pay.presentation.presenter.contacts;

import com.arellomobile.mvp.InjectViewState;
import com.bankex.pay.presentation.presenter.base.BasePresenter;
import com.bankex.pay.presentation.ui.view.contacts.IContactsView;

/**
 * Презентер экрана контактов
 *
 * @author Pavel Apanovskiy on 12/10/2018.
 */
@InjectViewState
public class ContactsPresenter extends BasePresenter<IContactsView> {

    public ContactsPresenter() {
    }

    public void doMagic() {
        getViewState().showToast();
    }
}
