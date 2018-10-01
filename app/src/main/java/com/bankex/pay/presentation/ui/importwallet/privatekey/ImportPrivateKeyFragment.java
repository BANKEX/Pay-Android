package com.bankex.pay.presentation.ui.importwallet.privatekey;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bankex.pay.R;
import com.bankex.pay.presentation.ui.view.base.BaseFragment;
import com.bankex.pay.presentation.ui.view.qr.QRActivity;

import static android.app.Activity.RESULT_OK;
import static com.bankex.pay.presentation.ui.view.qr.QRActivity.QR_SCANNER_RESULT;

/**
 * Экран экспорта по ключу
 *
 * @author Gevork Safaryan on 22.09.2018
 */
public class ImportPrivateKeyFragment extends BaseFragment {

    private static final int PERMISSION_CAMERA = 123;
    private static final int QR_SCANNER_REQUEST_CODE = 147;

    public static ImportPrivateKeyFragment newInstance() {
        return new ImportPrivateKeyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.import_wallet_private_key_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == QR_SCANNER_REQUEST_CODE) {
            String text;
            if (resultCode == RESULT_OK) {
                text = data.getStringExtra(QR_SCANNER_RESULT);
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

    private void initViews(View view) {
        View qrButton = view.findViewById(R.id.qr_button);
        qrButton.setOnClickListener(getQrClickListener());
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

    private View.OnClickListener getQrClickListener() {
        return view -> checkCameraPermission();
    }
}
