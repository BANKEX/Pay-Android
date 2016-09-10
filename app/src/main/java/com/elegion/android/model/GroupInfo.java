package com.elegion.android.model;

import android.provider.BaseColumns;

import com.google.gson.annotations.SerializedName;

import rxsqlite.annotation.SQLiteColumn;
import rxsqlite.annotation.SQLiteObject;
import rxsqlite.annotation.SQLitePk;

/**
 * @author Nikita Bumakov
 */
@SQLiteObject("groups")
public class GroupInfo {

    @SQLitePk
    @SerializedName("gid")
    private long mId;

    @SQLiteColumn(Columns.NAME)
    @SerializedName("name")
    private String mName;

    public interface Columns extends BaseColumns {

        String NAME = "name";
    }
}
