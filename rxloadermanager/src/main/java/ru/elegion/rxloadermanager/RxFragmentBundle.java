package ru.elegion.rxloadermanager;

import android.os.Bundle;

/**
 * @author Rovkin Max
 */
public class RxFragmentBundle extends RxLifecycleFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
}
