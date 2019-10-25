package com.tal.android.feedback.ui.activities

import android.os.Bundle

import com.nerdscorner.mvplib.events.activity.BaseActivity
import com.tal.android.feedback.R

import com.tal.android.feedback.ui.mvp.model.AreasListModel
import com.tal.android.feedback.ui.mvp.presenter.AreasListPresenter
import com.tal.android.feedback.ui.mvp.view.AreasListView

class AreasListActivity : BaseActivity<AreasListPresenter>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.areas_list_activity)

        presenter = AreasListPresenter(
            AreasListView(this),
            AreasListModel()
        )
    }
}
