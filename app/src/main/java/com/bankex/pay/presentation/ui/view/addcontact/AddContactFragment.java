package com.bankex.pay.presentation.ui.view.addcontact;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bankex.pay.R;
import com.bankex.pay.presentation.presenter.addcontact.AddContactPresenter;
import com.bankex.pay.presentation.ui.navigation.addcontact.IAddContactRouter;
import com.bankex.pay.presentation.ui.view.base.BaseFragment;
import javax.inject.Inject;

public class AddContactFragment extends BaseFragment implements IAddContactView {
	@Inject
	IAddContactRouter mAddContactRouter;

	@Inject
	@InjectPresenter
	AddContactPresenter mAddContactPresenter;

	@ProvidePresenter
	public AddContactPresenter providePresenter() {
		return mAddContactPresenter;
	}

	public static AddContactFragment newInstance() {
		return new AddContactFragment();
	}

	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_add_contact, container, false);
	}

	@Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	@Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		getActivity().getMenuInflater().inflate(R.menu.add_contact_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.menu.add_contact_menu) {
			mAddContactPresenter.onSaveContactClicked(null, null, null);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override public void onDestroy() {
		super.onDestroy();
	}

	@Override public void showError(int fieldId, int errorMessageId) {
		if (getView() != null) {
			TextInputLayout textInputLayout = getView().findViewById(fieldId);
			textInputLayout.setError(string(errorMessageId));
			textInputLayout.setErrorEnabled(true);
		}
	}

	@Override public void hideError(int fieldId) {
		if (getView() != null) {
			TextInputLayout textInputLayout = getView().findViewById(fieldId);
			textInputLayout.setError(null);
			textInputLayout.setErrorEnabled(false);
		}
	}
}
