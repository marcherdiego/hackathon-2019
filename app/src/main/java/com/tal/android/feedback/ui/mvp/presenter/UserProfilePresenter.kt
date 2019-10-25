package com.tal.android.feedback.ui.mvp.presenter

import android.content.Intent
import android.net.Uri
import com.nerdscorner.mvplib.events.presenter.BaseActivityPresenter
import com.tal.android.feedback.ui.activities.ChapterDetailsActivity
import com.tal.android.feedback.ui.activities.MyFeedbackActivity
import com.tal.android.feedback.ui.activities.SquadDetailActivity
import com.tal.android.feedback.ui.activities.UserProfileActivity
import com.tal.android.feedback.ui.dialog.SendFeedbackDialog
import com.tal.android.feedback.ui.mvp.model.UserProfileModel
import com.tal.android.feedback.ui.mvp.view.UserProfileView
import org.greenrobot.eventbus.Subscribe


class UserProfilePresenter(view: UserProfileView, model: UserProfileModel) :
    BaseActivityPresenter<UserProfileView, UserProfileModel>(view, model) {

    init {
        view.hideFab()
    }

    @Subscribe
    fun onUserFetchedSuccessfully(event: UserProfileModel.UserFetchedSuccessfullyEvent) {
        with(event.userProfile) {
            view.loadUserData(
                email,
                getDisplayName(),
                position,
                pictureUrl,
                flagUrl,
                location,
                timeZone,
                model.getUserLocalTime(),
                squad?.name,
                chapter?.name,
                userLeader?.getDisplayName(),
                "@$slackName"
            )
        }
        if (model.isMyUser().not()) {
            //If it's not my profile, show the FAB
            view.showFab()
        }
    }

    @Subscribe
    fun onUserActionButtonButtonClicked(event: UserProfileView.UserActionButtonButtonClickedEvent) {
        view.withActivity {
            model.userProfile?.let {
                SendFeedbackDialog(this, it, model.getBus())
            }
        }
    }

    @Subscribe
    fun onUserChapterClicked(event: UserProfileView.UserChapterClickedEvent) {
        view.activity?.let {
            it.startActivity(
                Intent(it, ChapterDetailsActivity::class.java)
                    .putExtra(ChapterDetailsActivity.CHAPTER_ID, model.userProfile?.chapter?.id)
            )
        }
    }

    @Subscribe
    fun onUserSquadClicked(event: UserProfileView.UserSquadClickedEvent) {
        view.activity?.let {
            it.startActivity(
                Intent(it, SquadDetailActivity::class.java)
                    .putExtra(SquadDetailActivity.SQUAD_ID, model.userProfile?.squad?.id)
            )
        }
    }

    @Subscribe
    fun onUserEmailClicked(event: UserProfileView.UserEmailClickedEvent) {
        view.activity?.let {
            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse("mailto:${model.userProfile?.email}")
            it.startActivity(emailIntent)
        }
    }

    @Subscribe
    fun onUserLeaderClicked(event: UserProfileView.UserLeaderClickedEvent) {
        view.activity?.let {
            it.startActivity(
                Intent(it, UserProfileActivity::class.java)
                    .putExtra(UserProfileActivity.USER_ID, model.userProfile?.userLeader?.id)
            )
        }
    }

    @Subscribe
    fun onUserSlackClicked(event: UserProfileView.UserSlackClickedEvent) {
        view.activity?.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(UserProfileModel.SLACK_PROFILE_ADDRESS + model.userProfile?.slackUser)
            ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }

    @Subscribe
    fun onViewFeedbacksClicked(event: UserProfileView.ViewFeedbacksClickedEvent) {
        view.activity?.let {
            it.startActivity(
                Intent(it, MyFeedbackActivity::class.java)
            )
        }
    }

    @Subscribe
    fun onSendFeedbackClicked(event: SendFeedbackDialog.SendFeedbackClickedEvent) {
        model.sendFeedback(event.feedback.feedback ?: return)
    }

    @Subscribe
    fun onFeedbackSentSuccessfully(event: UserProfileModel.FeedbackSentSuccessfullyEvent) {
        view.showToast("Feedback sent!")
    }

    override fun onResume() {
        super.onResume()
        model.fetchUserData()
    }
}
