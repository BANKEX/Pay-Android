package com.bankex.pay.utils.share;

import android.content.Context;
import android.support.annotation.StringRes;

/**
 * Interface for utility helper class to share data.
 */
public interface IShareDataUtils {

	/**
	 * Method to share data as text.
	 *
	 * @param context {@link Context}
	 * @param text String value that is needed to share
	 */
	void shareDataLikeText(Context context, String text);

	/**
	 * Method to put text into clipboard.
	 *
	 * @param context {@link Context}
	 * @param data copied text
	 * @param stringResInfo @StringRes of message for toast
	 */
	void copyDataToClipboard(Context context, String data, @StringRes int stringResInfo);

	/**
	 * Method to get copied text from clipboard.
	 *
	 * @param context {@link Context}
	 * @return saved text as CharSequence value
	 */
	CharSequence getCharSequenceFromClipboard(Context context);
}
