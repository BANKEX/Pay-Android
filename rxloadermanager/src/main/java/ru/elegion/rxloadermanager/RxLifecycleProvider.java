package ru.elegion.rxloadermanager;

import android.app.Activity;
import android.app.FragmentManager;
import android.support.annotation.NonNull;

import rx.Observable;
import rx.subscriptions.CompositeSubscription;

/**
 * @author Rovkin Max
 */
public class RxLifecycleProvider {

    private CompositeSubscription mSubscription;

    RxLifecycleProvider() {
    }

    public static RxLifecycleProvider newInstance() {
        return new RxLifecycleProvider();
    }

    @NonNull
    public static RxLifecycleProvider get(@NonNull Activity activity) {
        return get(activity.getFragmentManager());
    }

    @NonNull
    private static RxLifecycleProvider get(@NonNull FragmentManager fm) {
        final RxFragmentBundle loader = (RxFragmentBundle) fm.findFragmentByTag(RxFragmentBundle.class.getName());
        if (loader == null) {
            throw new NullPointerException("RxLifecycleProvider not attached to FragmentManager");
        }
        return loader.getRxProvider();
    }

    public static void attach(@NonNull FragmentManager fm) {
        if (fm.findFragmentByTag(RxFragmentBundle.class.getName()) == null) {
            fm.beginTransaction()
                    .add(new RxFragmentBundle(), RxFragmentBundle.class.getName())
                    .commitAllowingStateLoss();
            fm.executePendingTransactions();
        }
    }

//    @NonNull
//    public <T> Observable.Operator<T, T> lifecycle() {
//        return subscriber -> {
//            if (mSubscription == null) {
//                mSubscription = new CompositeSubscription();
//            }
//            mSubscription.add(subscriber);
//            return subscriber;
//        };
//    }
//
//    @NonNull
//    public <T> Observable.Transformer<T, T> async() {
//        return observable -> observable
//                .subscribeOn(RxSchedulers.io())
//                .observeOn(RxSchedulers.main());
//    }

    public void unsubscribe() {
        if (mSubscription != null) {
            mSubscription.unsubscribe();
            mSubscription.clear();
            mSubscription = null;
        }
    }
}
