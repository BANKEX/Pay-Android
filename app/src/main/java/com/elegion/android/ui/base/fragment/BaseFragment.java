package com.elegion.android.ui.base.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.elegion.android.ui.base.view.ErrorView;
import com.elegion.android.ui.base.view.LoadingView;
import com.elegion.android.ui.base.view.ToastErrorView;
import com.elegion.android.ui.dialog.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Mike
 */
public class BaseFragment extends MvpAppCompatFragment implements LoadingView, ErrorView {
    protected Unbinder mUnbinder;
    protected boolean mIsAfterOnSavedState;

    private Runnable mRunOnResume;
    protected LoadingView mLoadingView;
    protected ErrorView mErrorView;

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIsAfterOnSavedState = false;
        if (mUnbinder == null && view != null) {
            mUnbinder = ButterKnife.bind(this, view);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mLoadingView = getLoadingView();
        mErrorView = getErrorView();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mRunOnResume != null) {
            mRunOnResume.run();
            mRunOnResume = null;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mIsAfterOnSavedState = true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    public void postOnResume(@Nullable Runnable run) {
        if (mIsAfterOnSavedState) {
            mRunOnResume = run;
        } else if (run != null) {
            run.run();
        }
    }

    protected LoadingView getLoadingView() {
        return LoadingDialog.view(getChildFragmentManager());
    }

    protected ErrorView getErrorView() {
        return new ToastErrorView(getActivity());
    }

    @Override
    public void showLoadingIndicator() {
        mLoadingView.showLoadingIndicator();
    }

    @Override
    public void hideLoadingIndicator() {
        mLoadingView.hideLoadingIndicator();
    }

    @Override
    public void showNetworkError() {
        mErrorView.showNetworkError();
    }

    @Override
    public void showUnexpectedError() {
        mErrorView.showUnexpectedError();
    }

    @Override
    public void showErrorMessage(@NonNull String message) {
        mErrorView.showErrorMessage(message);
    }

    @Override
    public void showErrorMessage(@StringRes int message) {
        mErrorView.showErrorMessage(message);
    }

    @Override
    public void hideErrorMessage() {
        mErrorView.hideErrorMessage();
    }
}
