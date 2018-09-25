package com.bankex.pay.presentation.ui.home;

import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.bankex.pay.domain.models.ContactModel;
import com.bankex.pay.presentation.ui.base.BaseView;

import java.util.List;

/**
 * @author Denis Anisimov.
 */
@StateStrategyType(AddToEndStrategy.class)
public interface IContactView extends BaseView {

    void assemblyContactList();

    void assemblyStickyIndexAndFastScroller();

    char[] convertToIndexList(List<ContactModel> list);

    /**
     * Load the given contacts in the list
     */
    void loadContacts(List<ContactModel> contacts);

    /**
     * Scroll the list to the contact with the given name
     */
    void scrollToContact(String name);
}
