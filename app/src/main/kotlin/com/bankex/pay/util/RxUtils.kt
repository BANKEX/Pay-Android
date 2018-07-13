package com.bankex.pay.util

import android.support.v4.util.Pair
import com.bankex.pay.data.remote.rest.response.EmptyListResponse
import com.bankex.pay.ui.base.view.ErrorStubView
import com.bankex.pay.ui.base.view.LoadingView
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

object RxUtils {
    @JvmStatic
    fun dispose(subscription: Disposable?) {
        if (subscription != null && !subscription.isDisposed) {
            subscription.dispose()
        }
    }

    @JvmStatic
    fun isNullOrDisposed(disposable: Disposable?): Boolean {
        return disposable == null || disposable.isDisposed
    }

    @JvmStatic
    fun <T> async(flowable: Flowable<T>): Flowable<T> {
        return flowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    @JvmStatic
    fun <T> loading(view: LoadingView): FlowableTransformer<T, T> {
        return FlowableTransformer {
            it.doOnSubscribe {
                view.showLoadingIndicator()
                Timber.d("LOADING: doOnSubscribe")
            }.doFinally {
                Timber.d("LOADING: doOnTerminate")
                view.hideLoadingIndicator()
            }
        }
    }

    @JvmStatic
    fun <T : EmptyListResponse> emptyErrorStub(view: ErrorStubView): FlowableTransformer<T, T> {
        return FlowableTransformer {
            it.doOnSubscribe {
                view.hideErrorStub(it)
            }.doOnNext {
                if (it.isEmpty) {
                    view.showErrorStub()
                }
            }
        }
    }

    @JvmStatic
    fun <F : EmptyListResponse, S : EmptyListResponse, T : Pair<F, S>> emptyPairErrorStub(view: ErrorStubView): FlowableTransformer<T, T> {
        return FlowableTransformer {
            it.doOnSubscribe {
                view.hideErrorStub(it)
            }.doOnNext {
                if (it.first != null || it.second == null) return@doOnNext
                if (it.first!!.isEmpty && it.second!!.isEmpty) {
                    view.showErrorStub()
                }
            }
        }
    }

    @JvmStatic
    fun errorLogE(e: Throwable) = Timber.d(e, "errorLogE")

    @JvmStatic
    fun <T> emptyTransformer(): FlowableTransformer<T, T> = FlowableTransformer { it }
}
