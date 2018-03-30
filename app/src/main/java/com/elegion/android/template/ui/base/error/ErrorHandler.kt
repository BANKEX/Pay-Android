package com.elegion.android.template.ui.base.error

import com.elegion.android.template.AppDelegate
import com.elegion.android.template.data.Repository
import com.elegion.android.template.data.model.ErrorBean
import com.elegion.android.template.ui.base.view.ErrorView
import com.elegion.android.template.ui.base.view.NoInternetStubView
import com.elegion.android.template.util.AuthUtils
import com.elegion.android.template.util.GsonUtils
import com.google.gson.JsonSyntaxException
import io.reactivex.FlowableTransformer
import io.reactivex.functions.Consumer
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class ErrorHandler protected constructor(
        private val mErrorView: ErrorView,
        private val mRepository: Repository
) {
    private var mNoInternetStubView: NoInternetStubView? = null

    protected constructor(errorView: ErrorView, repository: Repository,
                          noInternetStubView: NoInternetStubView?) : this(errorView, repository) {
        mNoInternetStubView = noInternetStubView
    }

    fun <T> transformer(): FlowableTransformer<T, T> {
        return FlowableTransformer {
            it.doOnSubscribe { mNoInternetStubView?.hideNoInternetStub() }
                    .doOnError(error())
        }
    }

    private fun error(): Consumer<Throwable> {
        return Consumer {
            Timber.d(it, "from ErrorHandler.error")
            handleError(it)
        }
    }

    private fun handleError(e: Throwable) {
        if (e is HttpException) {
            if (e.code() == 401) {
                handleAuthError(e)
            } else {
                try {
                    val errorBody = e.response().errorBody()!!.string()
                    val errorBean = GsonUtils.requestGson().fromJson(errorBody, ErrorBean::class.java)
                    if (errorBean != null) {
                        handleProtocolError(errorBean)
                    } else {
                        handleNonProtocolError(e)
                    }
                } catch (e1: IOException) {
                    handleNonProtocolError(e)
                } catch (e1: IllegalStateException) {
                    handleNonProtocolError(e)
                } catch (e1: JsonSyntaxException) {
                    handleNonProtocolError(e)
                }
            }
        } else if (NETWORK_EXCEPTIONS.contains(e.javaClass)) {
            if (null == mNoInternetStubView) {
                handleNetworkError(e)
            }
        } else {
            handleUnexpectedError(e)
        }
    }

    protected fun handleAuthError(e: Throwable) {
        mErrorView.showErrorMessage(e.message ?: "")
        AuthUtils.logout(mRepository)
        AuthUtils.openLogin(AppDelegate.appContext)
    }

    protected fun handleProtocolError(errorBean: ErrorBean) {
        mErrorView.showErrorMessage(errorBean.message ?: "")
    }

    protected fun handleNonProtocolError(httpException: HttpException) {
        mErrorView.showErrorMessage(httpException.message())
    }

    protected fun handleNetworkError(e: Throwable) {
        mErrorView.showNetworkError()
    }

    protected fun handleUnexpectedError(e: Throwable) {
        mErrorView.showUnexpectedError()
    }

    companion object {
        protected val NETWORK_EXCEPTIONS = listOf<Class<*>>(
                UnknownHostException::class.java,
                SocketTimeoutException::class.java,
                ConnectException::class.java
        )

        fun create(errorView: ErrorView, repository: Repository, noInternetStubView: NoInternetStubView?): ErrorHandler {
            return ErrorHandler(errorView, repository, noInternetStubView)
        }
    }
}
