package com.tal.android.feedback.ui.mvp.presenter

import android.content.Intent
import com.nerdscorner.mvplib.events.presenter.BaseActivityPresenter
import com.tal.android.feedback.ui.activities.UserProfileActivity
import com.tal.android.feedback.ui.adapters.UsersAdapter
import com.tal.android.feedback.ui.mvp.model.TeamListModel
import com.tal.android.feedback.ui.mvp.view.TeamListView
import org.greenrobot.eventbus.Subscribe

class TeamListPresenter(view: TeamListView, model: TeamListModel) :
    BaseActivityPresenter<TeamListView, TeamListModel>(view, model) {

    @Subscribe
    fun onUsersFetchedSuccessfully(event: TeamListModel.UsersFetchedSuccessfullyEvent) {
        view.setUsersAdapter(UsersAdapter(event.users, model.getBus()))
    }

    @Subscribe
    fun onUserClicked(event: UsersAdapter.UserClickedEvent) {
        view.activity?.let {
            it.startActivity(
                Intent(it, UserProfileActivity::class.java)
                    .putExtra(UserProfileActivity.USER_ID, event.userId)
            )
        }
    }

    override fun onResume() {
        super.onResume()
        model.fetchUserData()
    }
}
