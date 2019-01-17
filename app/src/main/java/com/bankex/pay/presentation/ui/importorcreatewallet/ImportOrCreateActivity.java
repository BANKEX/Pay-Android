package com.bankex.pay.presentation.ui.importorcreatewallet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.bankex.pay.R;
import com.bankex.pay.presentation.ui.base.BaseActivity;

/**
 * Import or create wallet activity that holds {@link ImportOrCreateFragment}.
 */
public class ImportOrCreateActivity extends BaseActivity {
	public static Intent newIntent(Context context) {
		return new Intent(context, ImportOrCreateActivity.class);
	}

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
