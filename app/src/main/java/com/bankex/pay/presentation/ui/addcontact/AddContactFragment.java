package com.bankex.pay.presentation.ui.addcontact;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bankex.pay.R;
import com.bankex.pay.di.addcontact.AddContactInjector;
import com.bankex.pay.presentation.navigation.contacts.ContactsRouter;
import com.bankex.pay.presentation.presenter.addcontact.AddContactPresenter;
import com.bankex.pay.presentation.ui.base.BaseFragment;
import javax.inject.Inject;

/**
 * Fragment to add new contact and save it.
 */
public class AddContactFragment extends BaseFragment implements IAddContactView {
	@Inject
	@InjectPresenter
	AddContactPresenter mAddContactPresenter;

	@Inject ContactsRouter mContactsRouter;

	@BindView(R.id.add_contact_toolbar) android.support.v7.widget.Toolbar mToolbar;
	@BindView(R.id.til_first_name) TextInputLayout mInputName;
	@BindView(R.id.et_first_name) EditText mName;
	@BindView(R.id.til_surname) TextInputLayout mInputSurname;
	@BindView(R.id.et_surname) EditText mSurname;
	@BindView(R.id.til_address) TextInputLayout mInputAddress;
	@BindView(R.id.et_address) EditText mAddress;

	@BindString(R.string.add_contact_title) String title;

	private Unbinder mBinder;

	@ProvidePresenter
	public AddContactPresenter providePresenter() {
		return mAddContactPresenter;
	}

	public static AddContactFragment newInstance() {
		return new AddContactFragment();
	}

	@Override public void onCreate(Bundle savedInstanceState) {
		AddContactInjector.getContactsComponent().inject(this);
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_add_contact, container, false);
		mBinder = ButterKnife.bind(this, view);
		setHasOptionsMenu(true);
		return view;
	}

	@Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initToolbar();
	}

	@Override public void onDestroyView() {
		super.onDestroyView();
		mBinder.unbind();
	}

	@Override public void onDestroy() {
		super.onDestroy();
		AddContactInjector.clearContactsComponent();
	}

	@Override public void showTextInputError(int fieldId, int errorMessageId) {
		if (getView() != null) {
			TextInputLayout textInputLayout = getView().findViewById(fieldId);
			textInputLayout.setError(string(errorMessageId));
			textInputLayout.setErrorEnabled(true);
		}
	}

	@Override public void hideTextInputError(int fieldId) {
		if (getView() != null) {
			TextInputLayout textInputLayout = getView().findViewById(fieldId);
			textInputLayout.setError(null);
			textInputLayout.setErrorEnabled(false);
		}
	}

	@Override public void showMessage(@StringRes int messageId) {
		showMessageToast(string(messageId));
	}

	@Override public void popBackStack() {
		mContactsRouter.popBackStack(getActivity());
	}

	private void initToolbar() {
		mToolbar.setTitle(title);
		mToolbar.setNavigationIcon(R.drawable.ic_arrow_left_black_24dp);
		mToolbar.setNavigationOnClickListener(v -> mContactsRouter.popBackStack(getActivity()));
		mToolbar.inflateMenu(R.menu.save_contact_menu);
		mToolbar.setOnMenuItemClickListener(menuItem -> {
			switch (menuItem.getItemId()) {
				case R.id.save_contact:
					mAddContactPresenter.onSaveContactClicked(
							mName.getText().toString(),
							mSurname.getText().toString(),
							mAddress.getText().toString());
					return true;
			}
			return false;
		});
	}
}
