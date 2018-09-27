package com.bankex.pay.presentation.ui.navigation.base;

import android.support.annotation.IdRes;
import android.support.transition.Fade;
import android.support.transition.TransitionInflater;
import android.support.transition.TransitionSet;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.bankex.pay.presentation.ui.view.base.BaseFragment;

/**
 * Реализация {@link IBankexRouter}
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
public class BankexRouter extends BaseRouter implements IBankexRouter {

    private static final long MOVE_DEFAULT_TIME = 100;
    private static final long FADE_DEFAULT_TIME = 100;

    /**
     * {@inheritDoc }
     */
    @Override
    public void runFragmentWithAnimation(FragmentActivity activity,
                                         BaseFragment baseFragment,
                                         @IdRes int containerViewId) {
        FragmentManager mFragmentManager = activity.getSupportFragmentManager();
        Fragment previousFragment = mFragmentManager.findFragmentById(containerViewId);

        if (previousFragment.getId() == baseFragment.getId()) {
            return;
        }

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        // 1. Exit for Previous Fragment
        Fade exitFade = new Fade();
        exitFade.setDuration(FADE_DEFAULT_TIME);
        previousFragment.setExitTransition(exitFade);

        // 2. Shared Elements Transition
        TransitionSet enterTransitionSet = new TransitionSet();
        enterTransitionSet.addTransition(TransitionInflater.from(activity).inflateTransition(android.R.transition.move));
        enterTransitionSet.setDuration(MOVE_DEFAULT_TIME);
        enterTransitionSet.setStartDelay(FADE_DEFAULT_TIME);
        baseFragment.setSharedElementEnterTransition(enterTransitionSet);

        // 3. Enter Transition for New Fragment
        Fade enterFade = new Fade();
        enterFade.setStartDelay(MOVE_DEFAULT_TIME + FADE_DEFAULT_TIME);
        enterFade.setDuration(FADE_DEFAULT_TIME);
        baseFragment.setEnterTransition(enterFade);

        fragmentTransaction.replace(containerViewId, baseFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void runBankexFragment(FragmentActivity activity,
                                  BaseFragment fragment,
                                  int containerViewId) {
        runFragment(activity,
                fragment,
                containerViewId);
    }
}
