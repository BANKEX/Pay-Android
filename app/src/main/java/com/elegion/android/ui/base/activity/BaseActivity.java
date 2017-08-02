package com.elegion.android.ui.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;

import com.arellomobile.mvp.MvpAppCompatActivity;

import butterknife.ButterKnife;

/**
 * @author mikhail.funikov
 */
public abstract class BaseActivity extends MvpAppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
