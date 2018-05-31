package com.elegion.android.template.ui.base.error

import com.elegion.android.template.AppDelegate
import com.elegion.android.template.data.Repository
import com.elegion.android.template.data.model.ErrorBean
import com.elegion.android.template.ui.base.view.ErrorView
import com.elegion.android.template.ui.base.view.NoInternetStubView
import com.elegion.android.template.util.AuthUtils
import com.elegion.android.template.util.GsonUtils
import com.google.gson.JsonSyntaxException
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class ErrorHandler protected constructor(
    private val errorView: ErrorView,
    private val repository: Repository
) {
    private var noInternetStubView: NoInternetStubView? = null

    protected constructor(
        errorView: ErrorView,
        repository: Repository,
        noInternetStubView: NoInternetStubView?
    ) : this(errorView, repository) {
        this.noInternetStubView = noInternetStubView
    }

    fun hideNoInternetStub() = noInternetStubView?.hideNoInternetStub()

    fun handleError(t: Throwable) {
        when {
            t is HttpException -> handleHttpException(t)
            NETWORK_EXCEPTIONS.contains(t.javaClass) -> {
                noInternetStubView?.showNoInternetStub() ?: handleNetworkError(t)
            }
            else -> handleUnexpectedError(t)
        }
    }

    private fun handleHttpException(e: HttpException) {
        if (e.code() == AUTH_ERROR_HTTP_CODE) {
            handleAuthError(e)
        } else {
            try {
                val errorBody = e.response().errorBody()!!.string()
                val errorBean = GsonUtils.requestGson.fromJson(errorBody, ErrorBean::class.java)
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
    }

    protected fun handleAuthError(e: Throwable) {
        errorView.showErrorMessage(e.message ?: "")
        AuthUtils.logout(repository)
        AuthUtils.openLogin(AppDelegate.appContext)
    }

    protected fun handleProtocolError(errorBean: ErrorBean) {
        errorView.showErrorMessage(errorBean.message ?: "")
    }

    protected fun handleNonProtocolError(httpException: HttpException) {
        errorView.showErrorMessage(httpException.message())
    }

    protected fun handleNetworkError(e: Throwable) {
        errorView.showNetworkError()
    }

    protected fun handleUnexpectedError(e: Throwable) {
        errorView.showUnexpectedError()
    }

    companion object {
        const val AUTH_ERROR_HTTP_CODE = 401
        val NETWORK_EXCEPTIONS = listOf<Class<*>>(
            UnknownHostException::class.java,
            SocketTimeoutException::class.java,
            ConnectException::class.java
        )

        fun create(
            errorView: ErrorView,
            repository: Repository,
            noInternetStubView: NoInternetStubView?
        ): ErrorHandler {
            return ErrorHandler(errorView, repository, noInternetStubView)
        }
    }
}
