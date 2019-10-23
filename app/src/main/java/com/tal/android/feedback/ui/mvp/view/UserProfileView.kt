package com.tal.android.feedback.ui.mvp.view

import android.view.View
import com.nerdscorner.mvplib.events.activity.BaseActivity
import com.nerdscorner.mvplib.events.view.BaseActivityView
import com.ntal.android.feedback.R

class UserProfileView(activity: BaseActivity<*>) : BaseActivityView(activity) {
    init {
        activity.findViewById<View>(R.id.fab).setOnClickListener {
            bus.post(SendFeedbackButtonClickedEvent())
        }
    }

    class SendFeedbackButtonClickedEvent
}
