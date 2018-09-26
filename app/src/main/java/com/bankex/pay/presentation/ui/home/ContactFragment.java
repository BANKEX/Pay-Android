package com.bankex.pay.presentation.ui.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bankex.pay.R;
import com.bankex.pay.di.contacts.ContactsInjector;
import com.bankex.pay.domain.analytics.IAnalyticsManager;
import com.bankex.pay.domain.models.ContactModel;
import com.bankex.pay.domain.navigation.wallet.IWalletRouter;
import com.bankex.pay.presentation.presenter.contacts.ContactsPresenter;
import com.bankex.pay.presentation.ui.base.BaseFragment;
import com.bankex.pay.presentation.ui.home.adapter.ContactsAdapter;

import java.util.List;

import javax.inject.Inject;

import br.com.stickyindex.view.FastScroller;
import br.com.stickyindex.view.StickyIndex;

/**
 * Фрагмент экрана контактов
 *
 * @author Denis Anisimov.
 */
public class ContactFragment extends BaseFragment implements IContactView {

    @Inject
    IWalletRouter mRouter;

    @Inject
    IAnalyticsManager mAnalyticsManager;

    @Inject
    ContactsAdapter adapter;

    @Inject
    @InjectPresenter
    ContactsPresenter mContactsPresenter;

    @ProvidePresenter
    ContactsPresenter providePresenter() {
        return mContactsPresenter;
    }

    RecyclerView recyclerView;

    FastScroller fastScroller;

    StickyIndex stickyIndex;

    FloatingActionButton floatingActionButton;

    /**
     * Возвращаем инстанс фрагмента ContactFragment
     *
     * @return new ContactFragment
     */
    public static ContactFragment newInstance() {
        return new ContactFragment();
    }

    public ContactFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ContactsInjector.getContactsComponent(getLifecycle()).inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_contact, container, false);
        initView(inflate);
        return inflate;

    }

    private void initView(View inflate) {
        recyclerView = inflate.findViewById(R.id.recyclerView);
        fastScroller = inflate.findViewById(R.id.fastScroller);
        stickyIndex = inflate.findViewById(R.id.stickyIndex);
        floatingActionButton = inflate.findViewById(R.id.fab);
    }

    @Override
    public void assemblyContactList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        assemblyStickyIndexAndFastScroller();
    }

    @Override
    public void assemblyStickyIndexAndFastScroller() {
        stickyIndex.bindRecyclerView(recyclerView);
        fastScroller.bindRecyclerView(recyclerView);
    }

    private char[] convertToIndexList(List<ContactModel> list) {
        char[] result = new char[list.size()];
        int i = 0;
        for (ContactModel model : list) {
            result[i] = model.getFirstName().toUpperCase().charAt(0);
            i++;
        }
        return result;
    }

    @Override
    public void loadContacts(List<ContactModel> contacts) {
        adapter.refresh(contacts);
        stickyIndex.refresh(convertToIndexList(contacts));
    }

    @Override
    public void scrollToContact(String name) {

    }


}
