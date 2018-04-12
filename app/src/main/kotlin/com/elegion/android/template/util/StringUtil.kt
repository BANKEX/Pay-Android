package com.elegion.android.template.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.text.Html
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Patterns

object StringUtil {
    private const val SPACE = ' '
    private const val ROUBLE_SIGN = '\u20BD'
    private val sRobotoTypeface: Typeface? = null

    @JvmStatic
    private val boldSpan: StyleSpan
        get() = StyleSpan(Typeface.BOLD)

    @JvmStatic
    fun getColoredText(context: Context, text: String, @ColorRes color: Int): SpannableStringBuilder {
        val result = SpannableStringBuilder()
        appendAndSetSpan(result, text, getColoredSpan(context, color))
        return result
    }

    @JvmStatic
    fun getBoldText(boldText: String, text: String): SpannableStringBuilder {
        val builder = SpannableStringBuilder()
        appendAndSetSpan(builder, boldText, boldSpan)
        builder.append(" ")
        builder.append(text)
        return builder
    }

    @JvmStatic
    fun getColoredTexts(
        context: Context,
        texts: Array<String>,
        @ColorRes colorRes: IntArray,
        connector: String?
    ): SpannableStringBuilder {
        val builder = SpannableStringBuilder()
        for (i in texts.indices) {
            appendAndSetSpan(builder, texts[i], getColoredSpan(context, colorRes[i]))
            if (connector != null) {
                builder.append(connector)
            }
        }
        return builder
    }

    /** GENERAL METHODS  */

    @JvmStatic
    fun isEmail(email: String?): Boolean = !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()

    @JvmStatic
    fun fromHtml(html: String): CharSequence {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(html)
        }
    }

    @JvmStatic
    private fun appendAndSetSpan(builder: SpannableStringBuilder, text: String, span: Any) {
        if (!TextUtils.isEmpty(text)) {
            val from = builder.length
            builder.append(text)
            val to = builder.length
            builder.setSpan(span, from, to, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }

    @JvmStatic
    private fun getImageSpan(context: Context, @DrawableRes icon: Int): ImageSpan =
        getImageSpan(ContextCompat.getDrawable(context, icon)!!)

    @JvmStatic
    private fun getImageSpan(context: Context, bitmap: Bitmap): ImageSpan =
        getImageSpan(BitmapDrawable(context.resources, bitmap))

    @JvmStatic
    private fun getImageSpan(drawable: Drawable): ImageSpan {
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        return ImageSpan(drawable, ImageSpan.ALIGN_BASELINE)
    }

    @JvmStatic
    private fun getColoredSpan(context: Context, @ColorRes color: Int): ForegroundColorSpan =
        ForegroundColorSpan(ContextCompat.getColor(context, color))

    @JvmStatic
    private fun getSizeSpan(relativeSize: Float): RelativeSizeSpan = RelativeSizeSpan(relativeSize)

    /** END OF GENERAL METHODS  */
}
