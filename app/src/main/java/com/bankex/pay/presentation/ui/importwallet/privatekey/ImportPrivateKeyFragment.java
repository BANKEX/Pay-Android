package com.bankex.pay.presentation.ui.importwallet.privatekey;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bankex.pay.R;
import com.bankex.pay.presentation.ui.base.BaseFragment;

/**
 * Экран экспорта по ключу
 *
 * @author Gevork Safaryan on 22.09.2018
 */
public class ImportPrivateKeyFragment extends BaseFragment {


    public static ImportPrivateKeyFragment newInstance() {
        return new ImportPrivateKeyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.import_wallet_private_key_fragment, container, false);
    }
}
