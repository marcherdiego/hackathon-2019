package com.tal.android.feedback.domain.wrappers

import com.google.gson.annotations.SerializedName
import com.tal.android.feedback.domain.Feedback
import java.io.Serializable

class FeedbackWrapper : Serializable {
    @SerializedName("feedback")
    val feedback: Feedback? = null
}