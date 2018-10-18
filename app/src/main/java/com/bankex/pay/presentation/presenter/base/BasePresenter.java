package com.bankex.pay.presentation.presenter.base;

import com.arellomobile.mvp.MvpPresenter;
import com.bankex.pay.presentation.view.base.BaseView;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Base presenter
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
public class BasePresenter<View extends BaseView> extends MvpPresenter<View> {

	private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

	/**
	 * Returns {@link CompositeDisposable}, which delete references on {@link #onDestroy()}.
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
