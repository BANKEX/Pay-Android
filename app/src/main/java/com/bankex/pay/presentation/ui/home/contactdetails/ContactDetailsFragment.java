package com.bankex.pay.presentation.ui.home.contactdetails;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bankex.pay.R;
import com.bankex.pay.domain.analytics.IAnalyticsManager;
import com.bankex.pay.presentation.ui.navigation.contacts.IContactsRouter;
import com.bankex.pay.presentation.presenter.contacts.ContactsPresenter;
import com.bankex.pay.presentation.ui.home.adapter.ContactsAdapter;
import com.bankex.pay.presentation.ui.home.addcontacts.IAddContactView;
import com.bankex.pay.presentation.ui.view.base.BaseFragment;

import javax.inject.Inject;

/**
 * Фрагмент экрана просмотра деталей контактов
 *
 * @author Denis Anisimov.
 */
public class ContactDetailsFragment extends BaseFragment implements IContactDetailsView{

    @Inject
    IContactsRouter mRouter;

    @Inject
    IAnalyticsManager mAnalyticsManager;

    @Inject
    ContactsAdapter adapter;

    ContactsPresenter mContactsPresenter;

/*    @ProvidePresenter
    ContactsPresenter providePresenter() {
        return mContactsPresenter;
    }*/

    /**
     * Возвращаем инстанс фрагмента
     *
     * @return new ContactDetailsFragment
     */
    public static ContactDetailsFragment newInstance() {
        return new ContactDetailsFragment();
    }

    public ContactDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_contact, container, false);
        return inflate;
    }


    @Override
    public void sendToAddress() {

    }

    @Override
    public void deleteContact() {

    }
}
