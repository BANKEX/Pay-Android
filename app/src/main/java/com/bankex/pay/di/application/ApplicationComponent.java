package com.bankex.pay.di.application;

import android.content.Context;
import com.bankex.pay.di.applicationmodules.AnalyticsModule;
import com.bankex.pay.di.applicationmodules.NavigationModule;
import com.bankex.pay.di.applicationmodules.NetworkModule;
import com.bankex.pay.di.applicationmodules.RealmModule;
import com.bankex.pay.di.applicationmodules.RxModule;
import com.bankex.pay.di.user.UserComponent;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Application base component.
 */
@Component(modules = {
		NetworkModule.class,
		RxModule.class,
		RealmModule.class,
		ApplicationModule.class,
		NavigationModule.class,
		AnalyticsModule.class
})

@Singleton
public interface ApplicationComponent {
	UserComponent.Builder plusUserComponent();

	Context getAppContext();
}
