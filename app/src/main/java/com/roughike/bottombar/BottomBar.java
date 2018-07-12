package com.roughike.bottombar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.XmlRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bankex.wallet.R;

import java.util.List;


import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

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
public class BottomBar extends LinearLayout implements View.OnClickListener, View.OnLongClickListener {
    private static final String STATE_CURRENT_SELECTED_TAB = "STATE_CURRENT_SELECTED_TAB";
    private static final float DEFAULT_INACTIVE_SHIFTING_TAB_ALPHA = 0.6f;
    // Behaviors
    private static final int BEHAVIOR_NONE = 0;
    private static final int BEHAVIOR_SHIFTING = 1;
    private static final int BEHAVIOR_SHY = 2;
    private static final int BEHAVIOR_DRAW_UNDER_NAV = 4;
    private static final int BEHAVIOR_ICONS_ONLY = 8;

    private BatchTabPropertyApplier mBatchPropertyApplier;
    private int mTenDp;

    // XML Attributes
    private int mTabXmlResource;
    private int mBehaviors;
    private float mInActiveTabAlpha;
    private float mActiveTabAlpha;
    private int mInActiveTabColor;
    private int mActiveTabColor;
    private int mBadgeBackgroundColor;
    private boolean mHideBadgeWhenActive;
    private boolean mLongPressHintsEnabled;
    private int mTitleTextAppearance;
    private Typeface mTitleTypeFace;

    private ViewGroup mTabContainer;

    private int mDefaultBackgroundColor = Color.WHITE;
    private int mCurrentTabPosition;

    @Nullable
    private TabSelectionInterceptor mTabSelectionInterceptor;

    @Nullable
    private OnTabSelectListener mOnTabSelectListener;

    @Nullable
    private OnTabReselectListener mOnTabReselectListener;

    private boolean mIsComingFromRestoredState;
    private boolean mIgnoreTabReselectionListener;

    private ShySettings mShySettings;
    private boolean mShyHeightAlreadyCalculated;
    private boolean mNavBarAccountedHeightCalculated;

    private BottomBarTab[] mCurrentTabs;

    public BottomBar(Context context) {
        this(context, null);
    }

    public BottomBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public BottomBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        mBatchPropertyApplier = new BatchTabPropertyApplier(this);

        populateAttributes(context, attrs, defStyleAttr, defStyleRes);
        initializeViews();
        determineInitialBackgroundColor();

