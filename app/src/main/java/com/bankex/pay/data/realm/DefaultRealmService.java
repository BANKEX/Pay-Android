package com.bankex.pay.data.realm;

import com.bankex.pay.data.entity.ContactModel;
import com.bankex.pay.model.domain.PayWalletModel;
import io.reactivex.Single;
import io.realm.Realm;
import io.realm.RealmResults;
import javax.annotation.Nullable;

/**
 * Реализация {@link DefaultRealmService}
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
public class DefaultRealmService implements IRealmService {
	/**
	 * {@inheritDoc }
	 */
	@Override
	public Single<PayWalletModel> saveWallet(PayWalletModel payWalletModel) {
		return Single.fromCallable(() -> {
			Realm realm = Realm.getDefaultInstance();
			realm.beginTransaction();
			realm.copyToRealmOrUpdate(payWalletModel);
			realm.commitTransaction();
			return payWalletModel;
		});
	}

	/**
	 * {@inheritDoc }
	 */
	@Override
	public Single<PayWalletModel> getWallet() {
		return Single.fromCallable(() -> {
			PayWalletModel payWalletModel = Realm.getDefaultInstance().where(PayWalletModel.class).findFirst();
			PayWalletModel walletModel = null;
			if (payWalletModel != null) {
				walletModel = new PayWalletModel(payWalletModel.getAddress(), payWalletModel.getName());
				walletModel.setKey(payWalletModel.getKey());
			}
			return walletModel;
		});
	}

	/**
	 * {@inheritDoc }
	 */
	@Nullable @Override public ContactModel getContactById(String id) {
		return Realm.getDefaultInstance().where(ContactModel.class).equalTo("address", id).findFirst();
	}

	/**
	 * {@inheritDoc }
	 */
	@Nullable @Override public RealmResults<ContactModel> getAllContacts() {
		return Realm.getDefaultInstance().where(ContactModel.class).findAll();
	}

	/**
	 * {@inheritDoc }
	 */
	@Override public Single<ContactModel> addContact(ContactModel contact) {
		return Single.create(singleEmitter -> {
			Realm realmInstance = Realm.getDefaultInstance();
			realmInstance.executeTransaction(realm -> {
				realm.copyToRealmOrUpdate(contact);
				singleEmitter.onSuccess(contact);
			});
			realmInstance.commitTransaction();
		});
	}

	/**
	 * {@inheritDoc }
	 */
	@Override public void deleteContactById(String id) {
		Realm realm = Realm.getDefaultInstance();
		realm.beginTransaction();
		ContactModel contact = realm.where(ContactModel.class).equalTo("address", id).findFirst();
		if (contact != null) contact.deleteFromRealm();
		realm.commitTransaction();
	}

	/**
	 * {@inheritDoc }
	 */
	@Override
	public void closeRealm() {
		//todo
	}
}
