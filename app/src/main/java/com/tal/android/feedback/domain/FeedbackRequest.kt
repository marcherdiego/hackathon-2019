package com.tal.android.feedback.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class FeedbackRequest : Serializable {
    @SerializedName("sender_id")
    var senderId: Int? = null

    @SerializedName("receiver_id")
    var receiverId: Int? = null

    @SerializedName("text")
    var text: String? = null

    @SerializedName("rating")
    var rating: Int? = null

    @SerializedName("category")
    var categoryId: Int? = null
}