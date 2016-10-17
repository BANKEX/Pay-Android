package ru.elegion.rxloadermanager;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nikita Bumakov
 */
public class RxLifecycleFragment extends Fragment {

    private static final String TAG = RxLifecycleFragment.class.getSimpleName();

    private Map<String, RxLoader> mLoaderMap = new HashMap<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        synchronized (RxLifecycleFragment.class) {
            for (RxLoader rxLoader : mLoaderMap.values()) {
                rxLoader.reset();
            }
        }
        super.onDestroy();
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop");
        synchronized (RxLifecycleFragment.class) {
            for (RxLoader rxLoader : mLoaderMap.values()) {
                rxLoader.stopEmitting();
            }
        }
        super.onStop();
    }

    @SuppressWarnings("unchecked")
    public <T> RxLoader<T> getLoader(@NonNull String loaderId) {
        return (RxLoader<T>) mLoaderMap.get(loaderId);
    }

    public <T> void putLoader(@NonNull String loaderId, @NonNull RxLoader<T> worker) {
        synchronized (RxLifecycleFragment.class) {
            mLoaderMap.put(loaderId, worker);
        }
    }

    public void destroyLoader(@NonNull String loaderId) {
        synchronized (RxLifecycleFragment.class) {
            RxLoader rxLoader = mLoaderMap.get(loaderId);
            if (rxLoader != null) {
                rxLoader.reset();
                mLoaderMap.remove(loaderId);
            }
        }
    }
}
