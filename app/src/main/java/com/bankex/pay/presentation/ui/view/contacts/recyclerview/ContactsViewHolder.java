package com.bankex.pay.presentation.ui.view.contacts.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.bankex.pay.R;
import com.bankex.pay.data.entity.ContactModel;

/**
 * View holder for user Contacts list.
 */
public class ContactsViewHolder extends RecyclerView.ViewHolder {
	@BindView(R.id.iv_contact_avatar) TextView mContactAvatar;
	@BindView(R.id.tv_contact_name) TextView mContactName;

	public ContactsViewHolder(@NonNull View itemView) {
		super(itemView);
	}

	public void bind(ContactModel contact) {
		mContactName.setText(contact.getName());
		mContactAvatar.setText(getContactInitials(contact.getName(), contact.getSurname()));
	}

	private String getContactInitials(String firstName, String surname) {
		StringBuilder initials = new StringBuilder();
		initials.append(firstName.charAt(0)).append(surname.charAt(0));
		return initials.toString();
	}
}
