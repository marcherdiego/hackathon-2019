package com.tal.android.feedback.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserProfile : Serializable {
    @SerializedName("id")
    var id: Int? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("first_name")
    var firstName: String? = null

    @SerializedName("last_name")
    var lastName: String? = null

    @SerializedName("position")
    var position: String? = null

    @SerializedName("picture_url")
    var pictureUrl: String? = null

    @SerializedName("flag_url")
    var flagUrl: String? = null

    @SerializedName("location")
    var location: String? = "Montevideo, Uruguay"

    @SerializedName("time_zone")
    var timeZone: String? = "GMT -3"

    @SerializedName("squad")
    var squad: Squad? = null

    @SerializedName("chapter")
    var chapter: Chapter? = null

    @SerializedName("leader")
    var userLeader: UserProfile? = null

    @SerializedName("slack_id")
    var slackUser: String? = null

    @SerializedName("slack_name")
    var slackName: String? = null

    fun getDisplayName() = "${firstName?.capitalize()} ${lastName?.capitalize()}"
}