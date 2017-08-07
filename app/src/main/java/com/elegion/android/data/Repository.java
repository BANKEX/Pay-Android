package com.elegion.android.data;

import android.content.Context;
import android.support.annotation.NonNull;

import com.elegion.android.data.local.PreferencesRepository;
import com.elegion.android.data.model.Feature;
import com.elegion.android.data.provider.ServiceProvider;
import com.elegion.android.data.remote.TemplateService;
import com.elegion.android.data.remote.request.LoginRequest;
import com.elegion.android.data.remote.response.LoginResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
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

    public Observable<LoginResponse> login(String username, String password) {
        final String basicAuthHeader = Credentials.basic(username, password);
        final List<String> scopes = Arrays.asList("repo", "user");
        final String note = "e-legion.com";
        final String clientId = getClientId();
        final String clientSecret = getClientSecret();
        final LoginRequest request = new LoginRequest(scopes, note, clientId, clientSecret);
        return mTemplateService.obtainOAuthToken(basicAuthHeader, request);
    }

    private String getClientId() {
        return "01d211db9c3e6dd5effd";
    }

    private String getClientSecret() {
        return "244d5f543b86f5dc6dd9555c6534cf8e86e46976";
    }

    public void saveLoginToken(String loginToken) {
        mPreferencesRepository.setLoginToken(loginToken);
    }

    public String getLoginToken() {
        return mPreferencesRepository.getLoginToken();
    }

    public Observable<List<Feature>> getFeatures(int offset, int count) {
        return Observable.defer(() -> {
            final List<Feature> features = new ArrayList<>();
            final int amount = offset + count;
            for (int i = offset; i < amount; i++) {
                features.add(new Feature("Title " + i, "Description " + i));
            }
            return Observable.just(features);
        }).delay(1500, TimeUnit.MILLISECONDS);
    }
}
