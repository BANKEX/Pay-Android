package com.bankex.pay.presentation.ui.contactinfo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
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
import com.bankex.pay.domain.model.ContactModel;
import com.bankex.pay.presentation.navigation.contacts.ContactsRouter;
import com.bankex.pay.presentation.presenter.ContactInfoPresenter;
import com.bankex.pay.presentation.ui.base.BaseFragment;
import com.hannesdorfmann.fragmentargs.FragmentArgs;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import icepick.State;
import javax.inject.Inject;

/**
 * Full contact info screen.
 * Shows contact name, address, transaction history and allow to send
 * tokens to chosen contact.
 */
@FragmentWithArgs
public class ContactInfoFragment extends BaseFragment implements IContactInfoView {
	@Arg @State String contactId;

	@Inject
	@InjectPresenter
	ContactInfoPresenter mContactInfoPresenter;

	@Inject ContactsRouter mContactsRouter;

	@BindView(R.id.toolbar) android.support.v7.widget.Toolbar mToolbar;
	@BindView(R.id.tv_contact_address) TextView mContactAddress;
	@BindView(R.id.transactions_empty_view) TextView mEmptyTransactions;
	@BindView(R.id.recycler_transactions_list) RecyclerView mTransactionHistoryList;

	private Unbinder mBinder;

	@ProvidePresenter
	public ContactInfoPresenter providePresenter() {
		return mContactInfoPresenter;
	}

	@Override public void onCreate(Bundle savedInstanceState) {
		ContactInfoInjector.getContactsComponent().inject(this);
		FragmentArgs.inject(this);
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_contact_info, container, false);
		mBinder = ButterKnife.bind(this, view);
		return view;
	}

	@Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initToolbar();
		setData(mContactInfoPresenter.setContactInfo(contactId));
		showContactsList(false);
		showEmptyView(true);
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

	@Override public void showContactsList(boolean isShow) {
		mTransactionHistoryList.setVisibility(isShow ? View.VISIBLE : View.GONE);
	}

	@Override public void showEmptyView(boolean isShow) {
		mEmptyTransactions.setVisibility(isShow ? View.VISIBLE : View.GONE);
	}

	@OnClick(R.id.fab_send_tokens)
	public void OnSendTokensClicked() {
		showMessageToast("Go to send some");
	}

	private void initToolbar() {
		mToolbar.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
		mToolbar.setNavigationOnClickListener(v -> mContactsRouter.popBackStack(getActivity()));
		mToolbar.inflateMenu(R.menu.contact_info_toolbar_menu);
		mToolbar.setOnMenuItemClickListener(menuItem -> {
			switch (menuItem.getItemId()) {
				case R.id.edit_contact:
					showMessageToast("On edit clicked");
					return true;
				case R.id.share_contact:
					showMessageToast("On share clicked");
					return true;
				case R.id.delete_contact:
					showMessageToast("On delete clicked");
					return true;
			}
			return false;
		});
	}

	private void setData(ContactModel contactInfo) {
		mToolbar.setTitle(contactInfo.getName() + contactInfo.getSurname());
		mContactAddress.setText(contactInfo.getAddress());
	}
}
