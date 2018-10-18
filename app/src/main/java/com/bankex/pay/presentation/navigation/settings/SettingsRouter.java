package com.bankex.pay.presentation.navigation.settings;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import com.bankex.pay.R;
import com.bankex.pay.presentation.navigation.base.BaseRouter;

/**
 * Settings screen router realisation {@link ISettingsRouter}
 *
 * @author Pavel Apanovskiy on 08.09.2018.
 */
public class SettingsRouter extends BaseRouter implements ISettingsRouter {
	private static final String EMAIL = "pay@bankex.com";
	private static final String TWITTER_USER_NAME = "BANKEX";
	private static final String FACEBOOK_USER_NAME = "BankExchange";
	private static final String FACEBOOK_PAGE_ID = "428217707302739";

	/**
	 * Method to open e-mail from settings screen to write to developers
	 */
	@Override
	public void goToEmail(Context context) {
		Intent intent = new Intent(Intent.ACTION_SENDTO);
		intent.setDataAndType(Uri.parse("mailto:" + EMAIL), "text/plain");

		try {
			context.startActivity(Intent.createChooser(intent, "Send Email"));
		} catch (ActivityNotFoundException e) {
			Toast.makeText(context, context.getText(R.string.settings_screen_error_no_email), Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * Method to open developers company twitter
	 */
	@Override
	public void goToTwitter(Context context) {
		Uri uri = Uri.parse("https://twitter.com/" + TWITTER_USER_NAME);
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		context.startActivity(intent);
	}

	/**
	 * Method to open developers company facebook page
	 */
	@Override
	public void goToFacebook(Context context) {
		Uri uri = Uri.parse("https://facebook.com/" + FACEBOOK_USER_NAME);
		Intent intent;
		try {
			context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
			intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/" + FACEBOOK_PAGE_ID));
		} catch (Exception e) {
			intent = new Intent(Intent.ACTION_VIEW, uri);
		}
		context.startActivity(intent);
	}
}
