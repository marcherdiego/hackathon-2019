package com.tal.android.feedback.domain.wrappers

import com.google.gson.annotations.SerializedName
import com.tal.android.feedback.domain.Squad
import java.io.Serializable

class SquadsWrapper : Serializable {
    @SerializedName("squads")
    val squads: List<Squad>? = null
}