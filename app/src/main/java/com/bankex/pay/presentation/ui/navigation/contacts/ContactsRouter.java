package com.bankex.pay.domain.navigation.contacts;

import android.support.v4.app.FragmentActivity;

import com.bankex.pay.R;
import com.bankex.pay.presentation.ui.base.BaseFragment;
import com.bankex.pay.presentation.ui.navigation.BaseRouter;

/**
 * @author Denis Anisimov.
 */
public class ContactsRouter extends BaseRouter implements IContactsRouter {

    @Override
    public void goToAddContacts(BaseFragment fragment) {
        runChildFragmentWithAnimation(
                fragment,
                R.id.fragment_container);
    }

    @Override
    public void goToContactDetails(BaseFragment fragment) {
        runChildFragmentWithAnimation(
                fragment,
                R.id.fragment_container);
    }
}
