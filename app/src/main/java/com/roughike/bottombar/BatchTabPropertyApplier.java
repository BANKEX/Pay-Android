package com.roughike.bottombar;

import android.support.annotation.NonNull;

class BatchTabPropertyApplier {
    private final BottomBar mBottomBar;

    interface TabPropertyUpdater {
        void update(BottomBarTab tab);
    }

    BatchTabPropertyApplier(@NonNull BottomBar bottomBar) {
        this.mBottomBar = bottomBar;
    }

    void applyToAllTabs(@NonNull TabPropertyUpdater propertyUpdater) {
        int tabCount = mBottomBar.getTabCount();

        if (tabCount > 0) {
            for (int i = 0; i < tabCount; i++) {
                BottomBarTab tab = mBottomBar.getTabAtPosition(i);
                propertyUpdater.update(tab);
            }
        }
    }
}
