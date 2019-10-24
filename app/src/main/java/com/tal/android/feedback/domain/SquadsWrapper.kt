package com.tal.android.feedback.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SquadsWrapper : Serializable {
    @SerializedName("squads")
    val squads: List<Squad>? = null
}