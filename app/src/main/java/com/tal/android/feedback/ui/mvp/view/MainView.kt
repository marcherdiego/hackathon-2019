package com.tal.android.feedback.ui.mvp.view

import android.view.View
import com.nerdscorner.mvplib.events.activity.BaseActivity
import com.nerdscorner.mvplib.events.view.BaseActivityView
import com.tal.android.feedback.R

class MainView(activity: BaseActivity<*>) : BaseActivityView(activity) {
    init {
        activity.findViewById<View>(R.id.my_profile).setOnClickListener {
            bus.post(MyProfileClickedEvent())
        }
        activity.findViewById<View>(R.id.squads).setOnClickListener {
            bus.post(SquadsClickedEvent())
        }
    }

    class MyProfileClickedEvent
    class SquadsClickedEvent
}