        if (mTabXmlResource != 0) {
            setItems(mTabXmlResource);
        }
    }

    private void populateAttributes(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        mTenDp = MiscUtils.dpToPixel(getContext(), 10);

        TypedArray ta = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.BottomBar, defStyleAttr, defStyleRes);

        try {
            mTabXmlResource = ta.getResourceId(R.styleable.BottomBar_bb_tabXmlResource, 0);
            mBehaviors = ta.getInteger(R.styleable.BottomBar_bb_barBehavior, BEHAVIOR_NONE);
            mInActiveTabAlpha = ta.getFloat(R.styleable.BottomBar_bb_inActiveTabAlpha, 1);
            mActiveTabAlpha = ta.getFloat(R.styleable.BottomBar_bb_activeTabAlpha, 1);

            @ColorInt
            int defaultInActiveColor = ContextCompat.getColor(context, R.color.shark);
            @ColorInt
            int defaultActiveColor = ContextCompat.getColor(context, R.color.shark);

            mLongPressHintsEnabled = ta.getBoolean(R.styleable.BottomBar_bb_longPressHintsEnabled, true);
            mInActiveTabColor = ta.getColor(R.styleable.BottomBar_bb_inActiveTabColor, defaultInActiveColor);
            mActiveTabColor = ta.getColor(R.styleable.BottomBar_bb_activeTabColor, defaultActiveColor);
            mBadgeBackgroundColor = ta.getColor(R.styleable.BottomBar_bb_badgeBackgroundColor, Color.RED);
            mHideBadgeWhenActive = ta.getBoolean(R.styleable.BottomBar_bb_badgesHideWhenActive, true);
            mTitleTextAppearance = ta.getResourceId(R.styleable.BottomBar_bb_barTitleTextAppearance, 0);
        } finally {
            ta.recycle();
        }
    }

    private boolean drawUnderNav() {
        return hasBehavior(BEHAVIOR_DRAW_UNDER_NAV);
    }

    boolean isShy() {
        return hasBehavior(BEHAVIOR_SHY);
    }

    boolean isShyHeightAlreadyCalculated() {
        return mShyHeightAlreadyCalculated;
    }

    private boolean isIconsOnlyMode() {
        return hasBehavior(BEHAVIOR_ICONS_ONLY);
    }

    private boolean hasBehavior(int behavior) {
        return (mBehaviors | behavior) == mBehaviors;
    }

    private Typeface getTypeFaceFromAsset(String fontPath) {
        if (fontPath != null) {
            return Typeface.createFromAsset(
                    getContext().getAssets(), fontPath);
        }

        return null;
    }

    private void initializeViews() {
        int width = MATCH_PARENT;
        int height = WRAP_CONTENT;
        LayoutParams params = new LayoutParams(width, height);

        setLayoutParams(params);
        setOrientation(VERTICAL);

        View rootView = inflate(getContext(), R.layout.w_bottom_bar_item_container, this);
        rootView.setLayoutParams(params);

        mTabContainer = rootView.findViewById(R.id.llBottomBarItemContainer);
    }

    private void determineInitialBackgroundColor() {
        Drawable userDefinedBackground = getBackground();

        boolean userHasDefinedBackgroundColor = userDefinedBackground != null
                && userDefinedBackground instanceof ColorDrawable;

        if (userHasDefinedBackgroundColor) {
            mDefaultBackgroundColor = ((ColorDrawable) userDefinedBackground).getColor();
            setBackgroundColor(Color.TRANSPARENT);
        }
    }

    /**
     * Set the items for the BottomBar from XML Resource.
     */
    public void setItems(@XmlRes int xmlRes) {
        setItems(xmlRes, null);
    }

    /**
     * Set the item for the BottomBar from XML Resource with a default configuration
     * for each tab.
     */
    public void setItems(@XmlRes int xmlRes, BottomBarTab.Config defaultTabConfig) {
        if (xmlRes == 0) {
            throw new RuntimeException("No items specified for the BottomBar!");
        }

        if (defaultTabConfig == null) {
            defaultTabConfig = getTabConfig();
        }

        TabParser parser = new TabParser(getContext(), defaultTabConfig, xmlRes);
        updateItems(parser.parseTabs());
    }

    private BottomBarTab.Config getTabConfig() {
        return new BottomBarTab.Config.Builder()
                .inActiveTabAlpha(mInActiveTabAlpha)
                .activeTabAlpha(mActiveTabAlpha)
                .inActiveTabColor(mInActiveTabColor)
                .activeTabColor(mActiveTabColor)
                .barColorWhenSelected(mDefaultBackgroundColor)
                .badgeBackgroundColor(mBadgeBackgroundColor)
                .hideBadgeWhenSelected(mHideBadgeWhenActive)
                .titleTextAppearance(mTitleTextAppearance)
                .titleTypeFace(mTitleTypeFace)
                .build();
    }

    private void updateItems(final List<BottomBarTab> bottomBarItems) {
        mTabContainer.removeAllViews();

        int index = 0;
        int biggestWidth = 0;

        BottomBarTab[] viewsToAdd = new BottomBarTab[bottomBarItems.size()];

        for (BottomBarTab bottomBarTab : bottomBarItems) {
            BottomBarTab.Type type = BottomBarTab.Type.FIXED;

            if (isIconsOnlyMode()) {
                bottomBarTab.setIsTitleless(true);
            }

            bottomBarTab.setType(type);
            bottomBarTab.prepareLayout();

            if (index == mCurrentTabPosition) {
                bottomBarTab.select();
            } else {
                bottomBarTab.deselect();
            }

            if (bottomBarTab.getWidth() > biggestWidth) {
                biggestWidth = bottomBarTab.getWidth();
            }

            viewsToAdd[index] = bottomBarTab;

            bottomBarTab.setOnClickListener(this);
            bottomBarTab.setOnLongClickListener(this);
            index++;
        }

        mCurrentTabs = viewsToAdd;

        resizeTabsToCorrectSizes(viewsToAdd);
    }

    private void resizeTabsToCorrectSizes(BottomBarTab[] tabsToAdd) {
        for (BottomBarTab tabView : tabsToAdd) {
            LayoutParams params = new LayoutParams(MATCH_PARENT, WRAP_CONTENT);
            params.weight = 1;

            if (tabView.getParent() == null) {
                mTabContainer.addView(tabView);
            }

            tabView.setLayoutParams(params);
        }
    }

    /**
     * Returns the settings specific for a shy BottomBar.
     *
     * @throws UnsupportedOperationException, if this BottomBar is not shy.
     */
    public ShySettings getShySettings() {
        if (!isShy()) {
            Log.e("BottomBar", "Tried to get shy settings for a BottomBar " +
                    "that is not shy.");
        }

        if (mShySettings == null) {
            mShySettings = new ShySettings(this);
        }

        return mShySettings;
    }

    /**
     * Set a listener that gets fired when the selected {@link BottomBarTab} is about to change.
     *
     * @param interceptor a listener for potentially interrupting changes in tab selection.
     */
    public void setTabSelectionInterceptor(@NonNull TabSelectionInterceptor interceptor) {
        mTabSelectionInterceptor = interceptor;
    }

    /**
     * Removes the current {@link TabSelectionInterceptor} listener
     */
    public void removeOverrideTabSelectionListener() {
        mTabSelectionInterceptor = null;
    }

    /**
     * Set a listener that gets fired when the selected {@link BottomBarTab} changes.
     * <p>
     * Note: Will be immediately called for the currently selected tab
     * once when set.
     *
     * @param listener a listener for monitoring changes in tab selection.
     */
    public void setOnTabSelectListener(@NonNull OnTabSelectListener listener) {
        setOnTabSelectListener(listener, true);
    }

    /**
     * Set a listener that gets fired when the selected {@link BottomBarTab} changes.
     * <p>
     * If {@code shouldFireInitially} is set to false, this listener isn't fired straight away
     * it's set, but you'll get all events normally for consecutive tab selection changes.
     *
     * @param listener            a listener for monitoring changes in tab selection.
     * @param shouldFireInitially whether the listener should be fired the first time it's set.
     */
    public void setOnTabSelectListener(@NonNull OnTabSelectListener listener, boolean shouldFireInitially) {
        mOnTabSelectListener = listener;

        if (shouldFireInitially && getTabCount() > 0) {
            listener.onTabSelected(getCurrentTabId());
        }
    }

    /**
     * Removes the current {@link OnTabSelectListener} listener
     */
    public void removeOnTabSelectListener() {
        mOnTabSelectListener = null;
    }

    /**
     * Set a listener that gets fired when a currently selected {@link BottomBarTab} is clicked.
     *
     * @param listener a listener for handling tab reselections.
     */
    public void setOnTabReselectListener(@NonNull OnTabReselectListener listener) {
        mOnTabReselectListener = listener;
    }

    /**
     * Removes the current {@link OnTabReselectListener} listener
     */
    public void removeOnTabReselectListener() {
        mOnTabReselectListener = null;
    }

    /**
     * Set the default selected to be the tab with the corresponding tab id.
     * By default, the first tab in the container is the default tab.
     */
    public void setDefaultTab(@IdRes int defaultTabId) {
        int defaultTabPosition = findPositionForTabWithId(defaultTabId);
        setDefaultTabPosition(defaultTabPosition);
    }

    /**
     * Sets the default tab for this BottomBar that is shown until the user changes
     * the selection.
     *
     * @param defaultTabPosition the default tab position.
     */
    public void setDefaultTabPosition(int defaultTabPosition) {
        if (mIsComingFromRestoredState) return;

        selectTabAtPosition(defaultTabPosition);
    }

    /**
     * Select the tab with the corresponding id.
     */
    public void selectTabWithId(@IdRes int tabResId) {
        int tabPosition = findPositionForTabWithId(tabResId);
        selectTabAtPosition(tabPosition);
    }

    /**
     * Select a tab at the specified position.
     *
     * @param position the position to select.
     */
    public void selectTabAtPosition(int position) {
        selectTabAtPosition(position, false);
    }

    /**
     * Select a tab at the specified position.
     *
     * @param position the position to select.
     * @param animate  should the tab change be animated or not.
     */
    public void selectTabAtPosition(int position, boolean animate) {
        if (position > getTabCount() - 1 || position < 0) {
            throw new IndexOutOfBoundsException("Can't select tab at position " +
                    position + ". This BottomBar has no items at that position.");
        }

        BottomBarTab oldTab = getCurrentTab();
        BottomBarTab newTab = getTabAtPosition(position);

        oldTab.deselect();
        newTab.select();

        updateSelectedTab(position);
    }

    public int getTabCount() {
        return mTabContainer.getChildCount();
    }

    /**
     * Get the currently selected tab.
     */
    public BottomBarTab getCurrentTab() {
        return getTabAtPosition(getCurrentTabPosition());
    }

    /**
     * Get the tab at the specified position.
     */
    public BottomBarTab getTabAtPosition(int position) {
        View child = mTabContainer.getChildAt(position);

        if (child instanceof BadgeContainer) {
            return findTabInLayout((BadgeContainer) child);
        }

        return (BottomBarTab) child;
    }

    /**
     * Get the resource id for the currently selected tab.
     */
    @IdRes
    public int getCurrentTabId() {
        return getCurrentTab().getId();
    }

    /**
     * Get the currently selected tab position.
     */
    public int getCurrentTabPosition() {
        return mCurrentTabPosition;
    }

    /**
     * Find the tabs' position in the container by id.
     */
    public int findPositionForTabWithId(@IdRes int tabId) {
        return getTabWithId(tabId).getIndexInTabContainer();
    }

    /**
     * Find a BottomBarTab with the corresponding id.
     */
    public BottomBarTab getTabWithId(@IdRes int tabId) {
        return (BottomBarTab) mTabContainer.findViewById(tabId);
    }

    /**
     * Controls whether the long pressed tab title should be displayed in
     * a helpful Toast if the title is not currently visible.
     *
     * @param enabled true if toasts should be shown to indicate the title
     *                of a long pressed tab, false otherwise.
     */
    public void setLongPressHintsEnabled(boolean enabled) {
        mLongPressHintsEnabled = enabled;
    }

    /**
     * Set alpha value used for inactive BottomBarTabs.
     */
    public void setInActiveTabAlpha(float alpha) {
        mInActiveTabAlpha = alpha;

        mBatchPropertyApplier.applyToAllTabs(new BatchTabPropertyApplier.TabPropertyUpdater() {
            @Override
            public void update(BottomBarTab tab) {
                tab.setInActiveAlpha(mInActiveTabAlpha);
            }
        });
    }

    /**
     * Set alpha value used for active BottomBarTabs.
     */
    public void setActiveTabAlpha(float alpha) {
        mActiveTabAlpha = alpha;

        mBatchPropertyApplier.applyToAllTabs(new BatchTabPropertyApplier.TabPropertyUpdater() {
            @Override
            public void update(BottomBarTab tab) {
                tab.setActiveAlpha(mActiveTabAlpha);
            }
        });
    }

    public void setInActiveTabColor(@ColorInt int color) {
        mInActiveTabColor = color;

        mBatchPropertyApplier.applyToAllTabs(new BatchTabPropertyApplier.TabPropertyUpdater() {
            @Override
            public void update(BottomBarTab tab) {
                tab.setInActiveColor(mInActiveTabColor);
            }
        });
    }

    /**
     * Set active color used for selected BottomBarTabs.
     */
    public void setActiveTabColor(@ColorInt int color) {
        mActiveTabColor = color;

        mBatchPropertyApplier.applyToAllTabs(new BatchTabPropertyApplier.TabPropertyUpdater() {
            @Override
            public void update(BottomBarTab tab) {
                tab.setActiveColor(mActiveTabColor);
            }
        });
    }

    /**
     * Set background color for the mBadge.
     */
    public void setBadgeBackgroundColor(@ColorInt int color) {
        mBadgeBackgroundColor = color;

        mBatchPropertyApplier.applyToAllTabs(new BatchTabPropertyApplier.TabPropertyUpdater() {
            @Override
            public void update(BottomBarTab tab) {
                tab.setBadgeBackgroundColor(mBadgeBackgroundColor);
            }
        });
    }

    /**
     * Controls whether the mBadge (if any) for active tabs
     * should be hidden or not.
     */
    public void setBadgesHideWhenActive(final boolean hideWhenSelected) {
        mHideBadgeWhenActive = hideWhenSelected;
        mBatchPropertyApplier.applyToAllTabs(new BatchTabPropertyApplier.TabPropertyUpdater() {
            @Override
            public void update(BottomBarTab tab) {
                tab.setBadgeHidesWhenActive(hideWhenSelected);
            }
        });
    }

    /**
     * Set custom text apperance for all BottomBarTabs.
     */
    public void setTabTitleTextAppearance(int textAppearance) {
        mTitleTextAppearance = textAppearance;

        mBatchPropertyApplier.applyToAllTabs(new BatchTabPropertyApplier.TabPropertyUpdater() {
            @Override
            public void update(BottomBarTab tab) {
                tab.setTitleTextAppearance(mTitleTextAppearance);
            }
        });
    }

    /**
     * Set a custom typeface for all tab's titles.
     *
     * @param fontPath path for your custom font file, such as fonts/MySuperDuperFont.ttf.
     *                 In that case your font path would look like src/main/assets/fonts/MySuperDuperFont.ttf,
     *                 but you only need to provide fonts/MySuperDuperFont.ttf, as the asset folder
     *                 will be auto-filled for you.
     */
    public void setTabTitleTypeface(String fontPath) {
        Typeface actualTypeface = getTypeFaceFromAsset(fontPath);
        setTabTitleTypeface(actualTypeface);
    }

    /**
     * Set a custom typeface for all tab's titles.
     */
    public void setTabTitleTypeface(Typeface typeface) {
        mTitleTypeFace = typeface;

        mBatchPropertyApplier.applyToAllTabs(new BatchTabPropertyApplier.TabPropertyUpdater() {
            @Override
            public void update(BottomBarTab tab) {
                tab.setTitleTypeface(mTitleTypeFace);
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if (changed) {
            resizeTabsToCorrectSizes(mCurrentTabs);

            updateTitleBottomPadding();

            if (isShy()) {
                initializeShyBehavior();
            }

            if (drawUnderNav()) {
                resizeForDrawingUnderNavbar();
            }
        }
    }

    private void updateTitleBottomPadding() {
        if (isIconsOnlyMode()) {
            return;
        }

        int tabCount = getTabCount();

        if (mTabContainer == null || tabCount == 0) {
            return;
        }

        for (int i = 0; i < tabCount; i++) {
            BottomBarTab tab = getTabAtPosition(i);
            TextView title = tab.getTitleView();

            if (title == null) {
                continue;
            }

            int baseline = title.getBaseline();
            int height = title.getHeight();
            int paddingInsideTitle = height - baseline;
            int missingPadding = mTenDp - paddingInsideTitle;

            if (missingPadding > 0) {
                title.setPadding(title.getPaddingLeft(), title.getPaddingTop(),
                        title.getPaddingRight(), missingPadding + title.getPaddingBottom());
            }
        }
    }

    private void initializeShyBehavior() {
        ViewParent parent = getParent();

        boolean hasAbusiveParent = parent != null
                && parent instanceof CoordinatorLayout;

        if (!hasAbusiveParent) {
            throw new RuntimeException("In order to have shy behavior, the " +
                    "BottomBar must be a direct child of a CoordinatorLayout.");
        }

        if (!mShyHeightAlreadyCalculated) {
            int height = getHeight();

            if (height != 0) {
                updateShyHeight(height);
                getShySettings().shyHeightCalculated();
                mShyHeightAlreadyCalculated = true;
            }
        }
    }

    private void updateShyHeight(int height) {
        ((CoordinatorLayout.LayoutParams) getLayoutParams())
                .setBehavior(new BottomNavigationBehavior(height, 0, false));
    }

    private void resizeForDrawingUnderNavbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int currentHeight = getHeight();

            if (currentHeight != 0 && !mNavBarAccountedHeightCalculated) {
                mNavBarAccountedHeightCalculated = true;
                mTabContainer.getLayoutParams().height = currentHeight;

                int navbarHeight = NavbarUtils.getNavbarHeight(getContext());
                int finalHeight = currentHeight + navbarHeight;
                getLayoutParams().height = finalHeight;

                if (isShy()) {
                    updateShyHeight(finalHeight);
                }
            }
        }
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Bundle bundle = saveState();
        bundle.putParcelable("superstate", super.onSaveInstanceState());
        return bundle;
    }

    @VisibleForTesting
    Bundle saveState() {
        Bundle outState = new Bundle();
        outState.putInt(STATE_CURRENT_SELECTED_TAB, mCurrentTabPosition);

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
        if (savedInstanceState != null) {
            mIsComingFromRestoredState = true;
            mIgnoreTabReselectionListener = true;

            int restoredPosition = savedInstanceState.getInt(STATE_CURRENT_SELECTED_TAB, mCurrentTabPosition);
            selectTabAtPosition(restoredPosition, false);
        }
    }

    @Override
    public void onClick(View target) {
        if (!(target instanceof BottomBarTab)) return;
        handleClick((BottomBarTab) target);
    }

    @Override
    public boolean onLongClick(View target) {
        return !(target instanceof BottomBarTab) || handleLongClick((BottomBarTab) target);
    }

    private BottomBarTab findTabInLayout(ViewGroup child) {
        for (int i = 0; i < child.getChildCount(); i++) {
            View candidate = child.getChildAt(i);

            if (candidate instanceof BottomBarTab) {
                return (BottomBarTab) candidate;
            }
        }

        return null;
    }

    private void handleClick(BottomBarTab newTab) {
        BottomBarTab oldTab = getCurrentTab();

        if (mTabSelectionInterceptor != null
                && mTabSelectionInterceptor.shouldInterceptTabSelection(oldTab.getId(), newTab.getId())) {
            return;
        }

        oldTab.deselect();
        newTab.select();

        updateSelectedTab(newTab.getIndexInTabContainer());
    }

    private boolean handleLongClick(BottomBarTab longClickedTab) {
        boolean isClickedTitleHidden = !longClickedTab.isActive();
        boolean shouldShowHint = isClickedTitleHidden
                && mLongPressHintsEnabled;

        if (shouldShowHint) {
            Toast.makeText(getContext(), longClickedTab.getTitle(), Toast.LENGTH_SHORT)
                    .show();
        }

        return true;
    }

    private void updateSelectedTab(int newPosition) {
        int newTabId = getTabAtPosition(newPosition).getId();

        if (newPosition != mCurrentTabPosition) {
            if (mOnTabSelectListener != null) {
                mOnTabSelectListener.onTabSelected(newTabId);
            }
        } else if (mOnTabReselectListener != null && !mIgnoreTabReselectionListener) {
            mOnTabReselectListener.onTabReSelected(newTabId);
        }

        mCurrentTabPosition = newPosition;

        if (mIgnoreTabReselectionListener) {
            mIgnoreTabReselectionListener = false;
        }
    }
}
