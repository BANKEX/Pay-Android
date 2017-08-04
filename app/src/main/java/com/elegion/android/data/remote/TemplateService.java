package com.elegion.android.data.remote;

import com.elegion.android.data.remote.request.LoginRequest;
import com.elegion.android.data.remote.response.LoginResponse;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author mikhail barannikov
 */
public interface TemplateService {

    @POST("authorizations")
    Observable<LoginResponse> obtainOAuthToken(@Header("Authorization") String basicAuthHeader,
                                               @Body LoginRequest params);
}
