package com.tal.android.feedback.ui.mvp.view

import androidx.recyclerview.widget.RecyclerView
import com.nerdscorner.mvplib.events.activity.BaseActivity
import com.nerdscorner.mvplib.events.view.BaseActivityView
import com.tal.android.feedback.R
import com.tal.android.feedback.ui.adapters.SquadsAdapter

class SquadsListView(activity: BaseActivity<*>) : BaseActivityView(activity) {
    private val squadsList: RecyclerView = activity.findViewById(R.id.squads)

    fun setSquadsAdapter(adapter: SquadsAdapter) {
        squadsList.adapter = adapter
    }
}
