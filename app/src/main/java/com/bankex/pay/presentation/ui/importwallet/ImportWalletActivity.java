package com.bankex.pay.presentation.ui.importwallet;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bankex.pay.R;
import com.bankex.pay.presentation.ui.base.BaseActivity;

/**
 * @author Gevork Safaryan 18.09.2018
 */
public class ImportWalletActivity extends BaseActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.import_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new ImportWalletFragment())
                .commit();
    }

}
