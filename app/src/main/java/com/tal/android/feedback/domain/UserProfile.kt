package com.tal.android.feedback.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserProfile : Serializable {
    @SerializedName("id")
    var id: Int? = null

    @SerializedName("email")
    var email: String? = "jgonzalez@theappraisallane.com"

    @SerializedName("first_name")
    var firstName: String? = "Javier"

    @SerializedName("last_name")
    var lastName: String? = "González"

    @SerializedName("position")
    var position: String? = "Backend developer"

    @SerializedName("picture_url")
    var pictureUrl: String? =
        "https://imagizer.imageshack.com/v2/100x75q90/921/CZe5QU.jpg"

    @SerializedName("location")
    var location: String? = "Montevideo, Uruguay"

    @SerializedName("time_zone")
    var timeZone: String? = "GMT -3"

    @SerializedName("current_squad_id")
    var currentSquadId: Int? = null

    @SerializedName("current_squad")
    var currentSquad: String? = "Details"

    @SerializedName("chapter_id")
    var chapterId: Int? = null

    @SerializedName("chapter")
    var chapter: String? = "Backend"

    @SerializedName("user_leader_id")
    var userLeaderId: Int? = null

    @SerializedName("user_leader")
    var userLeader: String? = "Christian Schmeichel"

    @SerializedName("slack_handle")
    var slackUser: String? = "DCLE3NCLV"

    fun getDisplayName() = "${firstName?.capitalize()} ${lastName?.capitalize()}"
}