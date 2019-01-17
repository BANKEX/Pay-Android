package com.bankex.pay.di.transactionhistory;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * Scope for transaction history screen.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface TransactionHistoryScope {
}
