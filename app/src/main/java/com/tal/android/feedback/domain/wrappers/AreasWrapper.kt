package com.tal.android.feedback.domain.wrappers

import com.google.gson.annotations.SerializedName
import com.tal.android.feedback.domain.Area
import java.io.Serializable

class AreasWrapper : Serializable {
    @SerializedName("areas")
    val areas: List<Area>? = null
}