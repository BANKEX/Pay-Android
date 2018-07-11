package com.roughike.bottombar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

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
class BottomBarBadge extends AppCompatTextView {
    private int mCount;
    private boolean mIsVisible = false;

    BottomBarBadge(Context context) {
        super(context);
    }

    /**
     * Set the unread / new item / whatever mCount for this Badge.
     *
     * @param count the value this Badge should show.
     */
    void setCount(int count) {
        this.mCount = count;
        setText(String.valueOf(count));
    }

    /**
     * Get the currently showing mCount for this Badge.
     *
     * @return current mCount for the Badge.
     */
    int getCount() {
        return mCount;
    }

    /**
     * Shows the mBadge with a neat little scale animation.
     */
    void show() {
        mIsVisible = true;
        ViewCompat.animate(this)
                .setDuration(150)
                .alpha(1)
                .scaleX(1)
                .scaleY(1)
                .start();
    }

    /**
     * Hides the mBadge with a neat little scale animation.
     */
    void hide() {
        mIsVisible = false;
        ViewCompat.animate(this)
                .setDuration(150)
                .alpha(0)
                .scaleX(0)
                .scaleY(0)
                .start();
    }

    /**
     * Is this mBadge currently visible?
     *
     * @return true is this mBadge is visible, otherwise false.
     */
    boolean isVisible() {
        return mIsVisible;
    }

    void attachToTab(BottomBarTab tab, int backgroundColor) {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        setLayoutParams(params);
        setGravity(Gravity.CENTER);
        MiscUtils.setTextAppearance(this, R.style.BottomBarBadgeTextAppearance);

        setColoredCircleBackground(backgroundColor);
        wrapTabAndBadgeInSameContainer(tab);
    }

    void setColoredCircleBackground(int circleColor) {
        int innerPadding = MiscUtils.dpToPixel(getContext(), 1);
        ShapeDrawable backgroundCircle = BadgeCircle.make(innerPadding * 3, circleColor);
        setPadding(innerPadding, innerPadding, innerPadding, innerPadding);
        setBackgroundCompat(backgroundCircle);
    }

    private void wrapTabAndBadgeInSameContainer(final BottomBarTab tab) {
        ViewGroup tabContainer = (ViewGroup) tab.getParent();
        tabContainer.removeView(tab);

        final BadgeContainer badgeContainer = new BadgeContainer(getContext());
        LinearLayout.LayoutParams badgeContainerLp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        badgeContainerLp.weight = 1;
        badgeContainer.setLayoutParams(badgeContainerLp);

        badgeContainer.addView(tab);
        badgeContainer.addView(this);

        tabContainer.addView(badgeContainer, tab.getIndexInTabContainer());

        badgeContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {
                badgeContainer.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                adjustPositionAndSize(tab);
            }
        });
    }

    void removeFromTab(BottomBarTab tab) {
        BadgeContainer badgeAndTabContainer = (BadgeContainer) getParent();
        ViewGroup originalTabContainer = (ViewGroup) badgeAndTabContainer.getParent();

        badgeAndTabContainer.removeView(tab);
        originalTabContainer.removeView(badgeAndTabContainer);
        originalTabContainer.addView(tab, tab.getIndexInTabContainer());
    }

    void adjustPositionAndSize(BottomBarTab tab) {
        AppCompatImageView iconView = tab.getIconView();
        ViewGroup.LayoutParams params = getLayoutParams();

        int size = Math.max(getWidth(), getHeight());
        float xOffset = (float) (iconView.getWidth() / 1.25);

        setX(iconView.getX() + xOffset);
        setTranslationY(10);

        if (params.width != size || params.height != size) {
            params.width = size;
            params.height = size;
            setLayoutParams(params);
        }
    }

    @SuppressWarnings("deprecation")
    private void setBackgroundCompat(Drawable background) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(background);
        } else {
            setBackgroundDrawable(background);
        }
    }
}
