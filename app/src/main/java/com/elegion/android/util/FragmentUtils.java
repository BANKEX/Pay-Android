package com.elegion.android.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.elegion.android.R;

/**
 * @author mikhail barannikov
 */
public final class FragmentUtils {
    private FragmentUtils() {
    }

    public static void replaceFragment(FragmentManager fm, Fragment fragment) {
        replaceFragment(fm, fragment, false, 0, 0, 0, 0);
    }

    public static void replaceFragment(FragmentManager fm, Fragment fragment, boolean addToBackStack) {
        replaceFragment(fm, fragment, addToBackStack, 0, 0, 0, 0);
    }

    public static void replaceFragment(FragmentManager fm, Fragment fragment, boolean addToBackStack, int enter, int exit) {
        replaceFragment(fm, fragment, addToBackStack, enter, exit, 0, 0);
    }

    public static void replaceFragment(FragmentManager fm, Fragment fragment, boolean addToBackStack,
                                       int enter, int exit, int popEnter, int popExit) {
        FragmentTransaction tr = fm.beginTransaction();
        if (enter != 0 && exit != 0) {
            if (popEnter != 0 && popExit != 0) {
                tr.setCustomAnimations(enter, exit, popEnter, popExit);
            } else {
                tr.setCustomAnimations(enter, exit);
            }
        }
        tr.replace(R.id.fl_container, fragment, fragment.getClass().getName());
        if (addToBackStack) {
            tr.addToBackStack(fragment.getClass().getName());
        }
        tr.commit();
    }

    public static void addFragment(FragmentManager fm, Fragment fragment) {
        addFragment(fm, fragment, false);
    }

    public static void addFragment(FragmentManager fm, Fragment fragment, boolean addToBackStack) {
        FragmentTransaction tr = fm.beginTransaction();
        tr.add(R.id.fl_container, fragment, fragment.getClass().getName());
        if (addToBackStack) {
            tr.addToBackStack(fragment.getClass().getName());
        }
        tr.commit();

    }

    public static void addFragment(FragmentManager fm, Fragment fragment, boolean addToBackStack,
                                   int enter, int exit, int popEnter, int popExit) {
        FragmentTransaction tr = fm.beginTransaction();
        tr.setCustomAnimations(enter, exit, popEnter, popExit);
        tr.add(R.id.fl_container, fragment, fragment.getClass().getName());
        if (addToBackStack) {
            tr.addToBackStack(fragment.getClass().getName());
        }
        tr.commit();
    }
}
