package com.elegion.android.repository.group;

import android.support.annotation.NonNull;

import com.elegion.android.model.GroupInfo;
import com.elegion.android.util.CollectionUtil;

import java.util.concurrent.TimeUnit;

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
        return Observable.timer(10, TimeUnit.SECONDS) //simulating long data processing
                .flatMap(time -> mApi.getGroupInfo(groupId, "description"))
                .flatMap(groupResponse ->
                        Statement.ifThen(() -> CollectionUtil.isEmpty(groupResponse.getGroupInfoList()),
                                Observable.empty(),
                                Observable.just(groupResponse.getGroupInfoList().get(0))))
                .flatMap(this::saveGroupInfo);
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

    @NonNull
    public Observable<Boolean> hasGroupInfoLocal(long groupId) {
        return RxSQLite.query(GroupInfo.class, new RxSQLiteWhere().equalTo(GroupInfo.Columns._ID, groupId)).map(
                groupInfo -> true
        ).switchIfEmpty(Observable.just(false));
    }

    private Observable<GroupInfo> saveGroupInfo(@NonNull GroupInfo groupInfo) {
        return RxSQLite.save(groupInfo);
    }
}
