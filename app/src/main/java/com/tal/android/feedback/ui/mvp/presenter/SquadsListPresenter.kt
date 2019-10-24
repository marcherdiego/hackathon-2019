package com.tal.android.feedback.ui.mvp.presenter

import android.content.Intent
import com.nerdscorner.mvplib.events.presenter.BaseActivityPresenter
import com.tal.android.feedback.ui.activities.SquadDetailActivity
import com.tal.android.feedback.ui.adapters.SquadsAdapter

import com.tal.android.feedback.ui.mvp.model.SquadsListModel
import com.tal.android.feedback.ui.mvp.view.SquadsListView
import org.greenrobot.eventbus.Subscribe

class SquadsListPresenter(view: SquadsListView, model: SquadsListModel) :
    BaseActivityPresenter<SquadsListView, SquadsListModel>(view, model) {

    @Subscribe
    fun onSquadsFetchedSuccessfully(event: SquadsListModel.SquadsFetchedSuccessfullyEvent) {
        view.setSquadsAdapter(SquadsAdapter(event.squads, model.getBus()))
    }

    @Subscribe
    fun onSquadsFetchFailed(event: SquadsListModel.SquadsFetchFailedEvent) {
        view.showToast("Error while fetching squads: " + event.errorBody)
    }

    @Subscribe
    fun onSquadClicked(event: SquadsAdapter.SquadClickedEvent) {
        view.activity?.let {
            it.startActivity(
                Intent(it, SquadDetailActivity::class.java)
                    .putExtra(SquadDetailActivity.SQUAD_ID, event.squadId)
            )
        }
    }

    override fun onResume() {
        super.onResume()
        model.fetchSquads()
    }
}
