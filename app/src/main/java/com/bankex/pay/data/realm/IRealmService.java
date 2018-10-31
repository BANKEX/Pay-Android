package com.bankex.pay.data.realm;

import com.bankex.pay.data.entity.ContactModel;
import com.bankex.pay.domain.model.PayWalletModel;
import io.reactivex.Single;
import java.util.List;
import javax.annotation.Nullable;

/**
 * Interface to work with database.
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

	// =====================
	// CONTACTS
	// =====================

	/**
	 * Method that search in database for certain contact by id.
	 *
	 * @param id - contacts` id (contacts` address).
	 * @return contact or null if there is no such item in database.
	 */
	@Nullable
	ContactModel getContactById(String id);

	/**
	 * Methods that gets all contacts from database.
	 *
	 * @return contact list or null if table is empty.
	 */
	@Nullable
	List<ContactModel> getAllContacts();

	/**
	 * Method that inserts only one item to the contact list.
	 *
	 * @param contact - contact to insert in table.
	 */
	Single<ContactModel> addContact(ContactModel contact);

	/**
	 * Method to delete existed contact item from table.
	 *
	 * @param id - contacts` id (contacts` address).
	 */
	void deleteContactById(String id);

	/**
	 * Close database connection.
	 */
	void closeRealm();
}
