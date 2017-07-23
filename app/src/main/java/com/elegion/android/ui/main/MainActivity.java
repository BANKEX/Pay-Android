package com.elegion.android.ui.main;

import android.os.Bundle;

import com.elegion.android.R;
import com.elegion.android.ui.base.activity.BaseActivity;

import butterknife.ButterKnife;

/**
 * @author Mike
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        ButterKnife.bind(this);
    }
}
