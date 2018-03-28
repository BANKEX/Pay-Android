package com.elegion.android.template.ui.base.presenter

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import com.elegion.android.template.util.RxUtils

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter<V : MvpView> : MvpPresenter<V>() {
    private var mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    fun removeDisposable(d: Disposable) {
        mCompositeDisposable.remove(d)
    }

    fun addDisposable(d: Disposable) {
        mCompositeDisposable.add(d)
    }

    fun clearDisposables() {
        mCompositeDisposable.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        RxUtils.dispose(mCompositeDisposable)
    }
}
