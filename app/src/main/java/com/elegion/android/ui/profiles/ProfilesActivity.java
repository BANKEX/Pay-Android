package com.elegion.android.ui.profiles;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.elegion.android.ui.base.activity.SingleFragmentActivity;

/**
 * @author Mike
 */
public class ProfilesActivity extends SingleFragmentActivity {

    public static Intent makeIntent(@NonNull Context context) {
        return new Intent(context, ProfilesActivity.class);
    }

    @Override
    public Fragment getFragment() {
        return ProfilesFragment.newInstance();
    }
}
