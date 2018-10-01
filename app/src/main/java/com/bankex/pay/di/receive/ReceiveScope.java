package com.bankex.pay.di.receive;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Скоуп экрана получения
 *
 * @author Pavel Apanovskiy on 30/09/2018.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ReceiveScope {
}
