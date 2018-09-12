package com.bankex.pay.presentation.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bankex.pay.R;
import com.bankex.pay.domain.analytics.IAnalyticsManager;
import com.bankex.pay.domain.navigation.wallet.IWalletRouter;
import com.bankex.pay.presentation.ui.base.BaseFragment;

import javax.inject.Inject;

/**
 * Фрагмент экрана кошелька
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
public class WalletFragment extends BaseFragment {

    @Inject
    IWalletRouter mRouter;
    @Inject
    IAnalyticsManager mAnalyticsManager;

    /**
     * Возвращаем инстанс фрагмента WalletFragment
     *
     * @return new WalletFragment
     */
    public static WalletFragment newInstance() {
        return new WalletFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}
