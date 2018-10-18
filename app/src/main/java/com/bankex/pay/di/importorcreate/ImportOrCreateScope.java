package com.bankex.pay.di.importorcreate;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * Scpoe for screen Import/Create wallet
 *
 * @author Gevork Safaryan on 19.09.2018.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ImportOrCreateScope {
}
