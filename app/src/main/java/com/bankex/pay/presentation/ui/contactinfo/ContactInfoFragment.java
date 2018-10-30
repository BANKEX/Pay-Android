package com.bankex.pay.presentation.ui.contactinfo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.bankex.pay.R;
import com.bankex.pay.presentation.ui.view.base.BaseFragment;

/**
 * Full contact info screen.
 * Shows contact name, address, transaction history and allow to send
 * tokens to shown contact.
 */
public class ContactInfoFragment extends BaseFragment implements IContactInfoView {

	private Toolbar toolbar;

	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_contact_info, container, false);
		return view;
	}

	@Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		toolbar = view.findViewById(R.id.contacts_toolbar);
		toolbar.setTitle("Contact");
		toolbar.setNavigationIcon(R.drawable.ic_arrow_left_black_24dp);
		toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
	}

	@Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		if (getActivity() != null) {
			getActivity().getMenuInflater().inflate(R.menu.delete_contact_menu, menu);
		}
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.menu.delete_contact_menu:
				onDeleteContactCLicked();
				break;
			case android.R.id.home:
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override public void onDeleteContactCLicked() {

	}

	@Override public void onSendClicked() {

	}
}
