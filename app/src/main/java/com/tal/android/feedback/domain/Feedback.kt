package com.tal.android.feedback.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Feedback : Serializable {
    @SerializedName("id")
    val id: Int? = null

    @SerializedName("sender")
    val sender: UserProfile? = null

    @SerializedName("receiver")
    val receiver: UserProfile? = null

    @SerializedName("text")
    val text: String? = null

    @SerializedName("rating")
    val rating: Int? = null

    @SerializedName("category")
    val category: String? = null
}