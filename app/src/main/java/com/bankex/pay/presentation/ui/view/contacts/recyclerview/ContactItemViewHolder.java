package com.bankex.pay.presentation.ui.view.contacts.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bankex.pay.R;
import com.bankex.pay.domain.model.ContactModel;

/**
 * View holder for user contacts list.
 */
public class ContactItemViewHolder extends RecyclerView.ViewHolder {
	@BindView(R.id.tv_letter_group) TextView mGroupMarker;
	@BindView(R.id.iv_avatar_initials) TextView mAvatarInitials;
	@BindView(R.id.tv_contact_name) TextView mContactName;

	public ContactItemViewHolder(@NonNull View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}

	public void bind(ContactModel contact) {
		mGroupMarker.setText(contact.getGroupLetterMarker());
		mAvatarInitials.setText(String.valueOf(contact.getName().charAt(0)));
		mContactName.setText(contact.getName());
	}

	@OnClick(R.id.item_contact_container)
	public void onItemClicked() {

	}
}
