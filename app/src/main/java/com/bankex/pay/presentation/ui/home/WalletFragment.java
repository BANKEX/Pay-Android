package com.bankex.pay.presentation.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bankex.pay.R;
import com.bankex.pay.di.wallet.WalletInjector;
import com.bankex.pay.domain.analytics.IAnalyticsManager;
import com.bankex.pay.presentation.ui.navigation.wallet.IWalletRouter;
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

    private Button mImportOrCreateButton;

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
        WalletInjector.getWalletComponent().inject(this);
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
        initViews(view);
    }

    private void initViews(View view) {
        mImportOrCreateButton = view.findViewById(R.id.create_button);
        mImportOrCreateButton.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        mRouter.openImportOrCreate(getContext());
    }


}
