package com.bankex.pay.presentation.view.importorcreate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.bankex.pay.R;
import com.bankex.pay.presentation.view.base.BaseActivity;

/**
 * Import or create wallet activity
 *
 * @author Gevork Safaryan on 18.09.2018
 */
public class ImportOrCreateActivity extends BaseActivity {
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_import_or_create_wallet);

		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.fragment_container, new ImportOrCreateFragment())
				.commit();
	}
}
