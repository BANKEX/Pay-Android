package com.elegion.android.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.content.res.AppCompatResources;
import android.text.Editable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public final class ViewUtils {

    private ViewUtils() {
    }


    @IntDef({View.VISIBLE, View.INVISIBLE, View.GONE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Visibility {
    }

    public static float pxToDp(float px) {
        float densityDpi = Resources.getSystem().getDisplayMetrics().densityDpi;
        return px / (densityDpi / 160f);
    }

    public static int dpToPx(float dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    public static int spToPx(float sp) {
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, Resources.getSystem().getDisplayMetrics());
        return px;
    }

    public static int getDisplayWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getDisplayHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static void hideKeyboard(Activity activity) {
        if (activity != null) {
            InputMethodManager imm =
                    (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    public static void setVisibility(@Visibility int visibility, @Nullable View... views) {
        if (views == null || views.length == 0) {
            return;
        }
        for (View v : views) {
            if (v != null && v.getVisibility() != visibility) v.setVisibility(visibility);
        }
    }

    public static void removeOnGlobalLayoutListener(View v, ViewTreeObserver.OnGlobalLayoutListener listener) {
        if (Build.VERSION.SDK_INT < 16) {
            v.getViewTreeObserver().removeGlobalOnLayoutListener(listener);
        } else {
            v.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
        }
    }

    public static void changeEditTextBottomLineColor(EditText editText, @ColorInt int color) {
        editText.getBackground().mutate().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
    }

    @SuppressWarnings("unchecked")
    public static <T> T findView(Activity activity, @IdRes int id) {
        return (T) activity.findViewById(id);
    }

    @SuppressWarnings("unchecked")
    public static <T> T findView(View view, @IdRes int id) {
        return (T) view.findViewById(id);
    }

    public static int getColorWithOpacity(@FloatRange(from = 0, to = 1) float opacity, int color) {
        return Color.argb((int) (255 * opacity), Color.red(color), Color.green(color), Color.blue(color));
    }

    public static <T extends View> List<T> findViews(Activity activity, @IdRes int... ids) {
        List<T> result = new ArrayList<>(ids.length);
        for (int id : ids) {
            result.add(ViewUtils.findView(activity, id));
        }
        return result;
    }

    public static <T extends View> List<T> findViews(View view, @IdRes int... ids) {
        List<T> result = new ArrayList<>(ids.length);
        for (int id : ids) {
            result.add(ViewUtils.findView(view, id));
        }
        return result;
    }

    public static void setCheckedWithoutNotify(CompoundButton button,
                                        boolean isChecked,
                                        CompoundButton.OnCheckedChangeListener listener) {
        button.setOnCheckedChangeListener(null);
        button.setChecked(isChecked);
        button.setOnCheckedChangeListener(listener);
    }

    public static void makeVisibleOrGone(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public static void makeVisibleOrInvisible(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }

    public static void makeVisibleOrGone(boolean visible, View... views) {
        for (View view : views) {
            makeVisibleOrGone(view, visible);
        }
    }

    public static void makeVisibleOrInvisible(boolean visible, View... views) {
        for (View view : views) {
            makeVisibleOrInvisible(view, visible);
        }
    }

    public static Drawable getDrawable(Context context, TypedArray attributeArray, int styleIndex) {
        Drawable result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            result = attributeArray.getDrawable(styleIndex);
        } else {
            int resId = attributeArray.getResourceId(styleIndex, -1);
            result = (resId == -1) ? null : AppCompatResources.getDrawable(context, resId);
        }
        return result;
    }

    public static String getStringText(@NonNull EditText editText) {
        Editable editable = editText.getText();
        return editable == null ? "" : editable.toString();
    }
}