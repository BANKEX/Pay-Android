package com.bankex.pay.presentation.presenter.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import com.arellomobile.mvp.MvpPresenter;
import com.bankex.pay.presentation.ui.base.BaseView;

import org.jetbrains.annotations.NotNull;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Базовый презентер
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
public class BasePresenter<View extends BaseView> extends MvpPresenter<View> implements LifecycleObserver {

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public BasePresenter() {
        super();
    }

    public BasePresenter(@NotNull Lifecycle lifecycle) {
        super();
        lifecycle.addObserver(this);
        this.mCompositeDisposable = new CompositeDisposable();
    }

    /**
     * Возвращает {@link CompositeDisposable}, который очистит ссылки на {@link #onDestroy()}.
     *
     * @return {@link CompositeDisposable}
     */
    public final CompositeDisposable getRxCompositeDisposable() {
        return mCompositeDisposable;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.dispose();
    }

    public final boolean addDisposable(@NotNull Disposable disposable) {
        return this.mCompositeDisposable.add(disposable);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        if (this.mCompositeDisposable.isDisposed()) {
            this.mCompositeDisposable = new CompositeDisposable();
        }
    }
}
