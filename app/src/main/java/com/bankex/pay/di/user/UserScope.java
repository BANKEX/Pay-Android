package com.bankex.pay.di.user;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * Scope for after-login part of application
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface UserScope {
}
