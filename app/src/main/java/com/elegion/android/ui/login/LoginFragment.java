package com.elegion.android.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.elegion.android.R;
import com.elegion.android.data.Repository;
import com.elegion.android.ui.base.fragment.BaseNoInternetFragment;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * @author Mike
 */

public class LoginFragment extends BaseNoInternetFragment implements LoginView {
    @BindView(R.id.top_layout)
    View mTopLayout;

    @InjectPresenter
    LoginPresenter mPresenter;

    @ProvidePresenter
    LoginPresenter providePresenter() {
        return new LoginPresenter(Repository.get(getActivity()));
    }

    public static Fragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_login, container);
    }

    @OnClick(R.id.login_btn)
    public void onLoginClick() {
        mPresenter.login();
    }

    @OnTextChanged(R.id.email)
    public void onEmailChanged(CharSequence text) {
        mPresenter.setEmail(text.toString());
    }

    @OnTextChanged(R.id.password)
    public void onPasswordChanged(CharSequence text) {
        mPresenter.setPassword(text.toString());
    }

    @Override
    protected View[] getViews() {
        return new View[] {mTopLayout};
    }

    @Override
    public void tryAgain() {
        mPresenter.login();
    }

    @Override
    public void loginSuccessful() {

    }
}
