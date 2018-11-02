package com.bankex.pay.presentation.presenter.base;

import com.arellomobile.mvp.MvpPresenter;
import com.bankex.pay.presentation.ui.base.BaseView;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Basic presenter that works with Rx.
 */
public class BasePresenter<View extends BaseView> extends MvpPresenter<View> {
	private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

	/**
	 * Returns {@link CompositeDisposable} that clears all references to {@link #onDestroy()}.
	 *
	 * @return {@link CompositeDisposable}
	 */
	public final CompositeDisposable getRxCompositeDisposable() {
		return mCompositeDisposable;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mCompositeDisposable.dispose();
	}
}
