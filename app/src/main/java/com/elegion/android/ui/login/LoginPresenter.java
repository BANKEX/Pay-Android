package com.elegion.android.ui.login;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.elegion.android.data.Repository;

/**
 * @author Mike
 */
@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {
    private String mEmail;
    private String mPassword;
    private Repository mRepository;

    public LoginPresenter(Repository repository) {
        mRepository = repository;
    }

    public void login() {
        // TODO: fix the view problem: we need loading, error, no internet, empty stub views here
        getViewState().loginSuccessful();
//        mRepository.login(mEmail, mPassword)
//                .compose(RxUtils::async)
//                .compose(RxUtils.loading(getViewState()))
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public void setPassword(String password) {
        mPassword = password;
    }
}
