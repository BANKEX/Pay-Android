package com.bankex.pay.presentation.view.receiveshareaddress;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.bankex.pay.R;
import com.bankex.pay.presentation.view.base.BaseActivity;
import com.bankex.pay.utils.share.IShareDataUtils;
import com.bankex.pay.utils.share.ShareDataUtils;
import net.glxn.qrgen.android.QRCode;

/**
 * Receive or share address activity
 *
 * @author Pavel Apanovskiy on 30/09/2018.
 */
public class ReceiveShareAddressActivity extends BaseActivity implements IReceiveShareAddressView {

	private Toolbar mToolbar;
	private Button mCopyButton;
	private ImageView mQrImageView;

	public static Intent newIntent(Context context) {
		return new Intent(context, ReceiveShareAddressActivity.class);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_receive_share_address);
		initViews();
		setToolbarParameters();
		setQrParameters();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.share_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				return true;
			case R.id.action_share:
				shareActionSelected();
				return true;
			default:
				return false;
		}
	}

	private void initViews() {
		mToolbar = findViewById(R.id.toolbar);
		mCopyButton = findViewById(R.id.copy_button);
		mQrImageView = findViewById(R.id.qr_image_view);

		mCopyButton.setOnClickListener(getCopyButtonClickListener());
	}

	private void setToolbarParameters() {
		mToolbar.setTitle(getString(R.string.receive_toolbar_title));
		setSupportActionBar(mToolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
	}

	private void shareActionSelected() {
		// TODO: 30/09/2018 Реализовать подстановку валидных данных
		IShareDataUtils shareDataUtils = new ShareDataUtils();
		shareDataUtils.shareDataLikeText(this, "адрес токена");
	}

	private void setQrParameters() {
		Bitmap bitmapQr = QRCode.from("ru.wikipedia.org").bitmap();
		mQrImageView.setImageBitmap(bitmapQr);
	}

	private View.OnClickListener getCopyButtonClickListener() {
		return view -> {
			// TODO: 30/09/2018 Реализовать подстановку валидных данных
			IShareDataUtils shareDataUtils = new ShareDataUtils();
			shareDataUtils.copyDataToClipboard(ReceiveShareAddressActivity.this, "адрес токена", R.string.receive_toast_text);
		};
	}
}
