package com.bankex.pay.presentation.ui.mainscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bankex.pay.BuildConfig;
import com.bankex.pay.R;
import com.bankex.pay.di.mainscreen.MainScreenInjector;
import com.bankex.pay.presentation.ui.navigation.home.IHomeRouter;
import com.bankex.pay.presentation.presenter.mainscreen.MainScreenPresenter;
import com.bankex.pay.presentation.ui.home.SettingsFragment;
import com.bankex.pay.presentation.ui.home.WalletFragment;
import com.bankex.pay.presentation.ui.lockscreen.LockScreenActivity;
import com.bankex.pay.presentation.ui.onboarding.OnboardingActivity;
import com.bankex.pay.presentation.ui.setpin.SetPinActivity;
import com.bankex.pay.presentation.ui.view.base.BaseActivity;
import com.bankex.pay.utils.preferences.SharedPreferencesUtils;
import com.google.firebase.iid.FirebaseInstanceId;

import javax.inject.Inject;


/**
 * Корневая активити
 */
public class MainScreenActivity extends BaseActivity implements IMainScreenView {

    private static final int ONBOARDING_REQUEST = 0;

    @Inject
    IHomeRouter mRouter;

    @Inject
    @InjectPresenter
    MainScreenPresenter mMainScreenPresenter;

    @ProvidePresenter
    public MainScreenPresenter providePresenter() {
        return mMainScreenPresenter;
    }

    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MainScreenInjector.getMainScreenComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (BuildConfig.DEBUG) {
            Log.d("Firebase", "token " + FirebaseInstanceId.getInstance().getToken());
        }
        initViews();
        runFragment(WalletFragment.newInstance());
        checkOnboardingStatus();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MainScreenInjector.clearMainScreenComponent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ONBOARDING_REQUEST:
                if (resultCode == RESULT_OK) {
                    Intent intent = SetPinActivity.newIntent(this);
                    startActivityForResult(intent, ONBOARDING_REQUEST);
                }
                break;
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void showOnboarding() {
        startOnboarding();
    }

    @Override
    public void showLockScreen() {
        startLockScreen();
    }

    private void initViews() {
        mBottomNavigationView = findViewById(R.id.home_navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void checkOnboardingStatus() {
        boolean onboardingPreferenceStatus = SharedPreferencesUtils.getOnboardingPreferenceStatus(this);
        mMainScreenPresenter.checkOnboardingStatus(onboardingPreferenceStatus);
    }

    private void startOnboarding() {
        Intent intent = OnboardingActivity.newIntent(this);
        startActivityForResult(intent, ONBOARDING_REQUEST);
    }

    private void startLockScreen() {
        Intent intent = LockScreenActivity.newIntent(this);
        startActivityForResult(intent, ONBOARDING_REQUEST);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_wallet:
                hideKeyboard();
                if (!item.isChecked()) {
                    mRouter.goToWalletTab(this, WalletFragment.newInstance());
                }
                return true;
            case R.id.navigation_history:
                hideKeyboard();
                if (!item.isChecked()) {
                    mRouter.goToHistoryTab(this);
                }
                return true;
            case R.id.navigation_contacts:
                hideKeyboard();
                if (!item.isChecked()) {
                    mRouter.goToWalletTab(this, WalletFragment.newInstance());
                }
                return true;
            case R.id.navigation_settings:
                hideKeyboard();
                if (!item.isChecked()) {
                    mRouter.goToSettingsTab(this, SettingsFragment.newInstance());
                }
                return true;
        }
        return false;
    };
}
