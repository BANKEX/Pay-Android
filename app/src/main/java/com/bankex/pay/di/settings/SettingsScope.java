package com.bankex.pay.di.settings;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * Scope for settings screen.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface SettingsScope {
}
