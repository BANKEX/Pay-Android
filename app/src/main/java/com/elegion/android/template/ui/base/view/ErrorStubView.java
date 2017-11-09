package com.elegion.android.template.ui.base.view;

import org.reactivestreams.Subscription;

/**
 * @author Nikita Bumakov
 */
public interface ErrorStubView {

    void showErrorStub();

    void hideErrorStub(Subscription s);
}
