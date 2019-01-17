package com.bankex.pay.presentation.navigation.contacts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import com.bankex.pay.R;
import com.bankex.pay.presentation.navigation.base.BaseRouter;
import com.bankex.pay.presentation.ui.addcontact.AddContactFragment;
import com.bankex.pay.presentation.ui.contactinfo.ContactInfoFragmentBuilder;
import com.bankex.pay.presentation.ui.deletecontact.DeleteContactDialog;
import com.bankex.pay.presentation.ui.deletecontact.DeleteContactDialogBuilder;
import javax.inject.Inject;

/**
 * Am implementation of {@link IContactsRouter}.
 */
public class ContactsRouter extends BaseRouter implements IContactsRouter {

	@Inject public ContactsRouter() {
	}

	/**
	 * {@inheritDoc }
	 */
	// TODO delete unnecessary Context
	@Override public void openAddContactFragment(FragmentActivity activity, Context context) {
		runFragmentWithAnimation(activity, AddContactFragment.newInstance(), R.id.fragment_container);
	}

	/**
	 * {@inheritDoc }
	 */
	@Override public void openContactFragment(FragmentActivity activity, Context context, String contactId) {
		runFragmentWithAnimation(activity, ContactInfoFragmentBuilder.newContactInfoFragment(contactId), R.id.fragment_container);
	}

	/**
	 * {@inheritDoc }
	 */
	@Override public void openDeleteContactDialog(FragmentActivity activity, Context context, @NonNull String contactId) {
		DialogFragment dialogFragment = DeleteContactDialogBuilder.newDeleteContactDialog(contactId);
		dialogFragment.show(activity.getSupportFragmentManager(), DeleteContactDialog.class.getName());
	}
}
