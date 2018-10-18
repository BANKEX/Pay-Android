package com.bankex.pay.di.settings;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Scope for Settings screen
 *
 * @author Pavel Apanovskiy on 19.09.2018.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface SettingsScope {
}
