package com.tal.android.feedback.domain.wrappers

import com.google.gson.annotations.SerializedName
import com.tal.android.feedback.domain.Feedback
import java.io.Serializable

class FeedbacksWrapper : Serializable {
    @SerializedName("feedbacks")
    val feedbacks: List<Feedback>? = null
}