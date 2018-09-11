package com.bankex.pay.di.mainscreen;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Временный скоуп для варианта "чтобы быстро и работало"
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface MainScreenScope {
}
