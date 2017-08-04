package com.elegion.android.ui.splash;

import android.text.TextUtils;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.elegion.android.data.Repository;

/**
 * @author mikhail.barannikov on 04.08.2017
 */
@InjectViewState
public class SplashPresenter extends MvpPresenter<SplashView> {

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
