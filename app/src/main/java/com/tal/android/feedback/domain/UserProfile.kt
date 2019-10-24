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

    @SerializedName("location")
    var location: String? = "Montevideo, Uruguay"

    @SerializedName("time_zone")
    var timeZone: String? = "GMT -3"

    @SerializedName("current_squad_id")
    var currentSquadId: Int? = null

    @SerializedName("current_squad")
    var currentSquad: String? = null

    @SerializedName("chapter_id")
    var chapterId: Int? = null

    @SerializedName("chapter")
    var chapter: String? = null

    @SerializedName("user_leader_id")
    var userLeaderId: Int? = null

    @SerializedName("user_leader")
    var userLeader: String? = null

    @SerializedName("slack_handle")
    var slackUser: String? = null

    fun getDisplayName() = "${firstName?.capitalize()} ${lastName?.capitalize()}"
}