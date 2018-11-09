package com.bankex.pay.presentation.navigation.contacts;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import com.bankex.pay.R;
import com.bankex.pay.presentation.navigation.base.BaseRouter;
import com.bankex.pay.presentation.ui.addcontact.AddContactFragment;
import com.bankex.pay.presentation.ui.contactinfo.ContactInfoFragmentBuilder;
import com.bankex.pay.presentation.ui.deletecontact.DeleteContactDialog;
import javax.inject.Inject;

/**
 * Am Implementation of {@link IContactsRouter}.
 */
public class ContactsRouter extends BaseRouter implements IContactsRouter {

	@Inject public ContactsRouter() {
	}

	@Override public void openAddContactFragment(FragmentActivity activity, Context context) {
		runFragmentWithAnimation(activity, AddContactFragment.newInstance(), R.id.fragment_container);
	}

	@Override public void openContactFragment(FragmentActivity activity, Context context, String contactId) {
		runFragmentWithAnimation(activity, ContactInfoFragmentBuilder.newContactInfoFragment(contactId), R.id.fragment_container);
	}

	@Override public void openDeleteContactDialog(FragmentActivity activity, Context context, @NonNull String contactId) {
		Bundle args = new Bundle();
		args.putString("contact_id", contactId);

		DialogFragment dialogFragment = new DeleteContactDialog();
		dialogFragment.setArguments(args);
		dialogFragment.show(activity.getSupportFragmentManager(), DeleteContactDialog.class.getName());
	}
}
