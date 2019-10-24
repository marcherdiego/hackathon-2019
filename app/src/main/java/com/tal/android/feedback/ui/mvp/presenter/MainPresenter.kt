package com.tal.android.feedback.ui.mvp.presenter

import android.content.Intent
import com.nerdscorner.mvplib.events.presenter.BaseActivityPresenter
import com.tal.android.feedback.ui.activities.ChaptersListActivity
import com.tal.android.feedback.ui.activities.SquadsListActivity
import com.tal.android.feedback.ui.activities.UserProfileActivity

import com.tal.android.feedback.ui.mvp.model.MainModel
import com.tal.android.feedback.ui.mvp.view.MainView
import org.greenrobot.eventbus.Subscribe

class MainPresenter(view: MainView, model: MainModel) :
    BaseActivityPresenter<MainView, MainModel>(view, model) {

    @Subscribe
    fun onMyProfileClicked(event: MainView.MyProfileClickedEvent) {
        view.activity?.let {
            it.startActivity(
                Intent(it, UserProfileActivity::class.java)
                    .putExtra(UserProfileActivity.USER_ID, 1)
            )
        }
    }

    @Subscribe
    fun onSquadsClicked(event: MainView.SquadsClickedEvent) {
        view.activity?.let {
            it.startActivity(
                Intent(it, SquadsListActivity::class.java)
            )
        }
    }

    @Subscribe
    fun onChaptersClicked(event: MainView.ChaptersClickedEvent) {
        view.activity?.let {
            it.startActivity(
                Intent(it, ChaptersListActivity::class.java)
            )
        }
    }
}
