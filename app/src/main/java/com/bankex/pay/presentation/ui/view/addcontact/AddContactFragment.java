package com.bankex.pay.presentation.ui.view.addcontact;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bankex.pay.presentation.ui.view.base.BaseFragment;

public class AddContactFragment extends BaseFragment implements IAddContactView {


	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	@Override public void onDestroy() {
		super.onDestroy();
	}

	@Override public void showError() {

	}

	@Override public void hideError() {

	}
}
