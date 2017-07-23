package com.elegion.android.ui.base.view;

import com.arellomobile.mvp.MvpView;

/**
 * @author Mikhail Barannikov
 */
public interface LoadingView extends MvpView {

    void showLoadingIndicator();

    void hideLoadingIndicator();

}
