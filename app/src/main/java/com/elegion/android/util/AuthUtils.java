package com.elegion.android.util;

import com.elegion.android.data.Repository;

/**
 * @author Mike
 */
public class AuthUtils {
    private AuthUtils() {
        // private constructor
    }

    public static void logout(Repository repository) {
        repository.logout();
    }
}
