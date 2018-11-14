package com.bankex.pay.presentation.navigation.settings;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import com.bankex.pay.R;
import com.bankex.pay.presentation.navigation.base.BaseRouter;

/**
 * An implementation of {@link ISettingsRouter}.
 */
public class SettingsRouter extends BaseRouter implements ISettingsRouter {
	private static final String EMAIL = "pay@bankex.com";
	private static final String TWITTER_URL = "https://twitter.com/";
	private static final String TWITTER_USER_NAME = "BANKEX";
	private static final String FACEBOOK_URL = "https://facebook.com/";
	private static final String FACEBOOK_USER_NAME = "BankExchange";
	private static final String FACEBOOK_PAGE_ID = "428217707302739";
	private static final String FACEBOOK_PACKAGE_NAME = "com.facebook.katana";

	private static final String CHOOSER_TITLE = "Send Email";

	/**
	 * {@inheritDoc }
	 */
	@Override
	public void goToEmail(Context context) {
		Intent intent = new Intent(Intent.ACTION_SENDTO);
		intent.setDataAndType(Uri.parse("mailto:" + EMAIL), "text/plain");

		try {
			context.startActivity(Intent.createChooser(intent, CHOOSER_TITLE));
		} catch (ActivityNotFoundException e) {
			Toast.makeText(context, R.string.settings_error_no_email_app_message, Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * {@inheritDoc }
	 */
	@Override
	public void goToTwitter(Context context) {
		Uri uri = Uri.parse(TWITTER_URL + TWITTER_USER_NAME);
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		context.startActivity(intent);
	}

	/**
	 * {@inheritDoc }
	 */
	@Override
	public void goToFacebook(Context context) {
		Uri uri = Uri.parse(FACEBOOK_URL + FACEBOOK_USER_NAME);
		Intent intent;
		try {
			context.getPackageManager().getPackageInfo(FACEBOOK_PACKAGE_NAME, 0);
			intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/" + FACEBOOK_PAGE_ID));
		} catch (Exception e) {
			intent = new Intent(Intent.ACTION_VIEW, uri);
		}
		context.startActivity(intent);
	}
}
