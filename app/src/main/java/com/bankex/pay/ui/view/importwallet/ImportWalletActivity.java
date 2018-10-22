package com.bankex.pay.ui.view.importwallet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.bankex.pay.R;
import com.bankex.pay.ui.view.base.BaseActivity;

/**
 * @author Gevork Safaryan 18.09.2018
 */
public class ImportWalletActivity extends BaseActivity {
  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_import_wallet);
    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.fragment_container, new ImportWalletFragment())
        .commit();
  }
}
