package ru.elegion.rxloadermanager;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;

import rx.functions.Action1;

/**
 * @author Nikita Bumakov
 */
public class RxLifecycleFragment extends Fragment {

    private static final String TAG = RxLifecycleFragment.class.getSimpleName();

    private SparseArray<RxLoader> mLoaderSparseArray = new SparseArray<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        forEach(new Action1<RxLoader>() {
            @Override
            public void call(@NonNull RxLoader rxLoader) {
                rxLoader.reset();
            }
        });
        super.onDestroy();
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop");
        forEach(new Action1<RxLoader>() {
            @Override
            public void call(@NonNull RxLoader rxLoader) {
                rxLoader.stopEmitting();
            }
        });
        super.onStop();
    }

    @SuppressWarnings("unchecked")
    public <T> RxLoader<T> getLoader(@IdRes int loaderId) {
        return (RxLoader<T>) mLoaderSparseArray.get(loaderId);
    }

    public <T> void putLoader(@IdRes int loaderId, @NonNull RxLoader<T> worker) {
        mLoaderSparseArray.put(loaderId, worker);
    }

    public void destroyLoader(@IdRes int loaderId) {
        RxLoader rxLoader = mLoaderSparseArray.get(loaderId);
        if (rxLoader != null) {
            rxLoader.reset();
            mLoaderSparseArray.remove(loaderId);
        }
    }

    private void forEach(@NonNull Action1<RxLoader> action) {
        for (int i = 0; i < mLoaderSparseArray.size(); i++) {
            int key = mLoaderSparseArray.keyAt(i);
            RxLoader rxLoader = mLoaderSparseArray.get(key);
            action.call(rxLoader);
        }
    }
}
