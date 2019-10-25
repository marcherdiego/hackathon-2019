package com.tal.android.feedback.ui.mvp.presenter

import android.content.Intent
import com.nerdscorner.mvplib.events.presenter.BaseActivityPresenter
import com.tal.android.feedback.ui.activities.ChaptersListActivity
import com.tal.android.feedback.ui.activities.SquadsListActivity
import com.tal.android.feedback.ui.mvp.model.AreaDetailsModel
import com.tal.android.feedback.ui.mvp.view.AreaDetailsView
import org.greenrobot.eventbus.Subscribe

class AreaDetailsPresenter(view: AreaDetailsView, model: AreaDetailsModel) :
    BaseActivityPresenter<AreaDetailsView, AreaDetailsModel>(view, model) {


    @Subscribe
    fun onSquadsClicked(event: AreaDetailsView.SquadsClickedEvent) {
        view.activity?.let {
            it.startActivity(
                Intent(it, SquadsListActivity::class.java)
            )
        }
    }

    @Subscribe
    fun onChaptersClicked(event: AreaDetailsView.ChaptersClickedEvent) {
        view.activity?.let {
            it.startActivity(
                Intent(it, ChaptersListActivity::class.java)
            )
        }
    }
}
