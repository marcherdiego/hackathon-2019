package com.tal.android.feedback.ui.mvp.view

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.nerdscorner.mvplib.events.activity.BaseActivity
import com.nerdscorner.mvplib.events.view.BaseActivityView
import com.tal.android.feedback.R
import com.tal.android.feedback.ui.adapters.ChaptersAdapter

class ChaptersListView(activity: BaseActivity<*>) : BaseActivityView(activity) {
    private val chapters: RecyclerView = activity.findViewById(R.id.chapters)

    init {
        chapters.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    fun setChaptersAdapter(adapter: ChaptersAdapter) {
        chapters.adapter = adapter
    }
}
