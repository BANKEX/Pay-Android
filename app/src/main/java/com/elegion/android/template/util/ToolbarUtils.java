package com.elegion.android.template.util;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.elegion.android.template.R;

/**
 * @author mikhail barannikov
 */
public abstract class ToolbarUtils {

    public static void setupToolbar(@NonNull final AppCompatActivity activity) {
        final Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        if (toolbar != null) {
            activity.setSupportActionBar(toolbar);
        }
    }

    public static void setupToolbar(@NonNull final AppCompatActivity activity, @NonNull String title) {
        final Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle(title);
            activity.setSupportActionBar(toolbar);
        }
    }

    public static void setToolbarTitle(@NonNull final AppCompatActivity activity, @StringRes int title) {
        final ActionBar supportActionBar = activity.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(title);
        }
    }

    public static void setToolbarTitle(@NonNull final AppCompatActivity activity, @NonNull CharSequence title) {
        final ActionBar supportActionBar = activity.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(title);
        }
    }

    public static void setToolbarSubtitle(@NonNull final AppCompatActivity activity, @StringRes int subtitle) {
        final Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        final ActionBar supportActionBar = activity.getSupportActionBar();
        if (supportActionBar != null && toolbar != null) {
            // hack to render toolbar after subtitle is set if called in onActivityCreated
            toolbar.post(() -> supportActionBar.setSubtitle(subtitle));
        }
    }

    public static void setToolbarSubtitle(@NonNull final AppCompatActivity activity, @NonNull String subtitle) {
        final Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        final ActionBar supportActionBar = activity.getSupportActionBar();
        if (supportActionBar != null && toolbar != null) {
            // hack to render toolbar after subtitle is set if called in onActivityCreated
            toolbar.post(() -> supportActionBar.setSubtitle(subtitle));
        }
    }

    public static void setHomeEnabled(@NonNull final AppCompatActivity activity) {
        final ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static void setHomeDisabled(@NonNull final AppCompatActivity activity) {
        final ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }
}
