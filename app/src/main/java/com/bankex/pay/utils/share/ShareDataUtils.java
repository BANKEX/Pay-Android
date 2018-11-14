package com.bankex.pay.utils.share;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.StringRes;
import android.widget.Toast;
import com.bankex.pay.R;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * An implementation of {@link IShareDataUtils}.
 */
public class ShareDataUtils implements IShareDataUtils {

	@Override
	public void shareDataLikeText(Context context, String tokenAddress) {
		String appName = context.getString(R.string.app_name);
		Intent sharingIntent = new Intent(Intent.ACTION_SEND);
		sharingIntent.setType("text/plain");
		sharingIntent.putExtra(Intent.EXTRA_SUBJECT, appName);
		sharingIntent.putExtra(Intent.EXTRA_TEXT, tokenAddress);
		context.startActivity(Intent.createChooser(sharingIntent, appName));
	}

	@Override
	public void copyDataToClipboard(Context context, String data, @StringRes int stringResInfo) {
		ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
		ClipData clipData = ClipData.newPlainText(context.getString(stringResInfo), data);

		if (clipboardManager != null) {
			clipboardManager.setPrimaryClip(clipData);
			Toast.makeText(context, context.getString(stringResInfo), Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public CharSequence getCharSequenceFromClipboard(Context context) {
		CharSequence charSequence = null;
		ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
		if (clipboardManager != null) {
			ClipData primaryClip = clipboardManager.getPrimaryClip();
			if (primaryClip != null && primaryClip.getItemCount() > 0) {
				ClipData.Item item = primaryClip.getItemAt(0);
				charSequence = item.coerceToText(context);
			}
		}
		return charSequence;
	}
}
