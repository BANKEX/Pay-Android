package com.bankex.pay.di.setpin;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Скоуп для экрана установки пин кода
 * @author Denis Anisimov.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface SetPinScope {

}
