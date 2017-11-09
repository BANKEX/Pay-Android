package com.elegion.android.template.data.remote;

import com.elegion.android.template.data.remote.request.LoginRequest;
import com.elegion.android.template.data.remote.response.LoginResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * @author mikhail barannikov
 */
public interface TemplateService {

    @POST("authorizations")
    Single<LoginResponse> obtainOAuthToken(@Header("Authorization") String basicAuthHeader,
                                           @Body LoginRequest params);
}
