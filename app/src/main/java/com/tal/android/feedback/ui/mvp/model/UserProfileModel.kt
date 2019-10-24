package com.tal.android.feedback.ui.mvp.model

import com.nerdscorner.mvplib.events.model.BaseEventsModel
import com.tal.android.feedback.domain.UserProfile

class UserProfileModel(val userProfile: UserProfile) : BaseEventsModel() {
    fun getUserLocalTime(): String? {
        return "05:56 PM"
    }

    fun isMyUser(): Boolean {
        return true
        //TODO match user with current logged user
    }

    companion object {
        const val SLACK_PROFILE_ADDRESS = "slack://user?team=theappraisallane&id="
    }
}
