package com.elegion.android.bankex.util

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.ColorInt
import android.support.annotation.FloatRange
import android.support.annotation.IdRes
import android.support.annotation.IntDef
import android.support.v4.content.ContextCompat
import android.support.v7.content.res.AppCompatResources
import android.util.TypedValue
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.TextView
import java.util.ArrayList

object ViewUtils {
    private const val DP_DENSITY = 160f
    private const val MAX_OPACITY = 255f

    @JvmStatic
    val displayWidth: Int
        get() = Resources.getSystem().displayMetrics.widthPixels

    @JvmStatic
    val displayHeight: Int
        get() = Resources.getSystem().displayMetrics.heightPixels

    @IntDef(View.VISIBLE, View.INVISIBLE, View.GONE)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Visibility

    @JvmStatic
    fun pxToDp(px: Float): Float {
        val densityDpi = Resources.getSystem().displayMetrics.densityDpi.toFloat()
        return px / (densityDpi / DP_DENSITY)
    }

    @JvmStatic
    fun dpToPx(dp: Float): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().displayMetrics).toInt()

    @JvmStatic
    fun spToPx(sp: Float): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, Resources.getSystem().displayMetrics).toInt()

    @JvmStatic
    fun hideKeyboard(activity: Activity?) {
        if (activity != null) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            val window = activity.window
            if (imm != null && window != null) {
                var currentFocus = window.currentFocus
                if (currentFocus == null) {
                    currentFocus = window.decorView.findFocus()
                }
                if (currentFocus != null) {
                    imm.hideSoftInputFromWindow(currentFocus.windowToken, 0)
                    currentFocus.clearFocus()
                }
            }
        }
    }

    @JvmStatic
    fun hideKeyboard(context: Context?, view: View) {
        if (context != null) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    @JvmStatic
    fun setVisibility(@Visibility visibility: Int, vararg views: View) {
        if (views == null || views.size == 0) {
            return
        }
        for (v in views) {
            if (v != null && v.visibility != visibility) {
                v.visibility = visibility
            }
        }
    }

    @JvmStatic
    fun makeVisibleOrGone(visible: Boolean, vararg views: View) {
        if (views == null || views.size == 0) {
            return
        }
        setVisibility(if (visible) View.VISIBLE else View.GONE, *views)
    }

    @JvmStatic
    fun makeVisibleOrInvisible(visible: Boolean, vararg views: View) {
        if (views == null || views.size == 0) {
            return
        }
        setVisibility(if (visible) View.VISIBLE else View.INVISIBLE, *views)
    }

    @JvmStatic
    fun removeOnGlobalLayoutListener(v: View, listener: ViewTreeObserver.OnGlobalLayoutListener) {
        v.viewTreeObserver.removeOnGlobalLayoutListener(listener)
    }

    @JvmStatic
    fun changeEditTextBottomLineColor(editText: EditText, @ColorInt color: Int) {
        editText.background.mutate().setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
    }

    @JvmStatic
    fun setEditTextCursorColor(view: EditText, @ColorInt color: Int) {
        // Get the cursor resource id
        var field = TextView::class.java.getDeclaredField("mCursorDrawableRes")
        field.isAccessible = true
        val drawableResId = field.getInt(view)

        // Get the editor
        field = TextView::class.java.getDeclaredField("mEditor")
        field.isAccessible = true
        val editor = field.get(view)

        // Get the drawable and set a color filter
        val drawable = ContextCompat.getDrawable(view.context, drawableResId)
        drawable!!.setColorFilter(color, PorterDuff.Mode.SRC_IN)
        val drawables = arrayOf(drawable, drawable)

        // Set the drawables
        field = editor.javaClass.getDeclaredField("mCursorDrawable")
        field.isAccessible = true
        field.set(editor, drawables)
    }

    @JvmStatic
    fun getColorWithOpacity(@FloatRange(from = 0.0, to = 1.0) opacity: Float, @ColorInt color: Int): Int =
        Color.argb((MAX_OPACITY * opacity).toInt(), Color.red(color), Color.green(color), Color.blue(color))

    @JvmStatic
    fun setCheckedWithoutNotify(button: CompoundButton, isChecked: Boolean, listener: CompoundButton.OnCheckedChangeListener) {
        button.setOnCheckedChangeListener(null)
        button.isChecked = isChecked
        button.setOnCheckedChangeListener(listener)
    }

    @JvmStatic
    fun getEditTextString(editText: EditText) = editText.text?.toString() ?: ""

    /**
     * REGION PROJECT SPECIFIC TOOLS
     */

    @JvmStatic
    fun <T> findView(activity: Activity, @IdRes id: Int): T = activity.findViewById<View>(id) as T

    @JvmStatic
    fun <T> findView(view: View, @IdRes id: Int): T = view.findViewById<View>(id) as T

    @JvmStatic
    fun <T : View> findViews(activity: Activity, @IdRes vararg ids: Int): List<T> {
        val result = ArrayList<T>(ids.size)
        for (id in ids) {
            result.add(ViewUtils.findView(activity, id))
        }
        return result
    }

    @JvmStatic
    fun <T : View> findViews(view: View, @IdRes vararg ids: Int): List<T> {
        val result = ArrayList<T>(ids.size)
        for (id in ids) {
            result.add(ViewUtils.findView(view, id))
        }
        return result
    }

    @JvmStatic
    fun getTypedArrayDrawable(context: Context, attributeArray: TypedArray, styleIndex: Int): Drawable? {
        val result: Drawable?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            result = attributeArray.getDrawable(styleIndex)
        } else {
            val resId = attributeArray.getResourceId(styleIndex, -1)
            result = if (resId == -1) null else AppCompatResources.getDrawable(context, resId)
        }
        return result
    }

    /**
     * ENDREGION PROJECT SPECIFIC TOOLS
     */
}