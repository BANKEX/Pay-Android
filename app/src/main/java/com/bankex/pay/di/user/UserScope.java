package com.bankex.pay.di.user;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * Scope for user part of application (after login).
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface UserScope {
}
