package com.tal.android.feedback.domain.wrappers

import com.google.gson.annotations.SerializedName
import com.tal.android.feedback.domain.Squad
import java.io.Serializable

class SquadWrapper : Serializable {
    @SerializedName("squad")
    val squad: Squad? = null
}