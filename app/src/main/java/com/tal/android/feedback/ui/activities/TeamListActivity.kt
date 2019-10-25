package com.tal.android.feedback.ui.activities

import android.os.Bundle

import com.nerdscorner.mvplib.events.activity.BaseActivity
import com.tal.android.feedback.R

import com.tal.android.feedback.ui.mvp.model.TeamListModel
import com.tal.android.feedback.ui.mvp.presenter.TeamListPresenter
import com.tal.android.feedback.ui.mvp.view.TeamListView

class TeamListActivity : BaseActivity<TeamListPresenter>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.users_list_activity)

        presenter = TeamListPresenter(
            TeamListView(this),
            TeamListModel()
        )
    }
}
