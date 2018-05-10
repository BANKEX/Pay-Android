package com.elegion.android.template.ui.features

import com.arellomobile.mvp.InjectViewState
import com.elegion.android.template.data.Repository
import com.elegion.android.template.data.model.Feature
import com.elegion.android.template.ui.base.error.ErrorHandler
import com.elegion.android.template.ui.base.presenter.BasePresenter
import com.elegion.android.template.util.AuthUtils
import kotlinx.coroutines.experimental.Job

@InjectViewState
internal class FeaturesPresenter(private val repository: Repository) : BasePresenter<FeaturesView>() {

    private var offset = 0
    private var errorHandler = ErrorHandler.create(viewState, repository, viewState)
    private var loadFeaturesJob: Job? = null
    private var isLastPage: Boolean = false

    override fun onFirstViewAttach() = loadFeatures(true)

    fun loadFeatures(refresh: Boolean) {
        if (refresh) {
            offset = 0
            isLastPage = false
        }

        if (!isLastPage && loadFeaturesJob?.isCompleted != false) {
            loadFeaturesJob = launchLoadingErrorJob(errorHandler, viewState) {
                handleFeaturesResponse(repository.getFeatures(offset, PAGE_COUNT))
            }
            addCancellableJob(loadFeaturesJob)
        }
    }

    private fun handleFeaturesResponse(features: List<Feature>) {
        val clear = offset == 0
        if (clear) {
            viewState.clearFeatures()
        }
        viewState.showFeatures(features, clear)
        offset += features.size
        isLastPage = offset > MAX_FEATURES
    }

    fun logout() = AuthUtils.logout(repository)

    companion object {
        private const val PAGE_COUNT = 20
        private const val MAX_FEATURES = 50
    }
}
