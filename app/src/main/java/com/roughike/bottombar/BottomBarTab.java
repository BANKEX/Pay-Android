package com.roughike.bottombar;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.widget.AppCompatImageView;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.elegion.android.bankex.R;

/*
 * BottomBar library for Android
 * Copyright (c) 2016 Iiro Krankka (http://github.com/roughike).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class BottomBarTab extends LinearLayout {
    @VisibleForTesting
    static final String STATE_BADGE_COUNT = "STATE_BADGE_COUNT_FOR_TAB_";

    private static final long ANIMATION_DURATION = 150;
    private static final float ACTIVE_TITLE_SCALE = 1;
    private static final float INACTIVE_FIXED_TITLE_SCALE = 0.86f;
    private static final float ACTIVE_SHIFTING_TITLELESS_ICON_SCALE = 1.24f;
    private static final float INACTIVE_SHIFTING_TITLELESS_ICON_SCALE = 1f;

    @VisibleForTesting
    BottomBarBadge mBadge;

    private Type mType = Type.FIXED;
    private boolean mIsTitleless;
    private int mIconResId;
    private String mTitle;
    private float mInActiveAlpha;
    private float mActiveAlpha;
    private int mInActiveColor;
    private int mActiveColor;
    private int mBarColorWhenSelected;
    private int mBadgeBackgroundColor;
    private boolean mBadgeHidesWhenActive;
    private AppCompatImageView mIconView;
    private TextView mTitleView;
    private boolean mIsActive;
    private int mIndexInContainer;
    private int mTitleTextAppearanceResId;
    private Typeface mTitleTypeFace;

    BottomBarTab(Context context) {
        super(context);
    }

    void setConfig(@NonNull Config config) {
        setInActiveAlpha(config.mInActiveTabAlpha);
        setActiveAlpha(config.mActiveTabAlpha);
        setInActiveColor(config.mInActiveTabColor);
        setActiveColor(config.mActiveTabColor);
        setBarColorWhenSelected(config.mBarColorWhenSelected);
        setBadgeBackgroundColor(config.mBadgeBackgroundColor);
        setBadgeHidesWhenActive(config.mBadgeHidesWhenSelected);
        setTitleTextAppearance(config.mTitleTextAppearance);
        setTitleTypeface(config.mTitleTypeFace);
    }

    void prepareLayout() {
        inflate(getContext(), getLayoutResource(), this);
        setOrientation(VERTICAL);
        setGravity(mIsTitleless ? Gravity.CENTER : Gravity.CENTER_HORIZONTAL);
        setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        ViewCompat.setBackground(this, ContextCompat.getDrawable(getContext(), R.drawable.bg_bottom_tab));

        mIconView = (AppCompatImageView) findViewById(R.id.bb_bottom_bar_icon);
        mIconView.setImageResource(mIconResId);

        if (mType != Type.TABLET && !mIsTitleless) {
            mTitleView = (TextView) findViewById(R.id.bb_bottom_bar_title);
            mTitleView.setVisibility(VISIBLE);

            if (mType == Type.SHIFTING) {
                findViewById(R.id.spacer).setVisibility(VISIBLE);
            }

            updateTitle();
        }

        updateCustomTextAppearance();
        updateCustomTypeface();
    }

    @VisibleForTesting
    int getLayoutResource() {
        int layoutResource;
        switch (mType) {
            case FIXED:
                layoutResource = R.layout.w_bottom_bar_item_fixed;
                break;
            default:
                // should never happen
                throw new RuntimeException("Unknown BottomBarTab mType.");
        }
        return layoutResource;
    }

    private void updateTitle() {
        if (mTitleView != null) {
            mTitleView.setText(mTitle);
        }
    }

    @SuppressWarnings("deprecation")
    private void updateCustomTextAppearance() {
        if (mTitleView == null || mTitleTextAppearanceResId == 0) {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mTitleView.setTextAppearance(mTitleTextAppearanceResId);
        } else {
            mTitleView.setTextAppearance(getContext(), mTitleTextAppearanceResId);
        }

        mTitleView.setTag(R.id.bb_bottom_bar_appearance_id, mTitleTextAppearanceResId);
    }

    private void updateCustomTypeface() {
        if (mTitleTypeFace != null && mTitleView != null) {
            mTitleView.setTypeface(mTitleTypeFace);
        }
    }

    Type getType() {
        return mType;
    }

    void setType(Type type) {
        this.mType = type;
    }

    boolean isTitleless() {
        return mIsTitleless;
    }

    void setIsTitleless(boolean isTitleless) {
        if (isTitleless && getIconResId() == 0) {
            throw new IllegalStateException("This tab is supposed to be " +
                    "icon only, yet it has no icon specified. Index in " +
                    "container: " + getIndexInTabContainer());
        }

        this.mIsTitleless = isTitleless;
    }

    public ViewGroup getOuterView() {
        return (ViewGroup) getParent();
    }

    AppCompatImageView getIconView() {
        return mIconView;
    }

    int getIconResId() {
        return mIconResId;
    }

    void setIconResId(int iconResId) {
        this.mIconResId = iconResId;
    }

    TextView getTitleView() {
        return mTitleView;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
        updateTitle();
    }

    public float getInActiveAlpha() {
        return mInActiveAlpha;
    }

    public void setInActiveAlpha(float inActiveAlpha) {
        this.mInActiveAlpha = inActiveAlpha;

        if (!mIsActive) {
            setAlphas(inActiveAlpha);
        }
    }

    public float getActiveAlpha() {
        return mActiveAlpha;
    }

    public void setActiveAlpha(float activeAlpha) {
        this.mActiveAlpha = activeAlpha;

        if (mIsActive) {
            setAlphas(activeAlpha);
        }
    }

    public int getInActiveColor() {
        return mInActiveColor;
    }

    public void setInActiveColor(int inActiveColor) {
        this.mInActiveColor = inActiveColor;

        if (!mIsActive) {
            setColors(inActiveColor);
        }
    }

    public int getActiveColor() {
        return mActiveColor;
    }

    public void setActiveColor(int activeIconColor) {
        this.mActiveColor = activeIconColor;

        if (mIsActive) {
            setColors(mActiveColor);
        }
    }

    public int getBarColorWhenSelected() {
        return mBarColorWhenSelected;
    }

    public void setBarColorWhenSelected(int barColorWhenSelected) {
        this.mBarColorWhenSelected = barColorWhenSelected;
    }

    public int getBadgeBackgroundColor() {
        return mBadgeBackgroundColor;
    }

    public void setBadgeBackgroundColor(int badgeBackgroundColor) {
        this.mBadgeBackgroundColor = badgeBackgroundColor;

        if (mBadge != null) {
            mBadge.setColoredCircleBackground(badgeBackgroundColor);
        }
    }

    public void setBadgeHidesWhenActive(boolean hideWhenActive) {
        this.mBadgeHidesWhenActive = hideWhenActive;
    }

    int getCurrentDisplayedIconColor() {
        Object tag = mIconView.getTag(R.id.bb_bottom_bar_color_id);

        if (tag instanceof Integer) {
            return (int) tag;
        }

        return 0;
    }

    int getCurrentDisplayedTitleColor() {
        if (mTitleView != null) {
            return mTitleView.getCurrentTextColor();
        }

        return 0;
    }

    int getCurrentDisplayedTextAppearance() {
        Object tag = mTitleView.getTag(R.id.bb_bottom_bar_appearance_id);

        if (mTitleView != null && tag instanceof Integer) {
            return (int) tag;
        }

        return 0;
    }

    public void setBadgeCount(int count) {
        if (count <= 0) {
            if (mBadge != null) {
                mBadge.removeFromTab(this);
                mBadge = null;
            }

            return;
        }

        if (mBadge == null) {
            mBadge = new BottomBarBadge(getContext());
            mBadge.attachToTab(this, mBadgeBackgroundColor);
        }

        mBadge.setCount(count);

        if (mIsActive && mBadgeHidesWhenActive) {
            mBadge.hide();
        }
    }

    public void removeBadge() {
        setBadgeCount(0);
    }

    boolean isActive() {
        return mIsActive;
    }

    boolean hasActiveBadge() {
        return mBadge != null;
    }

    int getIndexInTabContainer() {
        return mIndexInContainer;
    }

    void setIndexInContainer(int indexInContainer) {
        this.mIndexInContainer = indexInContainer;
    }

    void setIconTint(int tint) {
        mIconView.setColorFilter(tint);
    }

    public int getTitleTextAppearance() {
        return mTitleTextAppearanceResId;
    }

    @SuppressWarnings("deprecation")
    void setTitleTextAppearance(int resId) {
        this.mTitleTextAppearanceResId = resId;
        updateCustomTextAppearance();
    }

    public void setTitleTypeface(Typeface typeface) {
        this.mTitleTypeFace = typeface;
        updateCustomTypeface();
    }

    public Typeface getTitleTypeFace() {
        return mTitleTypeFace;
    }

    void select() {
        mIsActive = true;

        setColors(mActiveColor);

        setSelected(true);

        if (mBadge != null && mBadgeHidesWhenActive) {
            mBadge.hide();
        }
    }

    void deselect() {
        mIsActive = false;

        boolean isShifting = mType == Type.SHIFTING;

        setColors(mInActiveColor);

        setSelected(false);

        if (!isShifting && mBadge != null && !mBadge.isVisible()) {
            mBadge.show();
        }
    }

    private void animateColors(int previousColor, int color) {
        ValueAnimator anim = new ValueAnimator();
        anim.setIntValues(previousColor, color);
        anim.setEvaluator(new ArgbEvaluator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                setColors((Integer) valueAnimator.getAnimatedValue());
            }
        });

        anim.setDuration(150);
        anim.start();
    }

    private void setColors(int color) {
        if (mIconView != null) {
            mIconView.setColorFilter(color);
            mIconView.setTag(R.id.bb_bottom_bar_color_id, color);
        }

        if (mTitleView != null) {
            mTitleView.setTextColor(color);
        }
    }

    private void setAlphas(float alpha) {
        if (mIconView != null) {
            ViewCompat.setAlpha(mIconView, alpha);
        }

        if (mTitleView != null) {
            ViewCompat.setAlpha(mTitleView, alpha);
        }
    }

    void updateWidth(float endWidth, boolean animated) {
        if (!animated) {
            getLayoutParams().width = (int) endWidth;

            if (!mIsActive && mBadge != null) {
                mBadge.adjustPositionAndSize(this);
                mBadge.show();
            }
            return;
        }

        float start = getWidth();

        ValueAnimator animator = ValueAnimator.ofFloat(start, endWidth);
        animator.setDuration(150);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                ViewGroup.LayoutParams params = getLayoutParams();
                if (params == null) return;

                params.width = Math.round((float) animator.getAnimatedValue());
                setLayoutParams(params);
            }
        });

        // Workaround to avoid using faulty onAnimationEnd() listener
        postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!mIsActive && mBadge != null) {
                    clearAnimation();
                    mBadge.adjustPositionAndSize(BottomBarTab.this);
                    mBadge.show();
                }
            }
        }, animator.getDuration());

        animator.start();
    }

    private void updateBadgePosition() {
        if (mBadge != null) {
            mBadge.adjustPositionAndSize(this);
        }
    }

    private void setTopPaddingAnimated(int start, int end) {
        if (mType == Type.TABLET || mIsTitleless) {
            return;
        }

        ValueAnimator paddingAnimator = ValueAnimator.ofInt(start, end);
        paddingAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mIconView.setPadding(
                        mIconView.getPaddingLeft(),
                        (Integer) animation.getAnimatedValue(),
                        mIconView.getPaddingRight(),
                        mIconView.getPaddingBottom()
                );
            }
        });

        paddingAnimator.setDuration(ANIMATION_DURATION);
        paddingAnimator.start();
    }

    private void animateTitle(int padding, float scale, float alpha) {
        if (mType == Type.TABLET && mIsTitleless) {
            return;
        }

        setTopPaddingAnimated(mIconView.getPaddingTop(), padding);

        ViewPropertyAnimatorCompat titleAnimator = ViewCompat.animate(mTitleView)
                .setDuration(ANIMATION_DURATION)
                .scaleX(scale)
                .scaleY(scale);
        titleAnimator.alpha(alpha);
        titleAnimator.start();
    }

    private void animateIconScale(float scale) {
        ViewCompat.animate(mIconView)
                .setDuration(ANIMATION_DURATION)
                .scaleX(scale)
                .scaleY(scale)
                .start();
    }

    private void animateIcon(float alpha, float scale) {
        ViewCompat.animate(mIconView)
                .setDuration(ANIMATION_DURATION)
                .alpha(alpha)
                .start();

        if (mIsTitleless && mType == Type.SHIFTING) {
            animateIconScale(scale);
        }
    }

    private void setTitleScale(float scale) {
        if (mType == Type.TABLET || mIsTitleless) {
            return;
        }

        ViewCompat.setScaleX(mTitleView, scale);
        ViewCompat.setScaleY(mTitleView, scale);
    }

    private void setIconScale(float scale) {
        if (mIsTitleless && mType == Type.SHIFTING) {
            ViewCompat.setScaleX(mIconView, scale);
            ViewCompat.setScaleY(mIconView, scale);
        }
    }

    @Override
    public Parcelable onSaveInstanceState() {
        if (mBadge != null) {
            Bundle bundle = saveState();
            bundle.putParcelable("superstate", super.onSaveInstanceState());

            return bundle;
        }

        return super.onSaveInstanceState();
    }

    @VisibleForTesting
    Bundle saveState() {
        Bundle outState = new Bundle();
        outState.putInt(STATE_BADGE_COUNT + getIndexInTabContainer(), mBadge.getCount());

        return outState;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            restoreState(bundle);

            state = bundle.getParcelable("superstate");
        }

        super.onRestoreInstanceState(state);
    }

    @VisibleForTesting
    void restoreState(Bundle savedInstanceState) {
        int previousBadgeCount = savedInstanceState.getInt(STATE_BADGE_COUNT + getIndexInTabContainer());
        setBadgeCount(previousBadgeCount);
    }

    enum Type {
        FIXED, SHIFTING, TABLET
    }

    public static class Config {
        private final float mInActiveTabAlpha;
        private final float mActiveTabAlpha;
        private final int mInActiveTabColor;
        private final int mActiveTabColor;
        private final int mBarColorWhenSelected;
        private final int mBadgeBackgroundColor;
        private final int mTitleTextAppearance;
        private final Typeface mTitleTypeFace;
        private boolean mBadgeHidesWhenSelected = true;

        private Config(Builder builder) {
            this.mInActiveTabAlpha = builder.mInActiveTabAlpha;
            this.mActiveTabAlpha = builder.mActiveTabAlpha;
            this.mInActiveTabColor = builder.mInActiveTabColor;
            this.mActiveTabColor = builder.mActiveTabColor;
            this.mBarColorWhenSelected = builder.mBarColorWhenSelected;
            this.mBadgeBackgroundColor = builder.mBadgeBackgroundColor;
            this.mBadgeHidesWhenSelected = builder.mHidesBadgeWhenSelected;
            this.mTitleTextAppearance = builder.mTitleTextAppearance;
            this.mTitleTypeFace = builder.mTitleTypeFace;
        }

        public static class Builder {
            private float mInActiveTabAlpha;
            private float mActiveTabAlpha;
            private int mInActiveTabColor;
            private int mActiveTabColor;
            private int mBarColorWhenSelected;
            private int mBadgeBackgroundColor;
            private boolean mHidesBadgeWhenSelected = true;
            private int mTitleTextAppearance;
            private Typeface mTitleTypeFace;

            public Builder inActiveTabAlpha(float alpha) {
                this.mInActiveTabAlpha = alpha;
                return this;
            }

            public Builder activeTabAlpha(float alpha) {
                this.mActiveTabAlpha = alpha;
                return this;
            }

            public Builder inActiveTabColor(@ColorInt int color) {
                this.mInActiveTabColor = color;
                return this;
            }

            public Builder activeTabColor(@ColorInt int color) {
                this.mActiveTabColor = color;
                return this;
            }

            public Builder barColorWhenSelected(@ColorInt int color) {
                this.mBarColorWhenSelected = color;
                return this;
            }

            public Builder badgeBackgroundColor(@ColorInt int color) {
                this.mBadgeBackgroundColor = color;
                return this;
            }

            public Builder hideBadgeWhenSelected(boolean hide) {
                this.mHidesBadgeWhenSelected = hide;
                return this;
            }

            public Builder titleTextAppearance(int titleTextAppearance) {
                this.mTitleTextAppearance = titleTextAppearance;
                return this;
            }

            public Builder titleTypeFace(Typeface titleTypeFace) {
                this.mTitleTypeFace = titleTypeFace;
                return this;
            }

            public Config build() {
                return new Config(this);
            }
        }
    }
}
