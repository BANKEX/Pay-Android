package com.elegion.android.template.ui.features;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.elegion.android.template.R;
import com.elegion.android.template.ui.base.activity.SingleFragmentActivity;
import com.elegion.android.template.util.ToolbarUtils;

/**
 * @author Mike
 */
public class FeaturesActivity extends SingleFragmentActivity {

    public static Intent makeIntent(@NonNull Context context) {
        return new Intent(context, FeaturesActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ToolbarUtils.setupToolbar(this);
        ToolbarUtils.setHomeEnabled(this);
    }

    @LayoutRes
    protected int getLayout() {
        return R.layout.ac_toolbar_single_frame;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public Fragment getFragment() {
        return FeaturesFragment.newInstance();
    }
}
