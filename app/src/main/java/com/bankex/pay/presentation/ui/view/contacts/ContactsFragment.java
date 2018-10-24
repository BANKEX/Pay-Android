package com.bankex.pay.presentation.ui.view.contacts;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bankex.pay.R;
import com.bankex.pay.data.entity.ContactModel;
import com.bankex.pay.di.contacts.ContactsInjector;
import com.bankex.pay.presentation.presenter.contacts.ContactsPresenter;
import com.bankex.pay.presentation.ui.navigation.contacts.IContactsRouter;
import com.bankex.pay.presentation.ui.view.base.BaseFragment;
import com.bankex.pay.presentation.ui.view.contacts.recyclerview.ContactsAdapter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * User Contacts screen
 */
public class ContactsFragment extends BaseFragment implements IContactsView {
	@Inject
	IContactsRouter mContactsRouter;

	@Inject
	@InjectPresenter
	ContactsPresenter mContactsPresenter;

	private android.support.v7.widget.Toolbar mToolbar;
	private RecyclerView mContactsList;
	private ContactsAdapter mContactsAdapter;
	private TextView mEmptyView;

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

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.contacts_fragment_layout, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		mToolbar = view.findViewById(R.id.contacts_toolbar);
		mContactsList = view.findViewById(R.id.recycler_contacts_list);
		mEmptyView = view.findViewById(R.id.contacts_empty_view);

		mToolbar.setTitle("Contacts");

		List<ContactModel> contacts = new ArrayList<>();
		// Recycler
		mContactsList.setLayoutManager(new LinearLayoutManager(getActivity()));
		mContactsAdapter = new ContactsAdapter(contacts);
		mContactsList.setAdapter(mContactsAdapter);
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

	@Override
	public void showMessage() {
		Toast.makeText(getActivity(), "Message", Toast.LENGTH_SHORT).show();
	}
}
