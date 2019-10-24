package com.tal.android.feedback.ui.mvp.presenter

import android.content.Intent
import android.net.Uri
import com.nerdscorner.mvplib.events.presenter.BaseActivityPresenter

import com.tal.android.feedback.ui.mvp.model.UserProfileModel
import com.tal.android.feedback.ui.mvp.view.UserProfileView
import org.greenrobot.eventbus.Subscribe

class UserProfilePresenter(view: UserProfileView, model: UserProfileModel) :
    BaseActivityPresenter<UserProfileView, UserProfileModel>(view, model) {

    init {
        if (model.isMyUser()) {
            //If it's my profile, hide the FAB
            view.hideFab()
        }
        with(model.userProfile) {
            view.loadUserData(
                email,
                getDisplayName(),
                position,
                pictureUrl,
                location,
                timeZone,
                model.getUserLocalTime(),
                currentSquad,
                chapter,
                userLeader,
                slackUser
            )
        }
    }

    @Subscribe
    fun onUserActionButtonButtonClicked(event: UserProfileView.UserActionButtonButtonClickedEvent) {
    }

    @Subscribe
    fun onUserChapterClicked(event: UserProfileView.UserChapterClickedEvent) {
    }

    @Subscribe
    fun onUserSquadClicked(event: UserProfileView.UserSquadClickedEvent) {
    }

    @Subscribe
    fun onUserEmailClicked(event: UserProfileView.UserEmailClickedEvent) {
    }

    @Subscribe
    fun onUserLeaderClicked(event: UserProfileView.UserLeaderClickedEvent) {
    }

    @Subscribe
    fun onUserSlackClicked(event: UserProfileView.UserSlackClickedEvent) {
        view.activity?.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(UserProfileModel.SLACK_PROFILE_ADDRESS + model.userProfile.slackUser)
            ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }
}
