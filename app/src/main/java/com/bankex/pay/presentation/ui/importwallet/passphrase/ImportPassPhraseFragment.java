package com.bankex.pay.presentation.ui.importwallet.passphrase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bankex.pay.R;
import com.bankex.pay.di.importorcreatewallet.ImportOrCreateWalletInjector;
import com.bankex.pay.presentation.navigation.importorcreate.IImportWalletRouter;
import com.bankex.pay.presentation.presenter.ImportPassPhrasePresenter;
import com.bankex.pay.presentation.ui.base.BaseFragment;
import com.bankex.pay.utils.share.IShareDataUtils;
import com.bankex.pay.utils.share.ShareDataUtils;
import javax.inject.Inject;

/**
 * Import wallet by password phrase fragment.
 */
public class ImportPassPhraseFragment extends BaseFragment implements IImportPassPhraseView {
	@Inject
	IImportWalletRouter mImportWalletRouter;

	@Inject
	@InjectPresenter
	ImportPassPhrasePresenter mImportPassPhrasePresenter;

	@BindView(R.id.passphrase_edit_text) EditText mPassPhraseEditText;
	@BindView(R.id.wallet_name_edit_text) EditText mWalletNameEditText;
	@BindView(R.id.import_button) Button mImportButton;

	public static ImportPassPhraseFragment newInstance() {
		return new ImportPassPhraseFragment();
	}

	@ProvidePresenter
	public ImportPassPhrasePresenter provideImportPassPhrasePresenter() {
		return mImportPassPhrasePresenter;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		ImportOrCreateWalletInjector.getImportOrCreateComponent().inject(this);
		super.onCreate(savedInstanceState);
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return setAndBindContentView(inflater, container, R.layout.fragment_import_wallet_pass_phrase);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		ImportOrCreateWalletInjector.clearImportOrCreateComponent();
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

	@OnClick(R.id.paste_button)
	public void onPasteClicked() {
		if (getActivity() != null) {
			IShareDataUtils shareDataUtils = new ShareDataUtils();
			CharSequence sequenceFromClipboard = shareDataUtils.getCharSequenceFromClipboard(getActivity());
			mPassPhraseEditText.setText(sequenceFromClipboard);
		}
	}

	@OnClick(R.id.import_button)
	public void onImportClicked() {
		String passPhrase = mPassPhraseEditText.getText().toString();
		String walletName = mWalletNameEditText.getText().toString();
		mImportPassPhrasePresenter.importWalletFromPassPhrase(passPhrase, walletName);
	}

	@OnTextChanged({ R.id.passphrase_edit_text, R.id.wallet_name_edit_text })
	public void afterTextChanged() {
		setImportButtonEnable(
				mPassPhraseEditText.getText().toString(),
				mWalletNameEditText.getText().toString());
	}

	private void setImportButtonEnable(String passPhraseText, String walletNameText) {
		mImportButton.setEnabled(!TextUtils.isEmpty(passPhraseText) && !TextUtils.isEmpty(walletNameText));
	}
}
