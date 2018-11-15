package com.bankex.pay.presentation.ui.receiveaddress;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bankex.pay.R;
import com.bankex.pay.presentation.ui.base.BaseActivity;
import com.bankex.pay.utils.share.IShareDataUtils;
import com.bankex.pay.utils.share.ShareDataUtils;
import net.glxn.qrgen.android.QRCode;

/**
 * View for receive address.
 */
public class ReceiveAddressActivity extends BaseActivity implements IReceiveAddressView {
	@BindView(R.id.add_contact_toolbar) Toolbar mToolbar;
	@BindView(R.id.qr_image_view) ImageView mQrImageView;

	public static Intent newIntent(Context context) {
		return new Intent(context, ReceiveAddressActivity.class);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_receive_tokens);
		ButterKnife.bind(this);

		setToolbarParameters();
		setQrParameters();
	}

	@OnClick(R.id.copy_button)
	public void onCopyButtonClicked() {
		// TODO: 30/09/2018 Реализовать подстановку валидных данных
		IShareDataUtils shareDataUtils = new ShareDataUtils();
		shareDataUtils.copyDataToClipboard(
				this,
				string(R.string.receive_activity_token_address_title),
				R.string.receive_toast_text);
	}

	private void setToolbarParameters() {
		mToolbar.setTitle(getString(R.string.receive_toolbar_title));
		mToolbar.inflateMenu(R.menu.share_menu);
		setSupportActionBar(mToolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
		mToolbar.setOnMenuItemClickListener(menuItem -> {
			switch (menuItem.getItemId()) {
				case android.R.id.home:
					finish();
					return true;
				case R.id.action_share:
					shareActionSelected();
					return true;
				default:
					return false;
			}
		});
	}

	private void shareActionSelected() {
		// TODO: 30/09/2018 Реализовать подстановку валидных данных
		IShareDataUtils shareDataUtils = new ShareDataUtils();
		shareDataUtils.shareDataLikeText(this, string(R.string.receive_activity_token_address_title));
	}

	private void setQrParameters() {
		Bitmap bitmapQr = QRCode.from("ru.wikipedia.org").bitmap();
		mQrImageView.setImageBitmap(bitmapQr);
	}
}
