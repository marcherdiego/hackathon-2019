package com.tal.android.feedback.ui.mvp.model

import com.nerdscorner.mvplib.events.model.BaseEventsModel
import com.tal.android.feedback.domain.Constants
import com.tal.android.feedback.domain.UserProfile
import com.tal.android.feedback.network.ServiceGenerator
import com.tal.android.feedback.network.extensions.enqueueResponseNotNull
import com.tal.android.feedback.network.extensions.noCache
import com.tal.android.feedback.network.services.UsersService

class UserProfileModel(private val userId: Int) : BaseEventsModel() {
    private val usersService = ServiceGenerator.createServiceBundle(UsersService::class.java)
    var userProfile: UserProfile? = null

    fun isMyUser() = userId == Constants.loggedUserId

    fun fetchUserData() {
        usersService
            .noCache()
            .getUserById(userId)
            .enqueueResponseNotNull(
                success = {
                    if (it.user == null) {
                        bus.post(UserFetchFailedException())
                    } else {
                        userProfile = it.user
                        bus.post(UserFetchedSuccessfullyEvent(it.user))
                    }
                },
                fail = {
                    bus.post(UserFetchFailedException(it.errorBody))
                }
            )
    }

    fun getUserLocalTime() = "11:34 AM"

    class UserFetchedSuccessfullyEvent(val userProfile: UserProfile)
    class UserFetchFailedException(val errorBody: String? = null)

    companion object {
        const val SLACK_PROFILE_ADDRESS = "slack://user?team=theappraisallane&id="
    }
}
