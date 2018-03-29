package com.elegion.android.template.data.model

import com.google.gson.annotations.SerializedName

class Feature(
        @SerializedName("title")
        var title: String?,
        @SerializedName("description")
        var description: String?
)
