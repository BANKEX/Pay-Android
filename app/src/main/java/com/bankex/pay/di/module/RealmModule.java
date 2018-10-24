package com.bankex.pay.di.module;

import com.bankex.pay.data.realm.DefaultRealmService;
import com.bankex.pay.data.realm.IRealmService;
import com.bankex.pay.data.repository.ContactsRepository;
import com.bankex.pay.data.repository.IContactsRepository;
import com.bankex.pay.data.repository.IPayWalletRepository;
import com.bankex.pay.data.repository.PayWalletRepository;
import com.bankex.pay.domain.interactor.IPayWalletInteractor;
import com.bankex.pay.domain.interactor.PayWalletInteractor;
import dagger.Module;
import dagger.Provides;

/**
 * Модуль представления зависимостей Realm
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
@Module
public class RealmModule {
	@Provides
	IRealmService provideRealmService() {
		return new DefaultRealmService();
	}

	// TODO: 13/10/2018 Унести в корректный модуль
	@Provides
	IPayWalletRepository providePayWalletRepositor(IRealmService realmService) {
		return new PayWalletRepository(realmService);
	}

	@Provides
	IPayWalletInteractor providePayWalletInteractor(IPayWalletRepository saveWalletRepository) {
		return new PayWalletInteractor(saveWalletRepository);
	}

	@Provides
	IContactsRepository provideContactsRepository(IRealmService realmService) {
		return new ContactsRepository(realmService);
	}
}
