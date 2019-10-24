package com.tal.android.feedback.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Chapter : Serializable {
    @SerializedName("id")
    var id: Int? = null

    @SerializedName("name")
    var name: String? = null
}