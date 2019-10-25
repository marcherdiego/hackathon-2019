package com.tal.android.feedback.domain.wrappers

import com.google.gson.annotations.SerializedName
import com.tal.android.feedback.domain.FeedbackRequest
import java.io.Serializable

class FeedbackRequestWrapper : Serializable {
    @SerializedName("feedback")
    var feedback: FeedbackRequest? = null
}