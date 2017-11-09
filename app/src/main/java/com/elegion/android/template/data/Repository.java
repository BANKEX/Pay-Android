package com.elegion.android.template.data;

import android.content.Context;
import android.support.annotation.NonNull;

import com.elegion.android.template.data.local.PreferencesRepository;
import com.elegion.android.template.data.model.Feature;
import com.elegion.android.template.data.provider.ServiceProvider;
import com.elegion.android.template.data.remote.TemplateService;
import com.elegion.android.template.data.remote.request.LoginRequest;
import com.elegion.android.template.data.remote.response.LoginResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import okhttp3.Credentials;

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

    public Single<LoginResponse> login(String username, String password) {
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

    public Single<List<Feature>> getFeatures(int offset, int count) {
        return Single.defer(() -> {
            final List<Feature> features = new ArrayList<>();
            final int amount = offset + count;
            for (int i = offset; i < amount; i++) {
                features.add(new Feature("Title " + i, "Description " + i));
            }
            return Single.just(features);
        }).delay(1500, TimeUnit.MILLISECONDS);
    }
}
