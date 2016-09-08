package ru.elegion.rxloadermanager.loader;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

/**
 * @author Artur Vasilov
 */
class RxLcImpl<T> implements LoaderManager.LoaderCallbacks<Void> {

    private final LifecycleLoader<T> mLifecycleLoader;
    private final Context mContext;

    public RxLcImpl(@NonNull Context context, @Nullable LifecycleLoader<T> loader) {
        mContext = context;
        if(loader != null){
            mLifecycleLoader = loader;
        }else {
            mLifecycleLoader = new LifecycleLoader<>(mContext);
        }
    }

    public RxLcImpl(@NonNull Context context, @Nullable  Loader loader) {
        mContext = context;
        if(loader != null){
            mLifecycleLoader = (LifecycleLoader<T>) loader;
        }else {
            mLifecycleLoader = new LifecycleLoader<>(mContext);
        }
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
