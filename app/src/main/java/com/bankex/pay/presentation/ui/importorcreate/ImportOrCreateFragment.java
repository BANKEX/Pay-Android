package com.bankex.pay.presentation.ui.importorcreate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bankex.pay.R;
import com.bankex.pay.presentation.ui.base.BaseFragment;

/**
 * Фрагмент экрана Импорта Создания Кошелька
 *
 * @author Gevork Safaryan on 18.09.2018
 */
public class ImportOrCreateFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.import_or_create_fragment, container, false);
    }
}
