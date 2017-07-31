package com.elegion.android.data.remote;

import com.elegion.android.data.model.Feature;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author mikhail barannikov
 */
public interface TemplateService {

    @GET("api/v1/sample")
    Observable<Feature> getFeature(@Query("id") long id);
}
