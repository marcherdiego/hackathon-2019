package com.tal.android.feedback.domain.wrappers

import com.google.gson.annotations.SerializedName
import com.tal.android.feedback.domain.Chapter
import java.io.Serializable

class ChaptersWrapper : Serializable {
    @SerializedName("chapters")
    val chapters: List<Chapter>? = null
}