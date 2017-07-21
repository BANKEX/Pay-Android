package com.elegion.android.ui.base.view;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

/**
 * @author Mike
 */
public interface PermissionsView extends MvpView {
    boolean isPermissionGranted(@NonNull String permission);
    boolean shouldShowRequestPermissionRationale(@NonNull String permission);
    void requestPermissions(@NonNull String[] permissions, int requestCode);
    void showPermissionRationaleDialog(@StringRes int rationale);
}