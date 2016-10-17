package com.elegion.android.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikita Bumakov
 */
public class GroupResponse {

    @SerializedName("response")
    private List<GroupInfo> mGroupInfoList = new ArrayList<>();

    public List<GroupInfo> getGroupInfoList() {
        return mGroupInfoList;
    }
}
