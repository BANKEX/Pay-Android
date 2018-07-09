package com.roughike.bottombar;

/**
 * Settings specific for a shy BottomBar.
 */
public class ShySettings {
    private BottomBar mBottomBar;
    private Boolean mPendingIsVisibleInShyMode;

    ShySettings(BottomBar bottomBar) {
        this.mBottomBar = bottomBar;
    }

    void shyHeightCalculated() {
        updatePendingShyVisibility();
    }

    /**
     * Shows the BottomBar if it was hidden, with a translate animation.
     */
    public void showBar() {
        toggleIsVisibleInShyMode(true);
    }

    /**
     * Hides the BottomBar in if it was visible, with a translate animation.
     */
    public void hideBar() {
        toggleIsVisibleInShyMode(false);
    }

    private void toggleIsVisibleInShyMode(boolean visible) {
        if (!mBottomBar.isShy()) {
            return;
        }

        if (mBottomBar.isShyHeightAlreadyCalculated()) {
            BottomNavigationBehavior<BottomBar> behavior = BottomNavigationBehavior.from(mBottomBar);

            if (behavior != null) {
                boolean isHidden = !visible;
                behavior.setHidden(mBottomBar, isHidden);
            }
        } else {
            mPendingIsVisibleInShyMode = true;
        }
    }

    private void updatePendingShyVisibility() {
        if (mPendingIsVisibleInShyMode != null) {
            toggleIsVisibleInShyMode(mPendingIsVisibleInShyMode);
            mPendingIsVisibleInShyMode = null;
        }
    }
}
