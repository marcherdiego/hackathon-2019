package com.tal.android.feedback.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserProfile : Serializable {
    @SerializedName("id")
    val id: Int? = null

    @SerializedName("email")
    val email: String? = "jgonzalez@theappraisallane.com"

    @SerializedName("first_name")
    val firstName: String? = "Javier"

    @SerializedName("last_name")
    val lastName: String? = "González"

    @SerializedName("position")
    val position: String? = "Backend developer"

    @SerializedName("picture")
    val pictureUrl: String? =
        "https://imagizer.imageshack.com/v2/100x75q90/921/CZe5QU.jpg"

    @SerializedName("location")
    val location: String? = "Montevideo, Uruguay"

    @SerializedName("time_zone")
    val timeZone: String? = "GMT -3"

    @SerializedName("current_squad_id")
    val currentSquadId: Int? = null

    @SerializedName("current_squad")
    val currentSquad: String? = "Details"

    @SerializedName("chapter_id")
    val chapterId: Int? = null

    @SerializedName("chapter")
    val chapter: String? = "Backend"

    @SerializedName("user_leader_id")
    val userLeaderId: Int? = null

    @SerializedName("user_leader")
    val userLeader: String? = "Christian Schmeichel"

    @SerializedName("slack_handle")
    val slackUser: String? = "DCLE3NCLV"

    fun getDisplayName() = "${firstName?.capitalize()} ${lastName?.capitalize()}"
}