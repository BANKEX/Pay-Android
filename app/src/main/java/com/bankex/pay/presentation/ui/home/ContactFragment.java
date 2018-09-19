package com.bankex.pay.presentation.ui.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bankex.pay.R;
import com.bankex.pay.domain.analytics.IAnalyticsManager;
import com.bankex.pay.domain.navigation.wallet.IWalletRouter;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment {

    @Inject
    IWalletRouter mRouter;
    @Inject
    IAnalyticsManager mAnalyticsManager;

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
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

}
