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
import com.bankex.pay.presentation.presenter.mainscreen.MainScreenPresenter;
import com.bankex.pay.presentation.ui.base.BaseActivity;
import com.bankex.pay.presentation.ui.home.WalletFragment;
import com.bankex.pay.presentation.ui.navigation.IBankexRouter;
import com.bankex.pay.presentation.ui.onboarding.OnboardingActivity;
import com.bankex.pay.utils.SharedPreferencesUtils;
import com.google.firebase.iid.FirebaseInstanceId;

import javax.inject.Inject;


/**
 * Корневая активити
 */
public class MainScreenActivity extends BaseActivity implements IMainScreenView {

    @Inject
    IBankexRouter mRouter;

    @Inject
    @InjectPresenter
    MainScreenPresenter mMainScreenPresenter;

    private BottomNavigationView mBottomNavigationView;

    @ProvidePresenter
    public MainScreenPresenter providePresenter() {
        return mMainScreenPresenter;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MainScreenInjector.getMainScreenComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
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

    /**
     * {@inheritDoc }
     */
    @Override
    public void showOnboarding() {
        startOnboarding();
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
        startActivity(intent);
    }
}
