package com.bankex.pay.presentation.ui.contactinfo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bankex.pay.R;
import com.bankex.pay.di.contactinfo.ContactInfoInjector;
import com.bankex.pay.presentation.navigation.contacts.ContactsRouter;
import com.bankex.pay.presentation.presenter.ContactInfoPresenter;
import com.bankex.pay.presentation.ui.base.BaseFragment;
import javax.inject.Inject;

/**
 * Full contact info screen.
 * Shows contact name, address, transaction history and allow to send
 * tokens to shown contact.
 */
public class ContactInfoFragment extends BaseFragment implements IContactInfoView {
	@Inject
	@InjectPresenter
	ContactInfoPresenter mContactInfoPresenter;

	@Inject ContactsRouter mContactsRouter;

	@BindView(R.id.toolbar) android.support.v7.widget.Toolbar mToolbar;
	@BindView(R.id.tv_contact_full_name) TextView mContactFullName;
	@BindView(R.id.tv_contact_address) TextView mContactAddress;
	@BindView(R.id.tv_send) TextView mBtnSend;

	private Unbinder mBinder;

	@ProvidePresenter
	public ContactInfoPresenter providePresenter() {
		return mContactInfoPresenter;
	}

	public static ContactInfoFragment newInstance() {
		return new ContactInfoFragment();
	}

	@Override public void onCreate(Bundle savedInstanceState) {
		ContactInfoInjector.getContactsComponent();
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_contact_info, container, false);
		mBinder = ButterKnife.bind(view);
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
		ContactInfoInjector.clearContactsComponent();
	}

	@Override public void popBackStack() {
		mContactsRouter.popBackStack(getActivity());
	}

	@OnClick(R.id.tv_send)
	public void inSendTokensClicked() {
		showMessageToast("Go to send some");
	}

	private void initToolbar() {
		// TODO cannot find toolbar
		//mToolbar.setTitle(string(R.string.contact_info_title));
		mToolbar.setTitle(getResources().getString(R.string.contact_info_title));
		mToolbar.setNavigationIcon(R.drawable.ic_arrow_left_black_24dp);
		mToolbar.setNavigationOnClickListener(v -> mContactsRouter.popBackStack(getActivity()));
		mToolbar.inflateMenu(R.menu.delete_contact_menu);
		mToolbar.setOnMenuItemClickListener(menuItem -> {
			switch (menuItem.getItemId()) {
				case R.id.delete_contact:
					return true;
			}
			return false;
		});
	}
}
