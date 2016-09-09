package ru.elegion.rxloadermanager.newrxloader;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.util.SparseArray;

/**
 * @author Bumakov Nikita
 */
public class RxLifecycleFragment extends Fragment {

    private SparseArray<RxWorkObserver> mWorkerSparseArray = new SparseArray<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public <T> RxWorkObserver<T> get(@IdRes int loaderId) {
        return mWorkerSparseArray.get(loaderId);
    }

    public <T> void put(@IdRes int loaderId, @NonNull RxWorkObserver<T> worker) {
        mWorkerSparseArray.put(loaderId, worker);
    }
}
