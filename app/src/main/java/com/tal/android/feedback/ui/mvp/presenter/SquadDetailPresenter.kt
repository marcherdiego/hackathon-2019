package com.tal.android.feedback.ui.mvp.presenter

import android.content.Intent
import com.nerdscorner.mvplib.events.presenter.BaseActivityPresenter
import com.tal.android.feedback.ui.activities.UserProfileActivity
import com.tal.android.feedback.ui.adapters.SquadMembersAdapter

import com.tal.android.feedback.ui.mvp.model.SquadDetailModel
import com.tal.android.feedback.ui.mvp.view.SquadDetailView
import org.greenrobot.eventbus.Subscribe

class SquadDetailPresenter(view: SquadDetailView, model: SquadDetailModel) :
    BaseActivityPresenter<SquadDetailView, SquadDetailModel>(view, model) {

    @Subscribe
    fun onSquadFetchedSuccessfully(event: SquadDetailModel.SquadFetchedSuccessfullyEvent) {
        view.loadSquadDetails(
            "Squad ${event.squadName}",
            event.squadImageUrl
        )
        view.setSquadMembersAdapter(SquadMembersAdapter(event.squadMembers, model.getBus()))
    }

    @Subscribe
    fun onSquadMemberClicked(event: SquadMembersAdapter.SquadMemberClickedEvent) {
        view.activity?.let {
            it.startActivity(
                Intent(it, UserProfileActivity::class.java)
                    .putExtra(UserProfileActivity.USER_ID, event.memberId)
            )
        }
    }

    override fun onResume() {
        super.onResume()
        model.fetchSquadMembers()
    }
}
