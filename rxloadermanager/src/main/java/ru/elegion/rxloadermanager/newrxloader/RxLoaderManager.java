package ru.elegion.rxloadermanager.newrxloader;

import android.app.Activity;
import android.os.Build;
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

    public static RxLoaderManager get(Activity activity) {
        RxLifecycleFragment lifecycleFragment = (RxLifecycleFragment) activity.getFragmentManager().findFragmentByTag(LIFECYCLE_FRAGMENT_TAG);
        if (lifecycleFragment == null) {
            lifecycleFragment = new RxLifecycleFragment();
            activity.getFragmentManager().beginTransaction().add(lifecycleFragment, LIFECYCLE_FRAGMENT_TAG).commit();
        }
        return new RxLoaderManager(lifecycleFragment);
    }

    public <T> RxLoader<T> create(@IdRes int loaderId, @NonNull Observable<T> observable, @NonNull RxLoaderObserver<T> observer) {
        return new RxLoader<>(loaderId, observable, observer);
    }
}
