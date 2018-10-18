package com.bankex.pay.di.setpin;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * Scope for pin code setting
 *
 * @author Denis Anisimov.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface SetPinScope {

}
