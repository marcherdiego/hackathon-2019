package com.tal.android.feedback.ui.mvp.view

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.nerdscorner.mvplib.events.activity.BaseActivity
import com.nerdscorner.mvplib.events.view.BaseActivityView
import com.tal.android.feedback.R
import com.tal.android.feedback.ui.adapters.SquadMembersAdapter

class SquadDetailView(activity: BaseActivity<*>) : BaseActivityView(activity) {
    private val squadMembersList: RecyclerView = activity.findViewById(R.id.squad_members_list)

    init {
        squadMembersList.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    fun setSquadMembersAdapter(adapter: SquadMembersAdapter) {
        squadMembersList.adapter = adapter
    }
}
