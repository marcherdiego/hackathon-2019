package com.tal.android.feedback.ui.mvp.presenter

import android.content.Intent
import android.net.Uri
import com.nerdscorner.mvplib.events.presenter.BaseActivityPresenter

import com.tal.android.feedback.ui.mvp.model.UserProfileModel
import com.tal.android.feedback.ui.mvp.view.UserProfileView
import org.greenrobot.eventbus.Subscribe

class UserProfilePresenter(view: UserProfileView, model: UserProfileModel) :
    BaseActivityPresenter<UserProfileView, UserProfileModel>(view, model) {

    @Subscribe
    fun onSendFeedbackButtonClicked(event: UserProfileView.SendFeedbackButtonClickedEvent) {
        view.activity?.startActivity(
            Intent(Intent.ACTION_VIEW, Uri.parse("slack://user?team=theappraisallane&id=DCLE3NCLV"))
        )
    }
}
