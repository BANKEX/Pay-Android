package com.elegion.android.template.ui.features

import com.arellomobile.mvp.InjectViewState
import com.elegion.android.template.data.Repository
import com.elegion.android.template.data.model.Feature
import com.elegion.android.template.ui.base.presenter.BasePresenter
import com.elegion.android.template.util.AuthUtils
import com.elegion.android.template.util.RxUtils
import io.reactivex.disposables.Disposable

@InjectViewState
internal class FeaturesPresenter(private val repository: Repository) : BasePresenter<FeaturesView>() {

    private var offset = 0
    private var loadFeaturesSubscription: Disposable? = null
    private var isLastPage: Boolean = false

    override fun onFirstViewAttach() = loadFeatures(true)

    fun loadFeatures(refresh: Boolean) {
        if (refresh) {
            offset = 0
            isLastPage = false
        }

        if (!isLastPage && RxUtils.isNullOrDisposed(loadFeaturesSubscription)) {
            removeDisposable(loadFeaturesSubscription)
            loadFeaturesSubscription = repository.getFeatures(offset, PAGE_COUNT)
                .compose<List<Feature>> { RxUtils.async(it) }
                .compose(RxUtils.loading(viewState))
                .subscribe({ this.handleFeaturesResponse(it) }, { RxUtils.errorLogE(it) })
            addDisposable(loadFeaturesSubscription)
        }
    }

    private fun handleFeaturesResponse(features: List<Feature>) {
        val clear = offset == 0
        if (clear) {
            viewState.clearFeatures()
        }
        viewState.showFeatures(features, clear)
        offset += features.size
        isLastPage = offset > 50
    }

    fun logout() = AuthUtils.logout(repository)

    companion object {
        private const val PAGE_COUNT = 20
    }
}
