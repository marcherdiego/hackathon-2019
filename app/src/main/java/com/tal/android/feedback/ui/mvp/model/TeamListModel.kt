package com.tal.android.feedback.ui.mvp.model

import com.nerdscorner.mvplib.events.model.BaseEventsModel
import com.tal.android.feedback.domain.UserProfile
import com.tal.android.feedback.network.ServiceGenerator
import com.tal.android.feedback.network.extensions.enqueueResponseNotNull
import com.tal.android.feedback.network.extensions.noCache
import com.tal.android.feedback.network.services.UsersService

class TeamListModel : BaseEventsModel() {
    private val usersService = ServiceGenerator.createServiceBundle(UsersService::class.java)

    fun fetchUserData() {
        usersService
            .noCache()
            .listUsers()
            .enqueueResponseNotNull(
                success = {
                    if (it.users == null) {
                        bus.post(UsersFetchFailedException())
                    } else {
                        bus.post(UsersFetchedSuccessfullyEvent(it.users))
                    }
                },
                fail = {
                    bus.post(UsersFetchFailedException(it.errorBody))
                }
            )
    }

    class UsersFetchedSuccessfullyEvent(val users: List<UserProfile>)
    class UsersFetchFailedException(val errorBody: String? = null)
}
