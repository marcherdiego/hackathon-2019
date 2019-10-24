package com.tal.android.feedback.domain.wrappers

import com.google.gson.annotations.SerializedName
import com.tal.android.feedback.domain.UserProfile
import java.io.Serializable

class UserWrapper : Serializable {
    @SerializedName("user")
    val user: UserProfile? = null
}