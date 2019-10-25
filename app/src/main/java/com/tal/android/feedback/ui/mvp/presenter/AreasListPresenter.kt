package com.tal.android.feedback.ui.mvp.presenter

import android.content.Intent
import com.nerdscorner.mvplib.events.presenter.BaseActivityPresenter
import com.tal.android.feedback.ui.activities.AreaDetailsActivity
import com.tal.android.feedback.ui.adapters.AreasAdapter
import com.tal.android.feedback.ui.mvp.model.AreasListModel
import com.tal.android.feedback.ui.mvp.view.AreasListView
import org.greenrobot.eventbus.Subscribe

class AreasListPresenter(view: AreasListView, model: AreasListModel) :
    BaseActivityPresenter<AreasListView, AreasListModel>(view, model) {

    @Subscribe
    fun onAreaClicked(event: AreasAdapter.AreaClickedEvent) {
        view.activity?.let {
            it.startActivity(
                Intent(it, AreaDetailsActivity::class.java)
                    .putExtra(AreaDetailsActivity.AREA_ID, event.areaId)
            )
        }
    }

    @Subscribe
    fun onAreasFetchedSuccessfully(event: AreasListModel.AreasFetchedSuccessfullyEvent) {
        view.setAreasAdapter(AreasAdapter(event.areas, model.getBus()))
    }

    override fun onResume() {
        super.onResume()
        model.fetchChapters()
    }
}
