package com.bankex.pay.presentation.ui.contacts.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bankex.pay.R;
import com.bankex.pay.domain.model.ContactModel;
import com.bankex.pay.presentation.navigation.contacts.IContactsRouter;
import javax.inject.Inject;

/**
 * View holder for user contacts list.
 */
public class ContactItemViewHolder extends RecyclerView.ViewHolder {
	@Inject
	IContactsRouter mContactsRouter;

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

	//@OnClick(R.id.item_contact_container)
	//public void onItemClicked() {
	//	mContactsRouter.openContactFragment(mContext, mContext, null);
	//}
}
