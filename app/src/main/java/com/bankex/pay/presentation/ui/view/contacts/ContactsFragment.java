package com.bankex.pay.presentation.ui.view.contacts;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bankex.pay.R;
import com.bankex.pay.di.contacts.ContactsInjector;
import com.bankex.pay.presentation.presenter.contacts.ContactsPresenter;
import com.bankex.pay.presentation.ui.navigation.contacts.IContactsRouter;
import com.bankex.pay.presentation.ui.view.base.BaseFragment;

import javax.inject.Inject;

/**
 * Экран контактов
 *
 * @author Pavel Apanovskiy on 12/10/2018.
 */
public class ContactsFragment extends BaseFragment implements IContactsView {

    @Inject
    IContactsRouter mContactsRouter;

    @Inject
    @InjectPresenter
    ContactsPresenter mContactsPresenter;

    @ProvidePresenter
    public ContactsPresenter providePresenter() {
        return mContactsPresenter;
    }

    public static ContactsFragment newInstance() {
        return new ContactsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ContactsInjector.getContactsComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contacts_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContactsPresenter.doMagic();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ContactsInjector.clearContactsComponent();
    }

    @Override
    public void showToast() {
        Toast.makeText(getActivity(), "Contacts", Toast.LENGTH_SHORT).show();
    }
}
