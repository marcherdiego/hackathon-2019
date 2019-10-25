package com.tal.android.feedback.ui.activities

import android.os.Bundle

import com.nerdscorner.mvplib.events.activity.BaseActivity
import com.tal.android.feedback.R

import com.tal.android.feedback.ui.mvp.model.AreaDetailsModel
import com.tal.android.feedback.ui.mvp.presenter.AreaDetailsPresenter
import com.tal.android.feedback.ui.mvp.view.AreaDetailsView

class AreaDetailsActivity : BaseActivity<AreaDetailsPresenter>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.area_detail_activity)

        val areaId = intent.getIntExtra(AREA_ID, -1)
        if (areaId == -1) {
            finish()
            return
        }

        presenter = AreaDetailsPresenter(
            AreaDetailsView(this),
            AreaDetailsModel(areaId)
        )
    }

    companion object {
        const val AREA_ID = "area_id"
    }
}
