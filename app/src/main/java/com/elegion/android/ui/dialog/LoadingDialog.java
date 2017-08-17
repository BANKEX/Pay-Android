package com.elegion.android.ui.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Window;

import com.elegion.android.R;
import com.elegion.android.ui.base.view.LoadingView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Mike
 */
public class LoadingDialog extends DialogFragment {

    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    @NonNull
    public static LoadingView view(@NonNull FragmentManager fm) {
        return new LoadingView() {
            private final AtomicBoolean mWaitForHide = new AtomicBoolean();

            @Override
            public void showLoadingIndicator() {
                if (mWaitForHide.compareAndSet(false, true)) {
                    HANDLER.removeCallbacks(null);
                    if (fm.findFragmentByTag(LoadingDialog.class.getName()) == null) {
                        final LoadingDialog loadingDialog = new LoadingDialog();
                        loadingDialog.show(fm, LoadingDialog.class.getName());
                    }
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

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final ProgressDialog dialog = new ProgressDialog(getActivity(), R.style.AppTheme_Dialog);
        final Window window = dialog.getWindow();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP && window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        dialog.setMessage(getActivity().getString(R.string.loading));
        return dialog;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setCancelable(false);
    }

    private static class HideTask implements Runnable {
        private static final int MAX_ATTEMPTS = 10;
        private static final int POST_DELAY = 300;

        private final Reference<FragmentManager> mFmRef;

        private int mAttempts = MAX_ATTEMPTS;

        HideTask(@NonNull FragmentManager fm) {
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
                    HANDLER.postDelayed(this, POST_DELAY);
                }
            }
        }
    }
}