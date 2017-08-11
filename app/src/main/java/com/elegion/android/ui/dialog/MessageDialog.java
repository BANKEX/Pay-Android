package com.elegion.android.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.elegion.android.R;
import com.elegion.android.util.ViewUtils;

/**
 * @author mikhail barannikov
 */
public class MessageDialog extends DialogFragment {
    private static final String ARG_TITLE = "ARG_TITLE";
    private static final String ARG_MESSAGE = "ARG_MESSAGE";
    private static final String ARG_CONFIRM_BUTTON = "ARG_CONFIRM_BUTTON";
    private static final String ARG_CANCEL_BUTTON = "ARG_CANCEL_BUTTON";

    private TextView mTitle;
    private TextView mMessage;
    private TextView mConfirmAction;
    private TextView mCancelAction;
    private Callback mCallback;

    @Nullable
    private String mTitleText;
    @Nullable
    private String mMessageText;
    @Nullable
    private String mConfirmText;
    @Nullable
    private String mCancelText;

    private final View.OnClickListener mPrimaryButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mCallback != null) {
                mCallback.onConfirmButtonClick();
            }
            getDialog().dismiss();
        }
    };

    private final View.OnClickListener mSecondaryButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mCallback != null) {
                mCallback.onCancelButtonClick();
            }
            getDialog().dismiss();
        }
    };

    public static void show(@NonNull FragmentManager fm,
                            @Nullable String title,
                            @Nullable String message,
                            @Nullable String confirm,
                            @Nullable String cancel) {
        show(fm, title, message, confirm, cancel, MessageDialog.class.getName());
    }

    public static void show(@NonNull FragmentManager fm,
                            @Nullable String title,
                            @Nullable String message,
                            @Nullable String confirm,
                            @Nullable String cancel,
                            @NonNull String tag) {
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_MESSAGE, message);
        args.putString(ARG_CONFIRM_BUTTON, confirm);
        args.putString(ARG_CANCEL_BUTTON, cancel);
        MessageDialog dialog = new MessageDialog();
        dialog.setArguments(args);
        dialog.show(fm, tag);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        resolveCallback(activity);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        resolveCallback(context);
    }

    public void resolveCallback(Context context) {
        if (getParentFragment() instanceof Callback) {
            mCallback = (Callback) getParentFragment();
        } else if (context instanceof Callback) {
            mCallback = (Callback) context;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_message_dialog, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews(view);
        initArguments();

        // set texts
        bindTextOrHide(mTitle, mTitleText);
        bindTextOrHide(mMessage, mMessageText);
        bindTextOrHide(mConfirmAction, mConfirmText);
        bindTextOrHide(mCancelAction, mCancelText);
    }

    private void bindTextOrHide(TextView textView, @Nullable String text) {
        if (!TextUtils.isEmpty(text)) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(text);
        } else {
            textView.setVisibility(View.GONE);
        }
    }

    private void bindViews(View view) {
        mTitle = ViewUtils.findView(view, R.id.title);
        mMessage = ViewUtils.findView(view, R.id.message);
        mConfirmAction = ViewUtils.findView(view, R.id.primary_action);
        mCancelAction = ViewUtils.findView(view, R.id.secondary_action);
    }

    private void initArguments() {
        final Bundle args = getArguments();
        if (args != null) {
            mTitleText = args.getString(ARG_TITLE);
            mMessageText = args.getString(ARG_MESSAGE);
            mConfirmText = args.getString(ARG_CONFIRM_BUTTON);
            mCancelText = args.getString(ARG_CANCEL_BUTTON);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        final int width = getResources().getDimensionPixelSize(R.dimen.dialog_message_width);
        changeWindowSizes(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        mConfirmAction.setOnClickListener(mPrimaryButtonListener);
        mCancelAction.setOnClickListener(mSecondaryButtonListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        mConfirmAction.setOnClickListener(null);
        mCancelAction.setOnClickListener(null);
    }

    private void changeWindowSizes(int width, int height) {
        final Window window = getDialog().getWindow();
        if (window != null) {
            // hack to change window width
            window.setLayout(width, height);
        }
    }

    @Override
    public int getTheme() {
        return R.style.AppTheme_Dialog;
    }

    public interface Callback {
        void onConfirmButtonClick();

        void onCancelButtonClick();
    }
}