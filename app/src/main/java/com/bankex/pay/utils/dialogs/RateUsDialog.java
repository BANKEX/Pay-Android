package com.bankex.pay.utils.dialogs;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;
import com.bankex.pay.BuildConfig;
import com.bankex.pay.R;

/**
 * Диалог с призывом поставить приложению рейтинг
 *
 * @author Pavel Apanovskiy on 03.09.2018.
 */
public class RateUsDialog extends DialogFragment {

	private FragmentActivity mActivity;

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		mActivity = getActivity();

		AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
		builder.setTitle(R.string.rate_us_dialog_title);
		builder.setMessage(R.string.rate_us_dialog_message);
		builder.setPositiveButton(R.string.rate_us_dialog_positive, getPositiveButtonClickListener());
		builder.setNegativeButton(R.string.rate_us_dialog_negative, getNegativeButtonClickListener());

		AlertDialog alertDialog = builder.create();
		alertDialog.setCanceledOnTouchOutside(false);

		return alertDialog;
	}

	private void launchMarket() {
		String packageName = BuildConfig.BUILD_TYPE.equals("debug") ? "com.bankex.pay" : mActivity.getPackageName();
		Uri uri = Uri.parse("market://details?id=" + packageName);
		Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
		try {
			startActivity(myAppLinkToMarket);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(getActivity(), " unable to find market app", Toast.LENGTH_LONG).show();
		}
	}

	private DialogInterface.OnClickListener getPositiveButtonClickListener() {
		return (dialogInterface, i) -> launchMarket();
	}

	private DialogInterface.OnClickListener getNegativeButtonClickListener() {
		return (dialogInterface, i) -> dismiss();
	}
}
