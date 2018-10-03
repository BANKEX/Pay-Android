package com.bankex.pay.presentation.presenter.contacts;

import android.content.ClipData;
import android.content.ClipboardManager;

import com.bankex.pay.data.reporitories.ContactRepository;
import com.bankex.pay.presentation.presenter.base.BasePresenter;
import com.bankex.pay.presentation.ui.home.addcontacts.IAddContactView;

/**
 * Презентер для добавления конткта
 *
 * @author Denis Anisimov.
 */
public class AddContactPresenter extends BasePresenter<IAddContactView> {

    public AddContactPresenter(ContactRepository contactRepository) {

    }

    /**
     * Метод вставки адресса из клипборда
     * @param clipboard
     */
    public void pasteAddress(ClipboardManager clipboard) {
        if (clipboard == null) return;
        ClipData clip = clipboard.getPrimaryClip();
        if (clip == null) return;
        ClipData.Item item = clip.getItemAt(0);
        if (item == null) return;
        CharSequence textToPaste = item.getText();
        if (textToPaste == null) return;
        getViewState().pasteAddress((String) textToPaste);
    }
}
