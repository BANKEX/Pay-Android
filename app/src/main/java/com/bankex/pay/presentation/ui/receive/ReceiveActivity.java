package com.bankex.pay.presentation.ui.receive;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
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
public class ReceiveActivity extends BaseActivity implements IReceiveView {
	@BindView(R.id.add_contact_toolbar) Toolbar mToolbar;
	@BindView(R.id.copy_button) Button mCopyButton;
	@BindView(R.id.qr_image_view) ImageView mQrImageView;

	public static Intent newIntent(Context context) {
		return new Intent(context, ReceiveActivity.class);
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
		shareDataUtils.copyDataToClipboard(this, "адрес токена", R.string.receive_toast_text);
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
		shareDataUtils.shareDataLikeText(this, "адрес токена");
	}

	private void setQrParameters() {
		Bitmap bitmapQr = QRCode.from("ru.wikipedia.org").bitmap();
		mQrImageView.setImageBitmap(bitmapQr);
	}
}
