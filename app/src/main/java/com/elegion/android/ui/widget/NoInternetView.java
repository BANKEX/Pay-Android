package com.elegion.android.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import com.elegion.android.R;

import butterknife.BindView;

/**
 * Created by marat.taychinov
 */
public class NoInternetView extends EmptyView {
    @BindView(R.id.no_internet_button)
    Button mButton;

    public NoInternetView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayout() {
        return R.layout.w_no_internet;
    }

    @Override
    protected Button getButton() {
        return mButton;
    }
}
