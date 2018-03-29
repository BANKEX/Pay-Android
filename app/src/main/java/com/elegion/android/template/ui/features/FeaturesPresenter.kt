package com.elegion.android.template.ui.features

import com.arellomobile.mvp.InjectViewState
import com.elegion.android.template.data.Repository
import com.elegion.android.template.data.model.Feature
import com.elegion.android.template.ui.base.presenter.BasePresenter
import com.elegion.android.template.util.AuthUtils
import com.elegion.android.template.util.RxUtils
import io.reactivex.disposables.Disposable

@InjectViewState
internal class FeaturesPresenter(private val mRepository: Repository) : BasePresenter<FeaturesView>() {

    private var mOffset = 0
    private var mLoadFeaturesSubscription: Disposable? = null
    private var mIsLastPage: Boolean = false

    override fun onFirstViewAttach() = loadFeatures(true)

    fun loadFeatures(refresh: Boolean) {
        if (refresh) {
            mOffset = 0
            mIsLastPage = false
        }

        if (!mIsLastPage && RxUtils.isNullOrUnsubscribed(mLoadFeaturesSubscription)) {
            removeDisposable(mLoadFeaturesSubscription!!)
            mLoadFeaturesSubscription = mRepository.getFeatures(mOffset, PAGE_COUNT)
                    .compose<List<Feature>>({ RxUtils.async(it) })
                    .compose(RxUtils.loading(viewState))
                    .subscribe({ this.handleFeaturesResponse(it) }, { RxUtils.errorLogE(it) })
            addDisposable(mLoadFeaturesSubscription!!)
        }
    }

    private fun handleFeaturesResponse(features: List<Feature>) {
        val clear = mOffset == 0
        if (clear) {
            viewState.clearFeatures()
        }
        viewState.showFeatures(features, clear)
        mOffset += features.size
        mIsLastPage = mOffset > 50
    }

    fun logout() = AuthUtils.logout(mRepository)

    companion object {
        private const val PAGE_COUNT = 20
    }
}
