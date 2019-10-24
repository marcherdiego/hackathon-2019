package com.tal.android.feedback.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SquadWrapper : Serializable {
    @SerializedName("squad")
    val squad: Squad? = null
}