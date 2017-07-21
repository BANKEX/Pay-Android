package com.elegion.android.ui.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.elegion.android.util.ToolbarUtils;

/**
 * @author Max Kuznetsov on 5/16/2017.
 */

public class LoginActivity extends AppCompatActivity implements LoginCallback {

    public static Intent makeIntent(@NonNull Context ctx, int mode) {
        return new Intent(ctx, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_single_frame);
        ToolbarUtils.setupToolbar(this);
        ToolbarUtils.setHomeEnabled(this);
        if (savedInstanceState == null) {
            dispatchWithExtras(getIntent().getExtras());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void dispatchWithExtras(Bundle extras) {
        if (extras == null) {
            FragmentUtils.replaceFragment(getSupportFragmentManager(), LoginFragment.newInstance());
            return;
        }
        mMode = extras.getInt(LOGIN_FLAG_EXTRA, 0);
        switch (mMode) {
            case LoginLaunchMode.REGISTRATION_MODE:
                FragmentUtils.replaceFragment(getSupportFragmentManager(),
                        RegistrationFragment.newInstance(false));
                break;
            case LoginLaunchMode.CREATE_TASK_MODE:
                FragmentUtils.replaceFragment(getSupportFragmentManager(),
                        RegistrationFragment.newInstance(true));
                break;
            case LoginLaunchMode.USER_DELETED_MODE:
                FragmentUtils.replaceFragment(getSupportFragmentManager(),
                        LoginFragment.newInstance(true));
                break;
            default:
                FragmentUtils.replaceFragment(getSupportFragmentManager(),
                        LoginFragment.newInstance());
                break;
        }
    }

    public void onLoginCodeSuccess(String phone) {
        ViewUtils.hideKeyboard(this);
        FragmentUtils.replaceFragment(getSupportFragmentManager(), ConfirmPhoneFragment.newInstance(phone), true);
    }

    @Override
    public void onRegistrationCodeSuccess(RegistrationRequest data) {
        FragmentUtils.replaceFragment(getSupportFragmentManager(), ConfirmPhoneFragment.newInstance(data), true);
    }

    @Override
    public void onUserNotFound(String phone) {
        FragmentUtils.replaceFragment(getSupportFragmentManager(), RegistrationFragment.newInstance(false, phone));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CONFIRM_PHONE_INTENT_CODE && resultCode == Activity.RESULT_OK) {
            setResult(RESULT_OK);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPhoneConfirmationSuccess() {
        ViewUtils.hideKeyboard(this);
        if (isNeedToStartMainActivity()) {
            startActivity(MainActivity.makeIntent(this));
        } else {
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ViewUtils.hideKeyboard(this);
    }

    private boolean isNeedToStartMainActivity() {
        return mMode == LoginLaunchMode.USER_DELETED_MODE;
    }
}
