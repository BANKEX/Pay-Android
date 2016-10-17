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

    @SQLiteColumn(Columns.DESCRIPTION)
    @SerializedName("description")
    private String mDescription;

    @SQLiteColumn(Columns.PHOTO)
    @SerializedName("photo_big")
    private String mPhoto;

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getPhoto() {
        return mPhoto;
    }

    public interface Columns extends BaseColumns {
        String NAME = "name";
        String PHOTO = "photo";
        String DESCRIPTION = "description";
    }
}
