package com.tal.android.feedback.ui.activities

import android.os.Bundle

import com.nerdscorner.mvplib.events.activity.BaseActivity
import com.tal.android.feedback.R

import com.tal.android.feedback.ui.mvp.model.SquadsListModel
import com.tal.android.feedback.ui.mvp.presenter.SquadsListPresenter
import com.tal.android.feedback.ui.mvp.view.SquadsListView

class SquadsListActivity : BaseActivity<SquadsListPresenter>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.squads_list_activity)

        presenter = SquadsListPresenter(
                SquadsListView(this),
                SquadsListModel()
        )
    }
}
