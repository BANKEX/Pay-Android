package com.bankex.wallet.ui.base.presenter

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import com.elegion.android.bankex.ui.base.error.ErrorHandler
import com.elegion.android.bankex.ui.base.view.LoadingView
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import timber.log.Timber
import kotlin.coroutines.experimental.CoroutineContext

open class BasePresenter<V : MvpView> : MvpPresenter<V>() {
    private var jobs = mutableListOf<Job>()

    protected fun addCancellableJob(job: Job?) {
        if (job != null) {
            jobs.filter { it.isCompleted }
                .forEach { jobs.remove(it) }
            jobs.add(job)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        jobs.forEach { it.cancel() }
        jobs.clear()
    }

    @Suppress("TooGenericExceptionCaught")
    protected fun launchLoadingErrorJob(
        errorHandler: ErrorHandler? = null,
        loadingView: LoadingView? = null,
        context: CoroutineContext = UI,
        block: suspend () -> Unit
    ): Job {
        return launch(context) {
            try {
                errorHandler?.hideNoInternetStub()
                loadingView?.showLoadingIndicator()
                block.invoke()
            } catch (t: Throwable) {
                errorHandler?.handleError(t) ?: Timber.e(t, "loadingErrorJob")
            } finally {
                loadingView?.hideLoadingIndicator()
            }
        }
    }
}
