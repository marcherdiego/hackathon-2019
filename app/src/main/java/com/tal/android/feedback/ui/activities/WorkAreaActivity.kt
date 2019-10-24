package com.tal.android.feedback.ui.activities

import android.os.Bundle

import com.nerdscorner.mvplib.events.activity.BaseActivity
import com.tal.android.feedback.R

import com.tal.android.feedback.ui.mvp.model.WorkAreaModel
import com.tal.android.feedback.ui.mvp.presenter.WorkAreaPresenter
import com.tal.android.feedback.ui.mvp.view.WorkAreaView

class WorkAreaActivity : BaseActivity<WorkAreaPresenter>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.work_area_activity)

        presenter = WorkAreaPresenter(
            WorkAreaView(this),
            WorkAreaModel()
        )
    }
}
