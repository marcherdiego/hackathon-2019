package com.tal.android.feedback.ui.mvp.view

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.nerdscorner.mvplib.events.activity.BaseActivity
import com.nerdscorner.mvplib.events.view.BaseActivityView
import com.tal.android.feedback.R
import com.tal.android.feedback.ui.adapters.UsersAdapter

class TeamListView(activity: BaseActivity<*>) : BaseActivityView(activity) {
    private val usersList: RecyclerView = activity.findViewById(R.id.users_list)

    init {
        usersList.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    fun setUsersAdapter(adapter: UsersAdapter) {
        usersList.adapter = adapter
    }
}
