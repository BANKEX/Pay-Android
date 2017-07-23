package com.elegion.android.ui.base.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;

import com.elegion.android.R;
import com.elegion.android.util.FragmentUtils;

public abstract class SingleFragmentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        if (savedInstanceState == null) {
            FragmentUtils.replaceFragment(getSupportFragmentManager(), getFragment());
        }
    }

    @LayoutRes
    protected int getLayout() {
        return R.layout.ac_single_frame;
    }

    public abstract Fragment getFragment();
}
