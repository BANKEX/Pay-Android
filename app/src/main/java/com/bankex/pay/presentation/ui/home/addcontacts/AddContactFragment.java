package com.bankex.pay.presentation.ui.home.addcontacts;


import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bankex.pay.R;
import com.bankex.pay.domain.analytics.IAnalyticsManager;
import com.bankex.pay.presentation.presenter.contacts.AddContactPresenter;
import com.bankex.pay.presentation.ui.home.adapter.ContactsAdapter;
import com.bankex.pay.presentation.ui.navigation.contacts.IContactsRouter;
import com.bankex.pay.presentation.ui.view.base.BaseFragment;

import javax.inject.Inject;

/**
 * Фрагмент экрана контактов
 *
 * @author Denis Anisimov.
 */
public class AddContactFragment extends BaseFragment implements IAddContactView {

    @Inject
    IContactsRouter mRouter;

    @Inject
    IAnalyticsManager mAnalyticsManager;

    @Inject
    ContactsAdapter adapter;

    private TextInputEditText enterFirstName;
    private TextInputEditText enterSurnameName;
    private TextInputEditText enterAddress;
    private TextView pasteButton;


    @Inject
    @InjectPresenter
    AddContactPresenter mContactsPresenter;

    @ProvidePresenter
    AddContactPresenter providePresenter() {
        return mContactsPresenter;
    }

    /**
     * Возвращаем инстанс фрагмента ContactFragment
     *
     * @return new ContactFragment
     */
    public static AddContactFragment newInstance() {
        return new AddContactFragment();
    }

    public AddContactFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_add_contact, container, false);
        initViews(inflate);
        return inflate;
    }

    private void initViews(View inflate) {
        enterFirstName = inflate.findViewById(R.id.enterFirstName);
        enterSurnameName = inflate.findViewById(R.id.enterSurnameName);
        enterAddress = inflate.findViewById(R.id.enterAddress);
        pasteButton = inflate.findViewById(R.id.paste);
        pasteButton.setOnClickListener(view -> {
            ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            mContactsPresenter.pasteAddress(clipboard);
        });
    }


    @Override
    public void pasteAddress(String address) {
        enterAddress.setText(address);
    }

    @Override
    public void saveContact() {
        getChildFragmentManager().popBackStack();
    }
}
