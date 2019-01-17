package com.bankex.pay.di.contacts;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Scope for contacts list screen.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ContactsScope {
}
