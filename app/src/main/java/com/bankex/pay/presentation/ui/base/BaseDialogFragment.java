package com.bankex.pay.presentation.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;

/**
 * Basic application DialogFragemnt for customs dialogs.
 */
public abstract class BaseDialogFragment extends AppCompatDialogFragment {

	@Override public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override public void onDestroyView() {
		super.onDestroyView();
	}

	@Override public void onDestroy() {
		super.onDestroy();
	}

	@Override public void dismiss() {
		super.dismiss();
	}
}
