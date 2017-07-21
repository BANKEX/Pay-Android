package com.elegion.android.data;

import android.content.Context;
import android.support.annotation.NonNull;

import com.elegion.android.data.local.PreferencesRepository;
import com.elegion.android.data.provider.ServiceProvider;
import com.elegion.android.data.remote.TemplateService;

/**
 * @author mikhail barannikov
 */
public class Repository {
    private final PreferencesRepository mPreferencesRepository;
    private final TemplateService mTemplateService;
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
}
