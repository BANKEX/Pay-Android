package com.elegion.android.repository.group;

import com.elegion.android.model.GroupResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author Nikita Bumakov
 */
public interface GroupService {

    @GET("groups.getById")
    Observable<GroupResponse> getGroupInfo(@Query("group_ids") long groupId);
}
