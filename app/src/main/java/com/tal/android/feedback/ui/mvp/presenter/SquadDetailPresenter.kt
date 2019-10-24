package com.tal.android.feedback.ui.mvp.presenter

import com.nerdscorner.mvplib.events.presenter.BaseActivityPresenter
import com.tal.android.feedback.ui.adapters.SquadMembersAdapter

import com.tal.android.feedback.ui.mvp.model.SquadDetailModel
import com.tal.android.feedback.ui.mvp.view.SquadDetailView
import org.greenrobot.eventbus.Subscribe

class SquadDetailPresenter(view: SquadDetailView, model: SquadDetailModel) :
    BaseActivityPresenter<SquadDetailView, SquadDetailModel>(view, model) {

    @Subscribe
    fun onSquadFetchedSuccessfully(event: SquadDetailModel.SquadFetchedSuccessfullyEvent) {
        view.setSquadMembersAdapter(SquadMembersAdapter(event.squadMembers))
    }

    override fun onResume() {
        super.onResume()
        model.fetchSquadMembers()
    }
}
