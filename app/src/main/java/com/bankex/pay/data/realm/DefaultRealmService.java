package com.bankex.pay.data.realm;

import android.support.annotation.Nullable;
import com.bankex.pay.domain.model.PayWalletModel;
import com.bankex.pay.data.entity.ContactModel;
import io.reactivex.Single;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import java.util.List;

/**
 * Implementation of interface {@link DefaultRealmService}.
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
	// TODO add asynchronous
	@Nullable @Override public List<ContactModel> getAllContacts() {
		RealmResults<ContactModel> realmResults = Realm.getDefaultInstance()
				.where(ContactModel.class)
				.sort("name", Sort.ASCENDING)
				.findAll();

		List<ContactModel> contactModels = Realm.getDefaultInstance().copyFromRealm(realmResults);
		if (!contactModels.isEmpty()) {
			Character groupFirstLetter = contactModels.get(0).getName().charAt(0);
			contactModels.get(0).setGroupLetterMarker(groupFirstLetter.toString());

			for (int i = 1; i < contactModels.size(); i++) {
				if (groupFirstLetter.equals(contactModels.get(i).getName().charAt(0))) {
					contactModels.get(i).setGroupLetterMarker("");
				} else {
					groupFirstLetter = contactModels.get(i).getName().charAt(0);
					contactModels.get(i).setGroupLetterMarker(groupFirstLetter.toString());
				}
			}
		}

		return contactModels;
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
