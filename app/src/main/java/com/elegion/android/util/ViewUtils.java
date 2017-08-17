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
import android.support.v4.content.ContextCompat;
import android.support.v7.content.res.AppCompatResources;
import android.text.Editable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public final class ViewUtils {
    private static final float DP_DENSITY = 160f;
    private static final float MAX_OPACITY = 255;

    private ViewUtils() {
    }


    @IntDef({View.VISIBLE, View.INVISIBLE, View.GONE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Visibility {
    }

    public static float pxToDp(float px) {
        float densityDpi = Resources.getSystem().getDisplayMetrics().densityDpi;
        return px / (densityDpi / DP_DENSITY);
    }

    public static int dpToPx(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    public static int spToPx(float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, Resources.getSystem().getDisplayMetrics());
    }

    public static int getDisplayWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getDisplayHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static void hideKeyboard(@Nullable Activity activity) {
        if (activity != null) {
            final InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            final Window window = activity.getWindow();
            if (imm != null && window != null) {
                View currentFocus = window.getCurrentFocus();
                if (currentFocus == null) {
                    currentFocus = window.getDecorView().findFocus();
                }
                if (currentFocus != null) {
                    imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
                    currentFocus.clearFocus();
                }
            }
        }
    }

    public static void hideKeyboard(@Nullable Context context, @NonNull View view) {
        if (context != null) {
            final InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public static void setVisibility(@Visibility int visibility, @Nullable View... views) {
        if (views == null || views.length == 0) {
            return;
        }
        for (View v : views) {
            if (v != null && v.getVisibility() != visibility) {
                v.setVisibility(visibility);
            }
        }
    }

    public static void makeVisibleOrGone(boolean visible, @Nullable View... views) {
        if (views == null || views.length == 0) {
            return;
        }
        setVisibility(visible ? View.VISIBLE : View.GONE, views);
    }

    public static void makeVisibleOrInvisible(boolean visible, @Nullable View... views) {
        if (views == null || views.length == 0) {
            return;
        }
        setVisibility(visible ? View.VISIBLE : View.INVISIBLE, views);
    }

    public static void removeOnGlobalLayoutListener(@NonNull View v, @NonNull ViewTreeObserver.OnGlobalLayoutListener listener) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            v.getViewTreeObserver().removeGlobalOnLayoutListener(listener);
        } else {
            v.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
        }
    }

    public static void changeEditTextBottomLineColor(@NonNull EditText editText, @ColorInt int color) {
        editText.getBackground().mutate().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
    }

    public static void setEditTextCursorColor(@NonNull EditText view, @ColorInt int color) {
        try {
            // Get the cursor resource id
            Field field = TextView.class.getDeclaredField("mCursorDrawableRes");
            field.setAccessible(true);
            int drawableResId = field.getInt(view);

            // Get the editor
            field = TextView.class.getDeclaredField("mEditor");
            field.setAccessible(true);
            Object editor = field.get(view);

            // Get the drawable and set a color filter
            final Drawable drawable = ContextCompat.getDrawable(view.getContext(), drawableResId);
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
            Drawable[] drawables = {drawable, drawable};

            // Set the drawables
            field = editor.getClass().getDeclaredField("mCursorDrawable");
            field.setAccessible(true);
            field.set(editor, drawables);
        } catch (Exception ignored) {
            // ignored
        }
    }

    public static int getColorWithOpacity(@FloatRange(from = 0, to = 1) float opacity, @ColorInt int color) {
        return Color.argb((int) (MAX_OPACITY * opacity), Color.red(color), Color.green(color), Color.blue(color));
    }

    public static void setCheckedWithoutNotify(@NonNull CompoundButton button, boolean isChecked,
                                               @NonNull CompoundButton.OnCheckedChangeListener listener) {
        button.setOnCheckedChangeListener(null);
        button.setChecked(isChecked);
        button.setOnCheckedChangeListener(listener);
    }

    public static String getEditTextString(@NonNull EditText editText) {
        Editable editable = editText.getText();
        return editable == null ? "" : editable.toString();
    }

    /**
     * REGION PROJECT SPECIFIC TOOLS
     */

    @SuppressWarnings("unchecked")
    public static <T> T findView(@NonNull Activity activity, @IdRes int id) {
        return (T) activity.findViewById(id);
    }

    @SuppressWarnings("unchecked")
    public static <T> T findView(View view, @IdRes int id) {
        return (T) view.findViewById(id);
    }

    public static <T extends View> List<T> findViews(@NonNull Activity activity, @IdRes int... ids) {
        List<T> result = new ArrayList<>(ids.length);
        for (int id : ids) {
            result.add(ViewUtils.findView(activity, id));
        }
        return result;
    }

    public static <T extends View> List<T> findViews(@NonNull View view, @IdRes int... ids) {
        List<T> result = new ArrayList<>(ids.length);
        for (int id : ids) {
            result.add(ViewUtils.findView(view, id));
        }
        return result;
    }

    public static Drawable getTypedArrayDrawable(@NonNull Context context, @NonNull TypedArray attributeArray, int styleIndex) {
        Drawable result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            result = attributeArray.getDrawable(styleIndex);
        } else {
            int resId = attributeArray.getResourceId(styleIndex, -1);
            result = (resId == -1) ? null : AppCompatResources.getDrawable(context, resId);
        }
        return result;
    }

    /**
     * ENDREGION PROJECT SPECIFIC TOOLS
     */
}