package com.elegion.android.template.data.remote.rest;

import com.elegion.android.template.data.remote.rest.request.LoginRequest;
import com.elegion.android.template.data.remote.rest.response.LoginResponse;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * @author mikhail barannikov
 */
public interface TemplateService {

    @POST("authorizations")
    Flowable<LoginResponse> obtainOAuthToken(@Header("Authorization") String basicAuthHeader,
                                             @Body LoginRequest params);
}
