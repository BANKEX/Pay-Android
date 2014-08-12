package com.elegion.android.activity;

import android.app.Activity;
import android.os.Bundle;

import com.elegion.android.R;

/**
 * @author Daniel Serdyukov
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
    }

}
