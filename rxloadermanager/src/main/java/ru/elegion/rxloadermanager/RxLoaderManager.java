package ru.elegion.rxloadermanager;

import android.app.Activity;
import android.app.FragmentManager;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;

import rx.Observable;

/**
 * @author Nikita Bumakov
 */
public class RxLoaderManager {

    private static final String LIFECYCLE_FRAGMENT_TAG = RxLifecycleFragment.class.getName();

    @NonNull
    private RxLifecycleFragment mRxLifecycleFragment;

    private RxLoaderManager(@NonNull RxLifecycleFragment lifecycleFragment) {
        mRxLifecycleFragment = lifecycleFragment;
    }

    public static RxLoaderManager get(@NonNull Activity activity) {
        return get(activity.getFragmentManager());
    }

    public static RxLoaderManager get(@NonNull FragmentManager fm) {
        RxLifecycleFragment lifecycleFragment = (RxLifecycleFragment) fm.findFragmentByTag(LIFECYCLE_FRAGMENT_TAG);
        if (lifecycleFragment == null) {
            lifecycleFragment = new RxLifecycleFragment();
            fm.beginTransaction().add(lifecycleFragment, LIFECYCLE_FRAGMENT_TAG).commit();
        }
        return new RxLoaderManager(lifecycleFragment);
    }

    public <T> RxLoader<T> create(@IdRes int loaderId, @NonNull Observable<T> observable, @NonNull RxLoaderObserver<T> observer) {
        return new RxLoader<>(loaderId, observable, observer, mRxLifecycleFragment);
    }
}
