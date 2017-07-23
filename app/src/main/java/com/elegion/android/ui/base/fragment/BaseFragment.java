package com.elegion.android.ui.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Mike
 */
public class BaseFragment extends MvpAppCompatFragment {
    protected Unbinder mUnbinder;
    protected boolean mIsAfterOnSavedState;

    private Runnable mRunOnResume;

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIsAfterOnSavedState = false;
        if (mUnbinder == null && view != null) {
            mUnbinder = ButterKnife.bind(this, view);
        }
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
}
