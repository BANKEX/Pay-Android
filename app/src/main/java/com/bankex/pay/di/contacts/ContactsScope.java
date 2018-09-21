package com.bankex.pay.di.contacts;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Скоуп для фрагмента с конктами
 *
 * @author Denis Anisimov.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ContactsScope {

}
