package com.elegion.android.ui.splash;

import android.text.TextUtils;

import com.arellomobile.mvp.InjectViewState;
import com.elegion.android.data.Repository;
import com.elegion.android.ui.base.presenter.BasePresenter;

/**
 * @author mikhail.barannikov on 04.08.2017
 */
@InjectViewState
public class SplashPresenter extends BasePresenter<SplashView> {

    private final Repository mRepository;

    public SplashPresenter(Repository repository) {
        mRepository = repository;
    }

    public void timerFinish() {
        final String loginToken = mRepository.getLoginToken();
        if (TextUtils.isEmpty(loginToken)) {
            getViewState().openLogin();
        } else {
            getViewState().openFeatures();
        }
    }
}
