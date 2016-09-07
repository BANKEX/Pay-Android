package ru.elegion.rxloadermanager.loader;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.NonNull;

import rx.Observable;
import rx.Observer;

/**
 * @author Artur Vasilov
 */
class RxLcImpl<T> implements LoaderManager.LoaderCallbacks<Void> {

    private final LifecycleLoader<T> mLifecycleLoader;

    public RxLcImpl(@NonNull Context context) {
        mLifecycleLoader = new LifecycleLoader<>(context);
    }

    public LifecycleLoader<T> getLoader() {
        return mLifecycleLoader;
    }

    @Override
    public Loader<Void> onCreateLoader(int i, Bundle bundle) {
        return mLifecycleLoader;
    }

    @Override
    public void onLoadFinished(Loader<Void> loader, Void aVoid) {
        //do noting
    }

    @Override
    public void onLoaderReset(Loader<Void> loader) {
        //do noting
    }
}
