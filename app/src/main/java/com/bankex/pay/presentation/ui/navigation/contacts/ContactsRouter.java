package com.bankex.pay.presentation.ui.navigation.contacts;

import com.bankex.pay.R;
import com.bankex.pay.presentation.ui.navigation.base.BaseRouter;
import com.bankex.pay.presentation.ui.view.base.BaseFragment;

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
