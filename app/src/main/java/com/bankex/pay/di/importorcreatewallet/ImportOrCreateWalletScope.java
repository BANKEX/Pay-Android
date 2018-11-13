package com.bankex.pay.di.importorcreatewallet;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Scope for impoet or create wallet screen.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ImportOrCreateWalletScope {
}
