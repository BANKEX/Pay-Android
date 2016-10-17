package com.elegion.android.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.elegion.android.AppDelegate;
import com.elegion.android.repository.group.GroupRepository;
import com.elegion.android.repository.group.GroupService;

/**
 * @author Nikita Bumakov
 */
public class RepositoryProvider {

    @Nullable
    private static GroupRepository sGroupRepository;

    private RepositoryProvider() {
        //Not implemented
    }

    @NonNull
    private static <T> T getServiceInstance(Class<T> clazz) {
        return AppDelegate.getRetrofitInstance().create(clazz);
    }

    @NonNull
    public static GroupRepository provideGroupsRepository() {
        if (sGroupRepository == null) {
            sGroupRepository = new GroupRepository(getServiceInstance(GroupService.class));
        }
        return sGroupRepository;
    }
}
