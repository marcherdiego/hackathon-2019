package com.tal.android.feedback.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Squad : Serializable {
    @SerializedName("id")
    val id: Int? = null

    @SerializedName("name")
    val name: String? = null

    @SerializedName("product_owner")
    val productOwner: UserProfile? = null

    @SerializedName("scrum_master")
    val scrumMaster: UserProfile? = null

    @SerializedName("members")
    val members: List<UserProfile>? = null

    @SerializedName("description")
    val description: String? = null
}