package com.bankex.pay.presentation.ui.contacts;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bankex.pay.R;
import com.bankex.pay.di.contacts.ContactsInjector;
import com.bankex.pay.domain.model.ContactModel;
import com.bankex.pay.presentation.navigation.contacts.IContactsRouter;
import com.bankex.pay.presentation.presenter.ContactsPresenter;
import com.bankex.pay.presentation.ui.base.BaseFragment;
import com.bankex.pay.presentation.ui.contacts.recyclerview.ContactsAdapter;
import java.util.List;
import javax.inject.Inject;

/**
 * User Contacts screen.
 */
public class ContactsFragment extends BaseFragment implements IContactsView, ContactsListClickListener {
	@Inject
	IContactsRouter mContactsRouter;

	@Inject
	@InjectPresenter
	ContactsPresenter mContactsPresenter;

	@BindView(R.id.contacts_toolbar) Toolbar mToolbar;
	@BindView(R.id.contacts_empty_view) TextView mEmptyView;
	@BindView(R.id.recycler_contacts_list) RecyclerView mContactsList;
	@BindView(R.id.search) SearchView mSearchView;

	@BindString(R.string.contacts_screen_title) String title;

	private ContactsAdapter mContactsAdapter;
	private Unbinder mBinder;

	@ProvidePresenter
	public ContactsPresenter providePresenter() {
		return mContactsPresenter;
	}

	public static ContactsFragment newInstance() {
		return new ContactsFragment();
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		ContactsInjector.getContactsComponent().inject(this);
		super.onCreate(savedInstanceState);
	}

	@Nullable @Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_contacts_list, container, false);
		mBinder = ButterKnife.bind(this, view);
		return view;
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mToolbar.setTitle(title);
		initRecycler();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		mBinder.unbind();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		ContactsInjector.clearContactsComponent();
	}

	@Override public void showContactsList(boolean isShow) {
		mContactsList.setVisibility(isShow ? View.VISIBLE : View.GONE);
	}

	@Override public void showEmptyView(boolean isShow) {
		mEmptyView.setVisibility(isShow ? View.VISIBLE : View.GONE);
	}

	@Override public void setContacts(List<ContactModel> contacts) {
		mContactsAdapter.clearContacts();
		mContactsAdapter.setContacts(contacts);
		mContactsAdapter.notifyDataSetChanged();
	}

	@Override public void onItemClicked(@NonNull String contactId) {
		mContactsRouter.openContactFragment(getActivity(), getContext(), contactId);
	}

	@OnClick(R.id.fab_add_contact)
	public void onAddContactClicked() {
		mContactsRouter.openAddContactFragment(getActivity(), getContext());
	}

	private void initRecycler() {
		mContactsAdapter = new ContactsAdapter(this);
		mContactsList.setLayoutManager(new LinearLayoutManager(getActivity()));
		mContactsList.setHasFixedSize(true);
		mContactsList.setAdapter(mContactsAdapter);
	}
}
