package com.elegion.android.template.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.elegion.android.template.R;
import com.elegion.android.template.util.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by marat.taychinov
 */
public class EmptyView extends LinearLayout {
    @BindView(R.id.empty_view_icon)
    ImageView mEmptyViewIcon;
    @BindView(R.id.empty_view_text)
    TextView mEmptyViewText;
    @BindView(R.id.empty_view_button)
    @Nullable
    Button mEmptyViewButton;

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, getLayout(), this);
        ButterKnife.bind(this);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.EmptyView,
                0, 0);

        try {
            int resourceId = a.getResourceId(R.styleable.EmptyView_icon, 0);
            if (resourceId != 0) {
                mEmptyViewIcon.setImageResource(resourceId);
            }
            mEmptyViewText.setText(a.getString(R.styleable.EmptyView_text));
            String buttonText = a.getString(R.styleable.EmptyView_buttonText);
            if (!TextUtils.isEmpty(buttonText)) {
                getButton().setText(buttonText);
                getButton().setVisibility(VISIBLE);
            } else {
                getButton().setVisibility(GONE);
            }
        } finally {
            a.recycle();
        }
    }

    @LayoutRes
    protected int getLayout() {
        return R.layout.w_empty_view;
    }

    protected Button getButton() {
        return mEmptyViewButton;
    }

    public void setButtonClickListener(OnClickListener buttonClickListener) {
        getButton().setOnClickListener(buttonClickListener);
    }

    public void bindEmptyView(@DrawableRes int icon, @StringRes int text, @StringRes int buttonText, OnClickListener buttonClickListener) {
        mEmptyViewIcon.setImageResource(icon);
        mEmptyViewText.setText(text);
        ViewUtils.setVisibility(View.VISIBLE, getButton());
        getButton().setText(buttonText);
        getButton().setOnClickListener(buttonClickListener);
    }

    public void bindEmptyView(@DrawableRes int icon, @StringRes int text) {
        mEmptyViewIcon.setImageResource(icon);
        mEmptyViewText.setText(text);
        ViewUtils.setVisibility(View.GONE, getButton());
    }

    public void bindEmptyView(@StringRes int text) {
        mEmptyViewText.setText(text);
        ViewUtils.setVisibility(View.GONE, mEmptyViewIcon);
        ViewUtils.setVisibility(View.GONE, getButton());
    }

    public void bindEmptyView(String text) {
        mEmptyViewText.setText(text);
        ViewUtils.setVisibility(View.GONE, mEmptyViewIcon);
        ViewUtils.setVisibility(View.GONE, getButton());
    }
}
