package com.tal.android.feedback.ui.activities

import android.os.Bundle

import com.nerdscorner.mvplib.events.activity.BaseActivity
import com.tal.android.feedback.R

import com.tal.android.feedback.ui.mvp.model.ChaptersListModel
import com.tal.android.feedback.ui.mvp.presenter.ChaptersListPresenter
import com.tal.android.feedback.ui.mvp.view.ChaptersListView

class ChaptersListActivity : BaseActivity<ChaptersListPresenter>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chapters_list_activity)

        presenter = ChaptersListPresenter(
            ChaptersListView(this),
            ChaptersListModel()
        )
    }
}
