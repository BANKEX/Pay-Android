package com.elegion.android.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Patterns;

/**
 * Created by azret.magometov on 30-Jan-17.
 */

public class StringUtil {

    private StringUtil() {
    }

    public static SpannableStringBuilder getColoredText(Context context, String text, @ColorRes int color) {
        SpannableStringBuilder result = new SpannableStringBuilder();
        appendAndSetSpan(result, text, getColoredSpan(context, color));
        return result;
    }

    public static SpannableStringBuilder getBoldText(String boldText, String text) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        appendAndSetSpan(builder, boldText, getBoldSpan());
        builder.append(" ");
        builder.append(text);
        return builder;
    }

    public static SpannableStringBuilder getColoredTexts(Context context, String[] texts, @ColorRes int[] colorRes, @Nullable String connector) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        for (int i = 0; i < texts.length; i++) {
            appendAndSetSpan(builder, texts[i], getColoredSpan(context, colorRes[i]));
            if (connector != null) {
                builder.append(connector);
            }
        }
        return builder;
    }

    /** GENERAL METHODS **/

    public static boolean isEmail(@Nullable String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static CharSequence fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    private static void appendAndSetSpan(SpannableStringBuilder builder, String text, Object span) {
        if (!TextUtils.isEmpty(text)) {
            int from = builder.length();
            builder.append(text);
            int to = builder.length();
            builder.setSpan(span, from, to, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    private static ImageSpan getImageSpan(@NonNull Context context, @DrawableRes int icon) {
        final Drawable d = ContextCompat.getDrawable(context, icon);
        return getImageSpan(context, d);
    }

    private static ImageSpan getImageSpan(@NonNull Context context, @NonNull Bitmap bitmap) {
        final Drawable d = new BitmapDrawable(context.getResources(), bitmap);
        return getImageSpan(context, d);
    }

    private static ImageSpan getImageSpan(@NonNull Context context, @NonNull Drawable drawable) {
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        return new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
    }

    @NonNull
    private static ForegroundColorSpan getColoredSpan(Context context, int color) {
        return new ForegroundColorSpan(ContextCompat.getColor(context, color));
    }

    @NonNull
    private static StyleSpan getBoldSpan() {
        return new StyleSpan(Typeface.BOLD);
    }

    @NonNull
    private static RelativeSizeSpan getSizeSpan(float relativeSize) {
        return new RelativeSizeSpan(relativeSize);
    }

    /** END OF GENERAL METHODS **/
}
