package com.bankex.pay.presentation.ui.walletinfo;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bankex.pay.R;
import com.bankex.pay.presentation.presenter.walletinfo.WalletInfoPresenter;
import com.bankex.pay.presentation.ui.navigation.send.SendRouter;
import com.bankex.pay.presentation.ui.view.base.BaseActivity;

import javax.inject.Inject;

public class WalletInfoActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener, View.OnClickListener, IWalletInfoView {

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.6f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION = 200;

    @Inject
    @InjectPresenter
    WalletInfoPresenter mWalletInfoPresenter;

    @ProvidePresenter
    public WalletInfoPresenter providePresenter() {
        return mWalletInfoPresenter;
    }

    private boolean mIsTheTitleVisible = false;
    private boolean mIsTheTitleContainerVisible = true;

    //private CoordinatorLayout titleContainer;
    //private TextView title;
    private AppBarLayout appBarLayout;
    private RelativeLayout toolbarAdderessContent;
    private RelativeLayout appBarContainer;
    private Toolbar toolbar;

    private TextView send;
    private TextView recieve;
    private TextView balance;
    private TextView balanceUSD;
    private TextView address;
    private TextView addressName;
    private TextView currency;

    private TextView addressNameTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_info);
        bindActivity();
        toolbar();
        appBarLayout.addOnOffsetChangedListener(this);
        startAlphaAnimation(toolbarAdderessContent, 0, View.INVISIBLE);
        mWalletInfoPresenter.fetchBalance("");
    }

    private void bindActivity() {
        toolbar = findViewById(R.id.toolbar);
        toolbarAdderessContent = findViewById(R.id.toolbarAdderessContent);
        send = findViewById(R.id.send);
        recieve = findViewById(R.id.recieve);
        send.setOnClickListener(this);
        recieve.setOnClickListener(this);
        addressNameTitle = findViewById(R.id.addressNameTitle);
        //title = findViewById(R.id.addressTitle);
        balance = findViewById(R.id.balance);
        balanceUSD = findViewById(R.id.balanceUSD);
        address = findViewById(R.id.address);
        addressName = findViewById(R.id.addressName);

        //titleContainer = findViewById(R.id.main_content);
        appBarContainer = findViewById(R.id.app_bar_content);
        appBarLayout = findViewById(R.id.appbar);
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
    }

    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

            if (!mIsTheTitleVisible) {
                startAlphaAnimation(toolbarAdderessContent, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }

        } else {

            if (mIsTheTitleVisible) {
                startAlphaAnimation(toolbarAdderessContent, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if (mIsTheTitleContainerVisible) {
                startAlphaAnimation(appBarContainer, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }

        } else {

            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(appBarContainer, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }

    public static void startAlphaAnimation(View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send:
                new SendRouter().open(this);
                break;
            case R.id.recieve:

                break;
        }
    }

    @Override
    public void showError(String error, String description, Throwable throwable) {

   /*     mPlaceHolderWrongType.setVisibility(View.GONE);
        mPlaceHolderNoResults.setVisibility(View.VISIBLE);
        mErrorText.setText(error);
        mErrorDescription.setText(description);
        if (throwable != null) {
            mAnalyticsManager.searchRequestErrorResult(throwable.toString());
        }*/
    }


    @Override
    public void setBalanceToAppBar(String value) {
        balance.setText(value);
    }

    @Override
    public void setBalanceInUSD(String balanceInUSD) {
        balanceUSD.setText(balanceInUSD);
    }
}
