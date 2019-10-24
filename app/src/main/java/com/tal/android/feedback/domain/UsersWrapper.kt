package com.tal.android.feedback.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UsersWrapper : Serializable {
    @SerializedName("users")
    val users: List<UserProfile>? = null
}