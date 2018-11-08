package com.bankex.pay.presentation.ui.importwallet.privatekey;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
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
import com.bankex.pay.presentation.presenter.ImportPrivateKeyPresenter;
import com.bankex.pay.presentation.ui.base.BaseFragment;
import com.bankex.pay.presentation.ui.qr.QRActivity;
import com.bankex.pay.utils.share.IShareDataUtils;
import com.bankex.pay.utils.share.ShareDataUtils;

import javax.inject.Inject;

import static android.app.Activity.RESULT_OK;
import static com.bankex.pay.presentation.ui.qr.QRActivity.QR_SCANNER_RESULT;

/**
 * Экран экспорта по ключу
 *
 * @author Gevork Safaryan on 22.09.2018
 */
public class ImportPrivateKeyFragment extends BaseFragment implements IImportPrivateKeyView {

    private static final int PERMISSION_CAMERA = 123;
    private static final int QR_SCANNER_REQUEST_CODE = 147;

    private EditText mPrivateKeyEditText;
    private EditText mWalletNameEditText;
    private Button mPasteButton;
    private Button mImportButton;
    private View mQrButton;

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
        ImportOrCreateInjector.getImportOrCreateComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_import_wallet_private_key, container, false);
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
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
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

    private void initViews(View view) {
        mPrivateKeyEditText = view.findViewById(R.id.private_key_edit_text);
        mWalletNameEditText = view.findViewById(R.id.wallet_name_edit_text);
        mPasteButton = view.findViewById(R.id.paste_button);
        mQrButton = view.findViewById(R.id.qr_button);
        mImportButton = view.findViewById(R.id.import_button);

        mPrivateKeyEditText.addTextChangedListener(getPrivateKeyTextChangedListener());
        mWalletNameEditText.addTextChangedListener(getWalletNameTextChangedListener());
        mPasteButton.setOnClickListener(getPasteButtonClickListener());
        mQrButton.setOnClickListener(getQrButtonClickListener());
        mImportButton.setOnClickListener(getImportButtonClickListener());
    }

    private void checkCameraPermission() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA},
                        PERMISSION_CAMERA);
            } else {
                startScanQR();
            }
        }
    }

    private void startScanQR() {
        Intent intent = new Intent(getActivity(), QRActivity.class);
        startActivityForResult(intent, QR_SCANNER_REQUEST_CODE);
    }

    private TextWatcher getPrivateKeyTextChangedListener() {
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
                        !TextUtils.isEmpty(mPrivateKeyEditText.getText().toString())) {
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
                mPrivateKeyEditText.setText(sequenceFromClipboard);
            }
        };
    }

    private View.OnClickListener getQrButtonClickListener() {
        return view -> checkCameraPermission();
    }

    private View.OnClickListener getImportButtonClickListener() {
        return view -> {
            String privateKey = mPrivateKeyEditText.getText().toString();
            String walletName = mWalletNameEditText.getText().toString();
            mImportPrivateKeyPresenter.magic(privateKey, walletName);
        };
    }
}
