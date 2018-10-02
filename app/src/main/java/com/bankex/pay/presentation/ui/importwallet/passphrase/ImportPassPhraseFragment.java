package com.bankex.pay.presentation.ui.importwallet.passphrase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bankex.pay.R;
import com.bankex.pay.presentation.ui.view.base.BaseFragment;

/**
 * Экран экспорта по фразе
 *
 * @author Gevork Safaryan on 22.09.2018
 */
public class ImportPassPhraseFragment extends BaseFragment {

    public static ImportPassPhraseFragment newInstance() {
        return new ImportPassPhraseFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.import_wallet_pass_phrase_fragment, container, false);
    }
}
