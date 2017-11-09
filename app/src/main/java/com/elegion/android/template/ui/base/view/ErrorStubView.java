package com.elegion.android.template.ui.base.view;

import io.reactivex.disposables.Disposable;

/**
 * @author Nikita Bumakov
 */
public interface ErrorStubView {

    void showErrorStub();

    void hideErrorStub(Disposable disposable);
}
