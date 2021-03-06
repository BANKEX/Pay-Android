package com.bankex.pay.presentation.ui.qr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.bankex.pay.R;
import com.bankex.pay.presentation.ui.base.BaseActivity;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * View for QR-scanner screen.
 */
public class QRActivity extends BaseActivity implements ZXingScannerView.ResultHandler {
    public static final String QR_SCANNER_RESULT = "QR_SCANNER_RESULT";

    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        setupToolbar();
        initViews();
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void handleResult(Result result) {
        Intent intent = new Intent();
        intent.putExtra(QR_SCANNER_RESULT, result.getText());
        setResult(RESULT_OK, intent);
        finish();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.add_contact_toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initViews() {
        ViewGroup contentFrame = findViewById(R.id.content_frame);
        mScannerView = new ZXingScannerView(this);
        contentFrame.addView(mScannerView);
    }
}
