package com.elegion.android.repository.group;

import android.support.annotation.NonNull;

import com.elegion.android.model.GroupInfo;
import com.elegion.android.util.CollectionUtil;

import rx.Observable;
import rx.Statement;
import rxsqlite.RxSQLite;
import rxsqlite.RxSQLiteWhere;

/**
 * @author Nikita Bumakov
 */
public class GroupRepository {

    private final GroupService mApi;

    public GroupRepository(@NonNull GroupService service) {
        mApi = service;
    }

    @NonNull
    public Observable<GroupInfo> fetchGroupInfo(long groupId) {
        return mApi.getGroupInfo(groupId)
                .flatMap(groupResponse ->
                        Statement.ifThen(() -> CollectionUtil.isEmpty(groupResponse.getGroupInfoList()),
                                Observable.empty(),
                                Observable.just(groupResponse.getGroupInfoList().get(0))))
                .flatMap(RxSQLite::save);
    }


    @NonNull
    public Observable<GroupInfo> getGroupInfo(long groupId) {
        return getGroupInfoLocal(groupId)
                .concatWith(fetchGroupInfo(groupId));
    }

    @NonNull
    public Observable<GroupInfo> getGroupInfoLocal(long groupId) {
        return RxSQLite.query(GroupInfo.class, new RxSQLiteWhere().equalTo(GroupInfo.Columns._ID, groupId));
    }
}
