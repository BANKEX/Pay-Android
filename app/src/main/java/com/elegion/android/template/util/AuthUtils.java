package com.elegion.android.template.util;

import android.content.Context;
import android.content.Intent;

import com.elegion.android.template.data.Repository;
import com.elegion.android.template.ui.login.LoginActivity;

/**
 * @author Mike
 */
public final class AuthUtils {
    private AuthUtils() {
        // private constructor
    }

    public static void logout(Repository repository) {
        repository.logout();
    }

    public static void openLogin(Context context) {
        final Intent intent = LoginActivity.makeIntent(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
