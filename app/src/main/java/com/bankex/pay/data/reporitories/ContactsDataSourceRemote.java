package com.bankex.pay.data.reporitories;


import com.bankex.pay.data.BaseFirebaseDataSource;
import com.bankex.pay.data.entity.ContactsEntity;
import com.bankex.pay.utils.rx.RxFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Датасорс дляктонтактов из файрбэйса
 * @author Denis Anisimov.
 */
public class ContactsDataSourceRemote extends BaseFirebaseDataSource {

    private DatabaseReference childReference = null;

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;

    @Inject
    public ContactsDataSourceRemote(FirebaseAuth firebaseAuth, FirebaseDatabase firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
        this.firebaseAuth = firebaseAuth;
    }

    public DatabaseReference getChildReference() {
        if (childReference == null) {
            this.childReference = this.firebaseDatabase.
                    getReference()
                    .child(FIREBASE_CHILD_KEY_CONTACTS)
                    .child(this.firebaseAuth.getCurrentUser().getUid());
        }

        return childReference;
    }

    public Observable<ContactsEntity> getContacts() {
        return RxFirebase.getObservable(getChildReference(), ContactsEntity.class);
    }
}
