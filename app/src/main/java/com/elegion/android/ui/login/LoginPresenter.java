package com.elegion.android.ui.login;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.elegion.android.data.Repository;
import com.elegion.android.data.remote.response.LoginResponse;
import com.elegion.android.util.RxUtils;

import rx.Subscription;

/**
 * @author Mike
 */
@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {
    private String mEmail;
    private String mPassword;
    private Repository mRepository;
    private Subscription mSubscription;

    public LoginPresenter(Repository repository) {
        mRepository = repository;
    }

    public void login() {
        if (RxUtils.isNullOrUnsubscribed(mSubscription)) {
            mSubscription = mRepository.login(mEmail, mPassword)
                    .compose(RxUtils::async)
                    .compose(RxUtils.loading(getViewState()))
                    .compose(RxUtils.errorTransformer(getViewState(), mRepository, getViewState()))
                    .subscribe(this::handleLogin, RxUtils::errorNoAction);
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
