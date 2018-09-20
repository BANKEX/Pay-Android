package com.bankex.pay.presentation.ui.home;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bankex.pay.R;
import com.bankex.pay.domain.analytics.IAnalyticsManager;
import com.bankex.pay.domain.navigation.wallet.IWalletRouter;
import com.bankex.pay.presentation.presenter.contacts.ContactsPresenter;
import com.bankex.pay.presentation.ui.base.BaseFragment;

import javax.inject.Inject;

import br.com.stickyindex.view.FastScroller;
import br.com.stickyindex.view.StickyIndex;

/**
 * Фрагмент экрана контактов
 *
 * @author Denis Anisimov.
 */
public class ContactFragment extends BaseFragment {

    @Inject
    IWalletRouter mRouter;

    @Inject
    IAnalyticsManager mAnalyticsManager;

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

}
