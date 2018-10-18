package com.bankex.pay.di.contacts;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Скоуп экрана контактов
 *
 * @author Pavel Apanovskiy on 12/10/2018.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ContactsScope {
}
