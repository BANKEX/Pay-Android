package com.bankex.pay.di.wallet;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Scope for Wallet screen
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface WalletScope {
}
