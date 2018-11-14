package com.bankex.pay.di.lockscreen;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Scope for lock screen.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface LockScreenScope {
}
