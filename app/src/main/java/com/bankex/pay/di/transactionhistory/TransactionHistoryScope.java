package com.bankex.pay.di.transactionhistory;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Скоуп экрана истории транзакций
 *
 * @author Pavel Apanovskiy on 27/09/2018.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface TransactionHistoryScope {
}
