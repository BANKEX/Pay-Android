package com.elegion.android.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.elegion.android.R;
import com.elegion.android.view.LoadingView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Daniel Serdyukov
 */
public class LoadingDialog extends DialogFragment {

    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    @NonNull
    public static LoadingView view(@NonNull FragmentManager fm) {
        return new LoadingView() {

            private AtomicBoolean mWaitForHide = new AtomicBoolean();

            @Override
            public void showLoadingIndicator() {
                if (mWaitForHide.compareAndSet(false, true)) {
                    new LoadingDialog().show(fm, LoadingDialog.class.getName());
                }
            }

            @Override
            public void hideLoadingIndicator() {
                if (mWaitForHide.compareAndSet(true, false)) {
                    HANDLER.post(new HideTask(fm));
                }
            }
        };
    }

    @NonNull
    public static LoadingView view(@NonNull Fragment fragment) {
        return view(fragment.getFragmentManager());
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity(), R.style.AppTheme_Dialog)
                .setView(R.layout.fr_loading)
                .create();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setCancelable(false);
    }

    private static class HideTask implements Runnable {

        private final Reference<FragmentManager> mFmRef;

        private int mAttempts = 10;

        public HideTask(@NonNull FragmentManager fm) {
            mFmRef = new WeakReference<>(fm);
        }

        @Override
        public void run() {
            HANDLER.removeCallbacks(this);
            final FragmentManager fm = mFmRef.get();
            if (fm != null) {
                final LoadingDialog dialog = (LoadingDialog) fm.findFragmentByTag(LoadingDialog.class.getName());
                if (dialog != null) {
                    dialog.dismissAllowingStateLoss();
                } else if (--mAttempts >= 0) {
                    HANDLER.postDelayed(this, 300);
                }
            }
        }

    }

}
