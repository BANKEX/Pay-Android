package com.bankex.pay.presentation.presenter.base;

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
public class BasePresenter<View extends BaseView> extends MvpPresenter<View> {

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    /**
     * Возвращает {@link CompositeDisposable}, который очистит ссылки на {@link #onDestroy()}.
     *
     * @return {@link CompositeDisposable}
     */
    public final CompositeDisposable getRxCompositeDisposable() {
        return mCompositeDisposable;
    }

    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.dispose();
    }

    public final boolean addDisposable(@NotNull Disposable disposable) {
        return this.mCompositeDisposable.add(disposable);
    }

    public void onStart() {
        if (this.mCompositeDisposable.isDisposed()) {
            this.mCompositeDisposable = new CompositeDisposable();
        }
    }
}
