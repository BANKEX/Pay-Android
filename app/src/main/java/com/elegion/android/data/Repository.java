package com.elegion.android.data;

import android.content.Context;
import android.support.annotation.NonNull;

import com.elegion.android.data.local.PreferencesRepository;
import com.elegion.android.data.model.UserProfile;
import com.elegion.android.data.provider.ServiceProvider;
import com.elegion.android.data.remote.TemplateService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * @author mikhail barannikov
 */
public class Repository {
    private PreferencesRepository mPreferencesRepository;
    private TemplateService mTemplateService;
    private static Repository sInstance;

    public static Repository get(Context context) {
        if (sInstance == null) {
            sInstance = new Repository(context);
        }
        return sInstance;
    }

    private Repository(@NonNull Context context) {
        mTemplateService = ServiceProvider.getServiceInstance(TemplateService.class);
        mPreferencesRepository = new PreferencesRepository(context);
    }

    public void logout() {
        mPreferencesRepository.setLoginToken("");
        mPreferencesRepository.setCurrentUser(null);
    }

    public Observable<Boolean> login(String email, String password) {
        return Observable.just(true).delay(3000, TimeUnit.MILLISECONDS);
    }

    public Observable<UserProfile> getProfile(long id) {
        return mTemplateService.getProfile(id);
    }

    public Observable<List<UserProfile>> getProfiles(int offset, int count) {
        return Observable.defer(() -> {
            final List<UserProfile> profiles = new ArrayList<>();
            UserProfile userProfile;
            final int amount = offset + count;
            for (int i = offset; i < amount; i++) {
                userProfile = new UserProfile();
                userProfile.setFirstName("First name " + i);
                userProfile.setLastName("Last name " + i);
                profiles.add(userProfile);
            }
            return Observable.just(profiles);
        }).delay(3000, TimeUnit.MILLISECONDS);
    }
}
