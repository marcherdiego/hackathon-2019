package com.tal.android.feedback.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserWrapper : Serializable {
    @SerializedName("user")
    val user: UserProfile? = null
}