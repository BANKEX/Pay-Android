package com.bankex.pay.data.realm;

import com.bankex.pay.data.entity.Contact;
import com.bankex.pay.model.domain.PayWalletModel;
import io.reactivex.Single;
import javax.annotation.Nullable;

/**
 * Интерфейс работы с базой данных
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
public interface IRealmService {

	// =====================
	// WALLET
	// =====================
	/**
	 * Сохраняем кошелек в БД
	 *
	 * @param payWalletModel кошелек
	 * @return Single<PayWalletModel>
	 */
	Single<PayWalletModel> saveWallet(PayWalletModel payWalletModel);

	/**
	 * Пытаемся получить кошелек из БД
	 *
	 * @return кошелек
	 */
	@Nullable
	Single<PayWalletModel> getWallet();

	/**
	 * Закрыть соединение
	 */
	void closeRealm();

	// =====================
	// CONTACTS
	// =====================

	/**
	 *
	 * @param id
	 * @return
	 */
	@Nullable
	Contact getContactById(String id);

	/**
	 *
	 * @return
	 */
	@Nullable
	Single<Contact> getAllContacts();

	/**
	 *
	 * @param contact
	 */
	void addContact(Contact contact);

	/**
	 *
	 * @param id
	 */
	void deleteContactById(String id);
}
