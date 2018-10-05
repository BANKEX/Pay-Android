package com.bankex.pay.di.walletinfo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Скоуп экрана продробностей кошелька
 *
 * @author Denis Anisimov on 05.10.2018.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface WalletInfoScope {
}
