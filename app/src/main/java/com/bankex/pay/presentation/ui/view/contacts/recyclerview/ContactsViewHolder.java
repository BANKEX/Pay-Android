package com.bankex.pay.presentation.ui.view.contacts.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.bankex.pay.R;
import com.bankex.pay.data.entity.Contact;

/**
 * View holder for user Contacts list
 */
public class ContactsViewHolder extends RecyclerView.ViewHolder {
	private TextView mContactAvatar;
	private TextView mContactName;

	public ContactsViewHolder(@NonNull View itemView) {
		super(itemView);
		mContactAvatar = itemView.findViewById(R.id.iv_contact_avatar);
		mContactName = itemView.findViewById(R.id.tv_contact_name);
	}

	public void bind(Contact contact) {
		mContactName.setText(contact.getName());
		mContactAvatar.setText(getContactInitials(contact.getName(), contact.getSurname()));
	}

	private String getContactInitials(String firstName, String surname) {
		StringBuilder initials = new StringBuilder();
		initials.append(firstName.charAt(0)).append(surname.charAt(0));
		return initials.toString();
	}
}
