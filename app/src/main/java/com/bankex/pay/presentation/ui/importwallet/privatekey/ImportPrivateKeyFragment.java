package com.bankex.pay.presentation.ui.importwallet.privatekey;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
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
import com.bankex.pay.presentation.presenter.ImportPrivateKeyPresenter;
import com.bankex.pay.presentation.ui.base.BaseFragment;
import com.bankex.pay.presentation.ui.qr.QRActivity;
import com.bankex.pay.utils.share.IShareDataUtils;
import com.bankex.pay.utils.share.ShareDataUtils;
import javax.inject.Inject;

import static android.support.v7.app.AppCompatActivity.RESULT_OK;
import static com.bankex.pay.presentation.ui.qr.QRActivity.QR_SCANNER_RESULT;

/**
 * View for import wallet by private key screen.
 */
public class ImportPrivateKeyFragment extends BaseFragment implements IImportPrivateKeyView {
	private static final int PERMISSION_CAMERA = 123;
	private static final int QR_SCANNER_REQUEST_CODE = 147;

	@BindView(R.id.private_key_edit_text) EditText mPrivateKeyEditText;
	@BindView(R.id.wallet_name_edit_text) EditText mWalletNameEditText;
	@BindView(R.id.paste_button) Button mPasteButton;
	@BindView(R.id.qr_button) View mQrButton;
	@BindView(R.id.import_button) Button mImportButton;

	@Inject
	@InjectPresenter
	ImportPrivateKeyPresenter mImportPrivateKeyPresenter;

	@ProvidePresenter
	public ImportPrivateKeyPresenter provideImportPrivateKeyPresenter() {
		return mImportPrivateKeyPresenter;
	}

	public static ImportPrivateKeyFragment newInstance() {
		return new ImportPrivateKeyFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		ImportOrCreateWalletInjector.getImportOrCreateComponent().inject(this);
		super.onCreate(savedInstanceState);
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return setAndBindContentView(inflater, container, R.layout.fragment_import_wallet_private_key);
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
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == QR_SCANNER_REQUEST_CODE) {
			String text;
			if (resultCode == RESULT_OK) {
				text = data.getStringExtra(QR_SCANNER_RESULT);
				mPrivateKeyEditText.setText(text);
			} else {
				// TODO: 28/09/2018 Убрать или поставить осмысленный текст
				text = "что-то пошло не так";
			}
			Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		switch (requestCode) {
			case PERMISSION_CAMERA:
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					startScanQR();
				}
		}
	}

	@Override
	public void doSomethingGood() {
		Toast.makeText(getActivity(), "good", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void showError(Throwable throwable) {
		Log.e("error ", throwable.getMessage(), throwable);
		Toast.makeText(getActivity(), "bad", Toast.LENGTH_SHORT).show();
	}

	@OnTextChanged({ R.id.private_key_edit_text, R.id.wallet_name_edit_text })
	public void afterTextChanged() {
		setImportButtonEnable(
				mPrivateKeyEditText.getText().toString(),
				mWalletNameEditText.getText().toString());
	}

	@OnClick(R.id.paste_button)
	public void onPasteButtonClicked() {
		if (getActivity() != null) {
			IShareDataUtils shareDataUtils = new ShareDataUtils();
			CharSequence sequenceFromClipboard = shareDataUtils.getCharSequenceFromClipboard(getActivity());
			mPrivateKeyEditText.setText(sequenceFromClipboard);
		}
	}

	@OnClick(R.id.import_button)
	public void onImportButtonClicked() {
		String privateKey = mPrivateKeyEditText.getText().toString();
		String walletName = mWalletNameEditText.getText().toString();
		mImportPrivateKeyPresenter.magic(privateKey, walletName);
	}

	@OnClick(R.id.qr_button)
	public void onQRButtonClicked() {
		checkCameraPermission();
	}

	private void checkCameraPermission() {
		FragmentActivity activity = getActivity();
		if (activity != null) {
			if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
				requestPermissions(new String[] { Manifest.permission.CAMERA }, PERMISSION_CAMERA);
			} else {
				startScanQR();
			}
		}
	}

	private void startScanQR() {
		Intent intent = new Intent(getActivity(), QRActivity.class);
		startActivityForResult(intent, QR_SCANNER_REQUEST_CODE);
	}

	private void setImportButtonEnable(String passPhraseText, String walletNameText) {
		mImportButton.setEnabled((!TextUtils.isEmpty(passPhraseText) && !TextUtils.isEmpty(walletNameText)));
	}
}
