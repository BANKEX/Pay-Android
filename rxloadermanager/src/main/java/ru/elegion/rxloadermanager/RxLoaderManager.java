package ru.elegion.rxloadermanager;

import android.app.Activity;
import android.app.FragmentManager;
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

    @NonNull
    public <T> Observable.Transformer<T, T> init(@NonNull final String loaderId) {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(@NonNull Observable<T> observable) {
                if (mRxLifecycleFragment.getLoader(loaderId) == null) {
                    RxLoader<T> rxLoader = new RxLoader<>(observable);
                    mRxLifecycleFragment.putLoader(loaderId, rxLoader);
                }
                final RxLoader<T> rxLoader = mRxLifecycleFragment.getLoader(loaderId);
                return rxLoader.getChildObservable();
            }
        };
    }

    @NonNull
    public <T> Observable.Transformer<T, T> restart(@NonNull final String loaderId) {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(final Observable<T> observable) {
                mRxLifecycleFragment.destroyLoader(loaderId);
                RxLoader<T> rxLoader = new RxLoader<>(observable);
                mRxLifecycleFragment.putLoader(loaderId, rxLoader);
                return rxLoader.getChildObservable();
            }
        };
    }
}
