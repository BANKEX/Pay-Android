package com.elegion.android.template.util

import android.support.v4.util.Pair
import com.elegion.android.template.AppDelegate
import com.elegion.android.template.data.Repository
import com.elegion.android.template.data.model.ErrorBean
import com.elegion.android.template.data.remote.rest.response.EmptyListResponse
import com.elegion.android.template.ui.base.view.ErrorStubView
import com.elegion.android.template.ui.base.view.ErrorView
import com.elegion.android.template.ui.base.view.LoadingView
import com.elegion.android.template.ui.base.view.NoInternetStubView
import com.google.gson.JsonSyntaxException
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.*

object RxUtils {
    private val NETWORK_EXCEPTIONS = Arrays.asList<Class<*>>(
            UnknownHostException::class.java,
            SocketTimeoutException::class.java,
            ConnectException::class.java
    )

    @JvmStatic
    fun dispose(subscription: CompositeDisposable?) {
        if (subscription != null && !subscription.isDisposed) {
            subscription.dispose()
        }
    }

    @JvmStatic
    fun isNullOrUnsubscribed(disposable: Disposable?): Boolean {
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
                if (it.first != null && it.first!!.isEmpty && it.second != null && it.second!!.isEmpty) {
                    view.showErrorStub()
                }
            }
        }
    }

    @JvmStatic
    fun <T> errorTransformer(errorView: ErrorView,
                             repository: Repository,
                             noInternetStubView: NoInternetStubView?): FlowableTransformer<T, T> {
        return FlowableTransformer {
            it.doOnNext {
                noInternetStubView?.hideNoInternetStub()
            }.doOnError(error(errorView, repository))
        }
    }

    @JvmStatic
    fun error(errorView: ErrorView, repository: Repository): Consumer<Throwable> {
        return Consumer {
            Timber.d(it, "from RxUtils.error")
            handleError(it, errorView, repository)
        }
    }

    @JvmStatic
    private fun handleError(e: Throwable, errorView: ErrorView, repository: Repository) {
        if (e is HttpException) {
            if (e.code() == 401) {
                errorView.showErrorMessage(e.message())
                AuthUtils.logout(repository)
                AuthUtils.openLogin(AppDelegate.appContext)
            } else {
                try {
                    val errorBody = e.response().errorBody()!!.string()
                    val errorBean = GsonUtils.requestGson().fromJson(errorBody, ErrorBean::class.java)
                    if (errorBean != null) {
                        errorView.showErrorMessage(errorBean.message)
                    } else {
                        errorView.showErrorMessage(e.message())
                    }
                } catch (e1: IOException) {
                    errorView.showErrorMessage(e.message())
                } catch (e1: IllegalStateException) {
                    errorView.showErrorMessage(e.message())
                } catch (e1: JsonSyntaxException) {
                    errorView.showErrorMessage(e.message())
                }

            }
        } else if (NETWORK_EXCEPTIONS.contains(e.javaClass)) {
            errorView.showNetworkError()
        } else {
            errorView.showUnexpectedError()
        }
    }

    @JvmStatic
    fun errorLogE(e: Throwable) = Timber.d(e, "errorLogE")

    @JvmStatic
    fun <T> emptyTransformer(): FlowableTransformer<T, T> = FlowableTransformer { it }
}
