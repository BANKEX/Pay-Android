package com.bankex.pay.utils.share;

import android.content.Context;
import android.support.annotation.StringRes;

/**
 * Interface for utility class to work with data sharing
 *
 * @author Pavel Apanovskiy on 17.06.2018.
 */
public interface IShareDataUtils {

	/**
	 * Method for generating intent for sharing
	 *
	 * @param context context
	 * @param text текст для шаринга
	 */
	void shareDataLikeText(Context context, String text);

	/**
	 * Method to put data text on clipboard
	 *
	 * @param context context
	 * @param data text data to cope
	 * @param stringResInfo message StringRes to show on the Toast
	 */
	void copyDataToClipboard(Context context, String data, @StringRes int stringResInfo);
}
