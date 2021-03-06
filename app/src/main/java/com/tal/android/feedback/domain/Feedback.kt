package com.tal.android.feedback.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Feedback : Serializable {
    @SerializedName("id")
    var id: Int? = null

    @SerializedName("sender")
    var sender: UserProfile? = null

    @SerializedName("receiver")
    var receiver: UserProfile? = null

    @SerializedName("text")
    var text: String? = null

    @SerializedName("rating")
    var rating: Int? = null

    @SerializedName("category")
    var category: String? = null
}