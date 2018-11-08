package com.bankex.pay.presentation.ui.contacts.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bankex.pay.R;
import com.bankex.pay.domain.model.ContactModel;
import com.bankex.pay.presentation.ui.contacts.ContactsListClickListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for user contacts list.
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactItemViewHolder> {
	private List<ContactModel> mContacts;
	private ContactsListClickListener mOnItemClickListener;

	public ContactsAdapter(ContactsListClickListener onItemClickListener) {
		this.mOnItemClickListener = onItemClickListener;
		this.mContacts = new ArrayList<>();
	}

	@NonNull @Override public ContactItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_contact, viewGroup, false);
		return new ContactItemViewHolder(view);
	}

	@Override public void onBindViewHolder(@NonNull ContactItemViewHolder contactsViewHolder, int i) {
		contactsViewHolder.bind(mContacts.get(i), mOnItemClickListener);
	}

	@Override public int getItemCount() {
		return mContacts.size();
	}

	public void setContacts(List<ContactModel> contacts) {
		mContacts.addAll(contacts);
		notifyDataSetChanged();
	}

	public void clearContacts() {
		mContacts.clear();
		notifyDataSetChanged();
	}
}
