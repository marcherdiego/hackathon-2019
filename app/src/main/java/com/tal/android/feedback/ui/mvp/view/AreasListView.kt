package com.tal.android.feedback.ui.mvp.view

import androidx.recyclerview.widget.RecyclerView
import com.nerdscorner.mvplib.events.activity.BaseActivity
import com.nerdscorner.mvplib.events.view.BaseActivityView
import com.tal.android.feedback.R
import com.tal.android.feedback.ui.adapters.AreasAdapter

class AreasListView(activity: BaseActivity<*>) : BaseActivityView(activity) {
    private val areasList: RecyclerView = activity.findViewById(R.id.areas_list)

    fun setAreasAdapter(adapter: AreasAdapter) {
        areasList.adapter = adapter
    }
}
