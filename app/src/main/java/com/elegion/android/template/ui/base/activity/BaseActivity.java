package com.elegion.android.template.ui.base.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.MvpAppCompatActivity;

import butterknife.ButterKnife;

/**
 * @author mikhail.funikov
 */
public abstract class BaseActivity extends MvpAppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
    }

    @LayoutRes
    protected abstract int getLayout();
}
