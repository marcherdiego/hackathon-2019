package com.tal.android.feedback.ui.mvp.presenter

import com.nerdscorner.mvplib.events.presenter.BaseActivityPresenter
import com.tal.android.feedback.ui.adapters.SquadsAdapter

import com.tal.android.feedback.ui.mvp.model.SquadsListModel
import com.tal.android.feedback.ui.mvp.view.SquadsListView
import org.greenrobot.eventbus.Subscribe

class SquadsListPresenter(view: SquadsListView, model: SquadsListModel) :
    BaseActivityPresenter<SquadsListView, SquadsListModel>(view, model) {

    @Subscribe
    fun onSquadsFetchedSuccessfully(event: SquadsListModel.SquadsFetchedSuccessfullyEvent) {
        view.setSquadsAdapter(SquadsAdapter(event.squads))
    }

    @Subscribe
    fun onSquadsFetchFailed(event: SquadsListModel.SquadsFetchFailedEvent) {
        view.showToast("Error while fetching squads: " + event.errorBody)
    }

    override fun onResume() {
        super.onResume()
        model.fetchSquads()
    }
}
