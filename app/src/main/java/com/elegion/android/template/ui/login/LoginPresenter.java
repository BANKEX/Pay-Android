package com.elegion.android.template.ui.login;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.elegion.android.template.data.Repository;
import com.elegion.android.template.data.remote.response.LoginResponse;
import com.elegion.android.template.ui.base.presenter.BasePresenter;
import com.elegion.android.template.util.RxUtils;

import rx.Subscription;

/**
 * @author Mike
 */
@InjectViewState
public class LoginPresenter extends BasePresenter<LoginView> {
    private String mEmail;
    private String mPassword;
    private Repository mRepository;
    private Subscription mLoginSubscription;

    public LoginPresenter(@NonNull Repository repository) {
        mRepository = repository;
    }

    public void login() {
        if (RxUtils.isNullOrUnsubscribed(mLoginSubscription)) {
            removeSubscription(mLoginSubscription);
            mLoginSubscription = mRepository.login(mEmail, mPassword)
                    .compose(RxUtils::async)
                    .compose(RxUtils.loading(getViewState()))
                    .compose(RxUtils.errorTransformer(getViewState(), mRepository, getViewState()))
                    .subscribe(this::handleLogin, RxUtils::errorLogE);
            addSubscription(mLoginSubscription);
        }
    }

    private void handleLogin(LoginResponse response) {
        mRepository.saveLoginToken(response.getToken());
        getViewState().loginSuccessful();
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public void setPassword(String password) {
        mPassword = password;
    }
}
