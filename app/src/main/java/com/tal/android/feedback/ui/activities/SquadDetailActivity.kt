package com.tal.android.feedback.ui.activities

import android.os.Bundle

import com.nerdscorner.mvplib.events.activity.BaseActivity
import com.tal.android.feedback.R

import com.tal.android.feedback.ui.mvp.model.SquadDetailModel
import com.tal.android.feedback.ui.mvp.presenter.SquadDetailPresenter
import com.tal.android.feedback.ui.mvp.view.SquadDetailView

class SquadDetailActivity : BaseActivity<SquadDetailPresenter>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.squad_detail_activity)

        val squadId = intent.getIntExtra(SQUAD_ID, -1)
        if (squadId == -1) {
            finish()
            return
        }
        presenter = SquadDetailPresenter(
            SquadDetailView(this),
            SquadDetailModel(squadId)
        )
    }

    companion object {
        const val SQUAD_ID = "squad_id"
    }
}
