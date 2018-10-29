package com.bankex.pay.presentation.ui.importorcreatewallet;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bankex.pay.R;
import com.bankex.pay.di.importorcreate.ImportOrCreateInjector;
import com.bankex.pay.presentation.ui.navigation.importorcreate.IImportWalletRouter;
import com.bankex.pay.presentation.ui.view.base.BaseFragment;

import javax.inject.Inject;

/**
 * Фрагмент экрана Импорта Создания Кошелька
 *
 * @author Gevork Safaryan on 18.09.2018
 */
public class ImportOrCreateFragment extends BaseFragment {

    @Inject
    IImportWalletRouter importWalletRouter;

    private Button mImportWallet;
    private Button mCreateWallet;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.import_or_create_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    @Override
    public void onAttach(Context context) {
        ImportOrCreateInjector.getImportOrCreateComponent().inject(this);
        super.onAttach(context);
    }

    /**
     * Инициализация элементов экрана
     *
     * @param view родительский {@link View}
     */
    private void initViews(View view) {
        mImportWallet = view.findViewById(R.id.import_button);
        mImportWallet.setOnClickListener(this::onImportWalletClick);
        mCreateWallet = view.findViewById(R.id.create_button);
        mCreateWallet.setEnabled(false);
        mCreateWallet.setOnClickListener(this::onCreateWalletClick);
    }

    /**
     * Пользователь нажал кнопку создать
     *
     * @param view объект нажатой {@link View}
     */
    private void onCreateWalletClick(View view) {
        importWalletRouter.openCreateWalletScreen(getActivity());
    }

    /**
     * Пользователь нажал кнопку импортировать
     *
     * @param view объект нажатой {@link View}
     */
    private void onImportWalletClick(View view) {
        importWalletRouter.openImportWalletScreen(getActivity());
    }
}
