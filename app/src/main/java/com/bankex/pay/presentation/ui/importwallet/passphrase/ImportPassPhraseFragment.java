package com.bankex.pay.presentation.ui.importwallet.passphrase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bankex.pay.R;
import com.bankex.pay.di.importorcreate.ImportOrCreateInjector;
import com.bankex.pay.presentation.presenter.importwallet.passphrase.ImportPassPhrasePresenter;
import com.bankex.pay.presentation.navigation.importorcreate.IImportWalletRouter;
import com.bankex.pay.presentation.ui.base.BaseFragment;
import com.bankex.pay.utils.share.IShareDataUtils;
import com.bankex.pay.utils.share.ShareDataUtils;

import javax.inject.Inject;

/**
 * Экран экспорта по фразе
 *
 * @author Gevork Safaryan on 22.09.2018
 */
public class ImportPassPhraseFragment extends BaseFragment implements IImportPassPhraseView {

    @Inject
    IImportWalletRouter mImportWalletRouter;

    @Inject
    @InjectPresenter
    ImportPassPhrasePresenter mImportPassPhrasePresenter;

    private Button mImportButton;
    private EditText mPassPhraseEditText;
    private EditText mWalletNameEditText;
    private Button mPasteButton;

    public static ImportPassPhraseFragment newInstance() {
        return new ImportPassPhraseFragment();
    }

    @ProvidePresenter
    public ImportPassPhrasePresenter provideImportPassPhrasePresenter() {
        return mImportPassPhrasePresenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ImportOrCreateInjector.getImportOrCreateComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.import_wallet_pass_phrase_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ImportOrCreateInjector.clearImportOrCreateComponent();
    }

    @Override
    public void doSomethingGood() {
        Toast.makeText(getActivity(), "Import Success", Toast.LENGTH_SHORT).show();
        mImportWalletRouter.startMainActivityScreen(getActivity());
    }

    @Override
    public void showError(Throwable throwable) {
        Log.e("error ", throwable.getMessage(), throwable);
        Toast.makeText(getActivity(), "Import Error", Toast.LENGTH_SHORT).show();
    }

    private void initViews(View view) {
        mImportButton = view.findViewById(R.id.import_button);
        mPassPhraseEditText = view.findViewById(R.id.passphrase_edit_text);
        mWalletNameEditText = view.findViewById(R.id.wallet_name_edit_text);
        mPasteButton = view.findViewById(R.id.paste_button);

        mPassPhraseEditText.addTextChangedListener(getPassPhraseTextChangedListener());
        mWalletNameEditText.addTextChangedListener(getWalletNameTextChangedListener());
        mPasteButton.setOnClickListener(getPasteButtonClickListener());
        mImportButton.setOnClickListener(getImportButtonClickListener());
    }

    private TextWatcher getPassPhraseTextChangedListener() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //do nothing
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable.toString()) &&
                        !TextUtils.isEmpty(mWalletNameEditText.getText().toString())) {
                    mImportButton.setEnabled(true);
                } else {
                    mImportButton.setEnabled(false);
                }
            }
        };
    }

    private TextWatcher getWalletNameTextChangedListener() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //do nothing
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable.toString()) &&
                        !TextUtils.isEmpty(mPassPhraseEditText.getText().toString())) {
                    mImportButton.setEnabled(true);
                } else {
                    mImportButton.setEnabled(false);
                }
            }
        };
    }

    private View.OnClickListener getPasteButtonClickListener() {
        return view -> {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                IShareDataUtils shareDataUtils = new ShareDataUtils();
                CharSequence sequenceFromClipboard = shareDataUtils.getCharSequenceFromClipboard(activity);
                mPassPhraseEditText.setText(sequenceFromClipboard);
            }
        };
    }

    private View.OnClickListener getImportButtonClickListener() {
        return view -> {
            String passPhrase = mPassPhraseEditText.getText().toString();
            String walletName = mWalletNameEditText.getText().toString();
            mImportPassPhrasePresenter.importWalletFromPassPhrase(passPhrase, walletName);
        };
    }
}
