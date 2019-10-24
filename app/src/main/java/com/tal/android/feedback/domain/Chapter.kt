package com.tal.android.feedback.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Chapter : Serializable {
    @SerializedName("id")
    var id: Int? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("picture_url")
    var imageUrl: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("leader")
    var leader: UserProfile? = null

    @SerializedName("members")
    var members: List<UserProfile>? = null
}