package ru.elegion.rxloadermanager;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.util.SparseArray;

/**
 * @author Nikita Bumakov
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
        unsubscribe();
        super.onDestroy();
    }

    private void unsubscribe() {
        for (int i = 0; i < mWorkerSparseArray.size(); i++) {
            int key = mWorkerSparseArray.keyAt(i);
            RxWorkObserver workObserver = mWorkerSparseArray.get(key);
            workObserver.unsubscribe();
        }
    }

    @Override
    public void onDetach() {
        for (int i = 0; i < mWorkerSparseArray.size(); i++) {
            int key = mWorkerSparseArray.keyAt(i);
            RxWorkObserver workObserver = mWorkerSparseArray.get(key);
//            workObserver.unsubscribe();
        }
        super.onDetach();
    }

    public <T> RxWorkObserver<T> get(@IdRes int loaderId) {
        return mWorkerSparseArray.get(loaderId);
    }

    public <T> void put(@IdRes int loaderId, @NonNull RxWorkObserver<T> worker) {
        mWorkerSparseArray.put(loaderId, worker);
    }
}
