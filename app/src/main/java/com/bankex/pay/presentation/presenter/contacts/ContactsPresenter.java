package com.bankex.pay.presentation.presenter.contacts;

import android.arch.lifecycle.Lifecycle;

import com.arellomobile.mvp.InjectViewState;
import com.bankex.pay.data.reporitories.ContactRepository;
import com.bankex.pay.presentation.presenter.base.BasePresenter;
import com.bankex.pay.presentation.ui.home.IContactView;
import com.bankex.pay.presentation.ui.lockscreen.LockScreenActivity;

import org.jetbrains.annotations.NotNull;

/**
 * Презентер фрагмента отображающего конгакты пользователя {@link LockScreenActivity}
 *
 * @author Denis Anisimov.
 */
@InjectViewState
public class ContactsPresenter extends BasePresenter<IContactView> {

    private ContactRepository datasource;

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
