package com.bankex.pay.di.wallet;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * Scope for wallet main screen.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface WalletScope {
}
