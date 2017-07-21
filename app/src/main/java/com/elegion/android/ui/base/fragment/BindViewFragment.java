package com.elegion.android.ui.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.pchela.android.ui.base.presenter.BasePresenter;

/**
 * @author mikhail barannikov
 */
public abstract class BindViewFragment<T extends BasePresenter> extends AttachableFragment<T> {

    private Runnable mRunOnResume;
    private boolean mIsAfterOnSavedState;

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews(view);
        mIsAfterOnSavedState = false;
    }

    protected abstract void bindViews(View view);

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

    public void postOnResume(@Nullable Runnable run) {
        if (mIsAfterOnSavedState) {
            mRunOnResume = run;
        } else if (run != null) {
            run.run();
        }
    }
}