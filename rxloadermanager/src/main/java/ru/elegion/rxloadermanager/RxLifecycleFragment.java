package ru.elegion.rxloadermanager;

import android.app.Fragment;

/**
 * @author Rovkin Max
 */
public abstract class RxLifecycleFragment extends Fragment {
    private RxLifecycleProvider mProvider = new RxLifecycleProvider();

    @Override
    public void onStop() {
        mProvider.unsubscribe();
        super.onStop();
    }

    public RxLifecycleProvider getRxProvider() {
        return mProvider;
    }
}
