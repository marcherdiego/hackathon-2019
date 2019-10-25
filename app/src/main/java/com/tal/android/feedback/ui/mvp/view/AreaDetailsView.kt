package com.tal.android.feedback.ui.mvp.view

import android.view.View
import com.nerdscorner.mvplib.events.activity.BaseActivity
import com.nerdscorner.mvplib.events.view.BaseActivityView
import com.tal.android.feedback.R

class AreaDetailsView(activity: BaseActivity<*>) : BaseActivityView(activity) {
    init {
        activity.findViewById<View>(R.id.squads).setOnClickListener {
            bus.post(SquadsClickedEvent())
        }
        activity.findViewById<View>(R.id.chapters).setOnClickListener {
            bus.post(ChaptersClickedEvent())
        }
        activity.findViewById<View>(R.id.team).setOnClickListener {
            bus.post(TeamClickedEvent())
        }
    }

    class SquadsClickedEvent
    class ChaptersClickedEvent
    class TeamClickedEvent
}
