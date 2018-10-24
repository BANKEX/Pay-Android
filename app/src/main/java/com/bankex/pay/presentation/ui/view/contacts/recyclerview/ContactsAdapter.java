package com.bankex.pay.presentation.ui.view.contacts.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bankex.pay.R;
import com.bankex.pay.data.entity.ContactModel;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsViewHolder> {
	@NonNull
	private List<ContactModel> mContacts;

	public ContactsAdapter(@NonNull List<ContactModel> mContacts) {
		this.mContacts = mContacts;
	}

	@NonNull @Override public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_contact, viewGroup, false);
		return new ContactsViewHolder(view);
	}

	@Override public void onBindViewHolder(@NonNull ContactsViewHolder contactsViewHolder, int i) {
		contactsViewHolder.bind(mContacts.get(i));
	}

	@Override public int getItemCount() {
		return mContacts.size();
	}

	public void setContacts(List<ContactModel> contacts) {
		mContacts.clear();
		mContacts.addAll(contacts);
		notifyDataSetChanged();
	}

	public void clearContacts() {
		mContacts.clear();
		notifyDataSetChanged();
	}
}
